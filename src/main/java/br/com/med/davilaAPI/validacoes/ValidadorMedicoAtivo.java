package br.com.med.davilaAPI.validacoes;

import br.com.med.davilaAPI.dto.DadosAgendamentoConsulta;
import br.com.med.davilaAPI.exception.ValidationException;
import br.com.med.davilaAPI.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){

        if (dados.idMedico() == null){
            return;
        }


        var medico = repository.getReferenceById(dados.idMedico());

        if (medico.getAtivo() != true){
            throw new ValidationException("Medico escolhido está Inativo no sistema");
        }



    }
}
