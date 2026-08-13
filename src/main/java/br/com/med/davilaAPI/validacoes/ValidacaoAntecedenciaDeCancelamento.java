package br.com.med.davilaAPI.validacoes;

import br.com.med.davilaAPI.dto.DadosCancelamentoConsulta;
import br.com.med.davilaAPI.exception.ValidationException;
import br.com.med.davilaAPI.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacaoAntecedenciaDeCancelamento implements ValidacaoCancelamento{

    @Autowired
    private ConsultaRepository repository;

    public void validarCancelamento(DadosCancelamentoConsulta dados){
        var consulta = repository.getReferenceById(dados.idConsulta());
        var dataConsulta = consulta.getData();
        var dataAgora = LocalDateTime.now();
        var diferenca = Duration.between(dataAgora, dataConsulta).toMinutes();

        if (diferenca < 1440){
            throw new ValidationException("Antecedencia mínima de 24 horas!!!");
        }

    }
}
