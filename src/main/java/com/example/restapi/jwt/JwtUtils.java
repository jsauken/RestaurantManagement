package com.example.restapi.jwt;
import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Date;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setName(claims.get("Name", String.class));
        jwtInfoToken.setEmail(claims.getSubject());
        jwtInfoToken.setRoles(claims.get("Roles", String.class));
        return jwtInfoToken;
    }
}
