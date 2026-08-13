package br.com.med.davilaAPI.service;

import br.com.med.davilaAPI.dto.*;
import br.com.med.davilaAPI.model.Paciente;
import br.com.med.davilaAPI.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Paciente cadastrarPaciente(DadosCadastroPaciente dados){
        var paciente = new Paciente(dados);
        repository.save(paciente);
        return paciente;
    }

    public Page<DadosListagemPaciente> listarPacientes(Pageable paginacao){
        return repository.findAllByAtivoTrueOrAtivoIsNull(paginacao).map(DadosListagemPaciente::new);
    }


    public DadosDetalhamentoPaciente atualizarPaciente(DadosAtualizarPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarPaciente(dados);
        return new DadosDetalhamentoPaciente(paciente);

    }

    public void deletarPaciente(Long id){
        var paciente = repository.getReferenceById(id);
        paciente.deletarPaciente();
    }

    public DadosDetalhamentoPaciente detalhamentoPaciente(Long id){
        var paciente = repository.findById(id).get();
        return new DadosDetalhamentoPaciente(paciente);

    }
}
