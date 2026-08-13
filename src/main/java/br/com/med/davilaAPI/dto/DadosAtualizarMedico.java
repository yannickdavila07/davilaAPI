package br.com.med.davilaAPI.dto;

import br.com.med.davilaAPI.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedico(
        @NotNull
        Long id,

        String nome,

        String telefone,

        DadosEndereco endereco

) {
}
