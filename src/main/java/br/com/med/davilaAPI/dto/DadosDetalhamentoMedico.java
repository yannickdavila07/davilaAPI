package br.com.med.davilaAPI.dto;

import br.com.med.davilaAPI.model.Endereco;
import br.com.med.davilaAPI.model.Especialidade;
import br.com.med.davilaAPI.model.Medico;

public record DadosDetalhamentoMedico(
        Long id,
        Boolean ativo,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade Especialidade,
        Endereco endereco
    

) {

    public DadosDetalhamentoMedico(Medico medico){
        this(medico.getId(), medico.getAtivo(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());

    }
}
