package com.vidaaustista.bootcamp.security;

import com.vidaaustista.bootcamp.model.UsuarioDTO;

import javax.xml.bind.DatatypeConverter;

public class Token {
    private String strToken;

    public String getStrToken() {
        return strToken;
    }

    public void setStrToken(String strToken) {
        this.strToken = strToken;
    }

}
