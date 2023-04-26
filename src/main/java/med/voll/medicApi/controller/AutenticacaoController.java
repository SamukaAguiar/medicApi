package med.voll.medicApi.controller;

import jakarta.validation.Valid;
import med.voll.medicApi.domain.usuario.AutenticacaoDtoCreate;
import med.voll.medicApi.domain.usuario.Usuario;
import med.voll.medicApi.infa.security.AutenticacaoDtoTokenJWT;
import med.voll.medicApi.infa.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity logar(@RequestBody @Valid AutenticacaoDtoCreate aut){
        var authenticationToken = new UsernamePasswordAuthenticationToken(aut.login(), aut.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new AutenticacaoDtoTokenJWT(tokenJwt));
    }
}
