package br.com.med.davilaAPI.validacoes;

import br.com.med.davilaAPI.dto.DadosCancelamentoConsulta;

public interface ValidacaoCancelamento {

    void validarCancelamento(DadosCancelamentoConsulta dados);
}
