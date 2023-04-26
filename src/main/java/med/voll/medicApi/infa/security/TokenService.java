package med.voll.medicApi.infa.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.medicApi.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try{
            var algoritmo = Algorithm.HMAC256(this.secret);

            return JWT.create()
                    .withIssuer("API Voll Med")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dtaExpiracao())
                    .withClaim("id", usuario.getId())
                    .sign(algoritmo);

        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar o token JWT", e);
        }
    }

    private Instant dtaExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
