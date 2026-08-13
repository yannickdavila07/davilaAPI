package br.com.med.davilaAPI.controller;

import br.com.med.davilaAPI.dto.DadosAgendamentoConsulta;
import br.com.med.davilaAPI.dto.DadosCancelamentoConsulta;
import br.com.med.davilaAPI.dto.DadosDetalhamentoConsulta;
import br.com.med.davilaAPI.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dados){
        var dto = consultaService.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping()
    @Transactional
    public ResponseEntity deletarConsulta(@RequestBody @Valid DadosCancelamentoConsulta dados){
        consultaService.deletarConsulta(dados);
        return ResponseEntity.noContent().build();
    }
}
