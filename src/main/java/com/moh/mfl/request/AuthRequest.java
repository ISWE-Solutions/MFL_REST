package com.moh.mfl.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Francis chulu
 */
public class AuthRequest implements Serializable {

    @NotBlank(message = "Password is required!")
    private String password;
    @NotBlank(message = "Username is required!")
    private String email;

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

   

}
