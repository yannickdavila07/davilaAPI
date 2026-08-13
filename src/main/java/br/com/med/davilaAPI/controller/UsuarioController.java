package br.com.med.davilaAPI.controller;

import br.com.med.davilaAPI.dto.DadosAutenticacao;
import br.com.med.davilaAPI.dto.DadosTokenJWT;
import br.com.med.davilaAPI.model.Usuario;
import br.com.med.davilaAPI.service.TokenService;
import br.com.med.davilaAPI.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var tokenJWT = usuarioService.gerarTokenJWT(dados);
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
