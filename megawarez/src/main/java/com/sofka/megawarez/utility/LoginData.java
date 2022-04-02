package com.sofka.megawarez.utility;

import lombok.Data;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

/**
 * Clase para crear el token de Usuario
 *
 * @version 1.0.0 2022-03-31
 * @author Julian Lasso <julian.lasso@sofka.com.co>
 * @since 1.0.0
 */
@Data
public class LoginData {
    private String username;
    private String password;

    public void setPassword(String password) throws Exception {
        this.password = createMD5(password);
    }

    public String getToken() throws Exception {
        return createMD5(getUsername() + Instant.now());
    }

    private String createMD5(String password) throws Exception {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(password.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            String hashtext = bigInt.toString(16);
            while(hashtext.length() < 32 ){
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new Exception(noSuchAlgorithmException.getCause());
        }
    }

}
