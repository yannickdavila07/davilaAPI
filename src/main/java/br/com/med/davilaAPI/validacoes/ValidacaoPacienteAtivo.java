package br.com.med.davilaAPI.validacoes;

import br.com.med.davilaAPI.dto.DadosAgendamentoConsulta;
import br.com.med.davilaAPI.exception.ValidationException;
import br.com.med.davilaAPI.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacienteAtivo implements ValidadorAgendamentoDeConsultas {
    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados){

        if (dados.idPaciente() == null){
            throw new ValidationException("Id do paciente nulo.");
        }

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        if (paciente.getAtivo() != true){
            throw new ValidationException("Paciete está inativo no sistema.");
        }

    }
}
