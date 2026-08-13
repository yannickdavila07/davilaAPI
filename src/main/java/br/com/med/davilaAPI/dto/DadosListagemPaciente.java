package br.com.med.davilaAPI.dto;

import br.com.med.davilaAPI.model.Paciente;

public record DadosListagemPaciente(
        String nome,
        String email,
        String cpf
) {
    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }

}
