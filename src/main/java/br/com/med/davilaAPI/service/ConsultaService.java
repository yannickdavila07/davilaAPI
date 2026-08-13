package br.com.med.davilaAPI.service;

import br.com.med.davilaAPI.dto.DadosAgendamentoConsulta;
import br.com.med.davilaAPI.dto.DadosCancelamentoConsulta;
import br.com.med.davilaAPI.dto.DadosDetalhamentoConsulta;
import br.com.med.davilaAPI.exception.ValidationException;
import br.com.med.davilaAPI.model.Consulta;
import br.com.med.davilaAPI.model.Medico;
import br.com.med.davilaAPI.repository.ConsultaRepository;
import br.com.med.davilaAPI.repository.MedicoRepository;
import br.com.med.davilaAPI.repository.PacienteRepository;
import br.com.med.davilaAPI.validacoes.ValidacaoCancelamento;
import br.com.med.davilaAPI.validacoes.ValidadorAgendamentoDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadores;

    @Autowired
    private List<ValidacaoCancelamento> validacaoCancelamentos;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidationException("Id do paciente nao existe!");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidationException("Id do medico nao existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        if (medico == null){
            throw new ValidationException("Nao há medicos disponiveis nessa data!");
        }

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if (dados.especialidade() == null){
            throw new ValidationException("Especialidade é obrigatoria quando nao há escolha de medico.");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void deletarConsulta(DadosCancelamentoConsulta dados) {
        if (dados.motivo() == null){
            throw new ValidationException("O Motivo do cancelamento é obrigatório!");
        }

        if (dados.idConsulta() == null){
            throw new ValidationException("Id da consulta inválida!");
        }

        if (!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidationException("Id da consulta nao existe!");
        }

        validacaoCancelamentos.forEach(v -> v.validarCancelamento(dados));

        consultaRepository.deleteById(dados.idConsulta());

    }
}
