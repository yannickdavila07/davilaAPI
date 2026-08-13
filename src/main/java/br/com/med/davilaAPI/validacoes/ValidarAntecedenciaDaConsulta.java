package br.com.med.davilaAPI.validacoes;

import br.com.med.davilaAPI.dto.DadosAgendamentoConsulta;
import br.com.med.davilaAPI.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarAntecedenciaDaConsulta implements ValidadorAgendamentoDeConsultas {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var dataAgora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(dataAgora, dataConsulta).toMinutes();
        if (diferencaEmMinutos < 30){
            throw new ValidationException("Consulta deve ser agendada com antecedencia mínima de 30 minutos");
        }

    }
}
