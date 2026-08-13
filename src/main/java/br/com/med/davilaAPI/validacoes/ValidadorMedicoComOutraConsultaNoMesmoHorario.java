package br.com.med.davilaAPI.validacoes;

import br.com.med.davilaAPI.dto.DadosAgendamentoConsulta;
import br.com.med.davilaAPI.exception.ValidationException;
import br.com.med.davilaAPI.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsultas {
    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var validacao = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (validacao){
            throw new ValidationException("Médico já possui outra consulta agendada nesse mesmo horário");
        }

    }
}
