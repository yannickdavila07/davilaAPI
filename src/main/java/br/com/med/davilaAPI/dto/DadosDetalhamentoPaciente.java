package br.com.med.davilaAPI.dto;

import br.com.med.davilaAPI.model.Endereco;
import br.com.med.davilaAPI.model.Paciente;

public record DadosDetalhamentoPaciente(
        Long id,
        Boolean ativo,
        String nome,
        String telefone,
        String email,
        String cpf,
        Endereco endereco

) {

    public DadosDetalhamentoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getAtivo(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }


}
