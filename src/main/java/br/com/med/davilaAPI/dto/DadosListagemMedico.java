package br.com.med.davilaAPI.dto;

import br.com.med.davilaAPI.model.Endereco;
import br.com.med.davilaAPI.model.Especialidade;
import br.com.med.davilaAPI.model.Medico;

public record DadosListagemMedico(
        Long id,
        String nome,
        String telefone,
        String email,
        String crm,
        Especialidade especialidade
) {

    public DadosListagemMedico(Medico medico){
        this(medico.getId(),medico.getNome(), medico.getTelefone(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
