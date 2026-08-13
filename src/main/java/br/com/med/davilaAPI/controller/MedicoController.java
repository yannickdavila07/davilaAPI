package br.com.med.davilaAPI.controller;

import br.com.med.davilaAPI.dto.DadosAtualizarMedico;
import br.com.med.davilaAPI.dto.DadosCadastroMedico;
import br.com.med.davilaAPI.dto.DadosDetalhamentoMedico;
import br.com.med.davilaAPI.dto.DadosListagemMedico;
import br.com.med.davilaAPI.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dto, UriComponentsBuilder uriBuilder){
       var dados = medicoService.cadastrarMedico(dto);
       var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(dados.getId()).toUri();
       return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(dados));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var pagina = medicoService.listarMedicos(paginacao);
        return ResponseEntity.ok(pagina);

    }

    @PutMapping()
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid DadosAtualizarMedico dto){
       var medico = medicoService.atualizarMedico(dto);
       return ResponseEntity.ok(medico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarMedico(@PathVariable Long id){
        medicoService.deletarMedico(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable Long id){
        var medico = medicoService.detalharMedico(id);
        return ResponseEntity.ok(medico);
    }


}
