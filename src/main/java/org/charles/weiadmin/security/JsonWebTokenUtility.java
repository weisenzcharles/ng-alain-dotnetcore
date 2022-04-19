package org.charles.weiadmin.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class JsonWebTokenUtility {

    private SignatureAlgorithm signatureAlgorithm;
    private Key secretKey;

    public JsonWebTokenUtility() {

        // THIS IS NOT A SECURE PRACTICE!
        // For simplicity, we are storing a static key here.
        // Ideally, in a microservices environment, this key would kept on a
        // config server.
        signatureAlgorithm = SignatureAlgorithm.HS512;
        String encodedKey = "L7A/6zARSkK1j7Vd5SDD9pSSqZlqF7mAhiOgRbgv9Smce6tf4cJnvKOjtKPxNNnWQj+2lQEScm3XIUjhW+YVZg==";
        secretKey = deserializeKey(encodedKey);

        // secretKey = MacProvider.generateKey(signatureAlgorithm);
    }

    public String createJsonWebToken(AuthTokenUser authTokenUser) {
        String token = Jwts.builder().setSubject(authTokenUser.userId).claim("email", authTokenUser.email)
            .claim("roles", authTokenUser.roleNames).setExpiration(authTokenUser.expirationDate)
            .claim("privileges", authTokenUser.privileges)
            .signWith(getSignatureAlgorithm(), getSecretKey()).compact();
        return token;
    }

    private Key deserializeKey(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        Key key = new SecretKeySpec(decodedKey, getSignatureAlgorithm().getJcaName());
        return key;
    }

    private Key getSecretKey() {
        return secretKey;
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public AuthTokenUser parseAndValidate(String token) {
        AuthTokenUser authTokenUser = null;
        try {
            Claims claims = Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
            String userId = claims.getSubject();
            String email = (String) claims.get("email");
            List<String> roleNames = (List<String>) claims.get("roles");
            List<String> privileges = (List<String>) claims.get("privileges");
            Date expirationDate = claims.getExpiration();

            authTokenUser = new AuthTokenUser();
            authTokenUser.userId = userId;
            authTokenUser.email = email;
            authTokenUser.roleNames = roleNames;
            authTokenUser.expirationDate = expirationDate;
            authTokenUser.privileges = privileges;
        } catch (JwtException ex) {
            System.out.println(ex);
        }
        return authTokenUser;
    }

    private String serializeKey(Key key) {
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        return encodedKey;
    }

}
