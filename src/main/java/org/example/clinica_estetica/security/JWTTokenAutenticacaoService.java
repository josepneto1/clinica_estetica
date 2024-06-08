package org.example.clinica_estetica.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Component
public class JWTTokenAutenticacaoService {

    private static final long EXPIRATION_TIME = 86_400_000;

    private static final String SECRET = "pastel";

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";

    public void addAuthentication(HttpServletResponse response, String username) throws Exception {

        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // tempo para expirar
                .signWith(SignatureAlgorithm.ES512, SECRET).compact();

        String token = TOKEN_PREFIX + " " + JWT;

        response.addHeader(HEADER_STRING, token);

        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
    }
}
