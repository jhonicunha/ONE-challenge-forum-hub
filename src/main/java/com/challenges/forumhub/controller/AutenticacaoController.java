package com.challenges.forumhub.controller;

import com.challenges.forumhub.domain.usuario.DadosAutenticacao;
import com.challenges.forumhub.domain.usuario.Usuario;
import com.challenges.forumhub.domain.usuario.UsuarioRepository;
import com.challenges.forumhub.infra.security.DadosTokenJWT;
import com.challenges.forumhub.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/usuarios")
    @Transactional
    public ResponseEntity cadastrarNovoUsuario(@RequestBody @Valid DadosAutenticacao dados) {
        if (usuarioRepository.findByEmail(dados.email()) != null) {
            return ResponseEntity.badRequest().body("Usuário com este email já existe.");
        }
        var nome = dados.email().split("@")[0];
        var senhaCriptografada = passwordEncoder.encode(dados.senha());
        var usuario = new Usuario(null, nome, dados.email(), senhaCriptografada);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}