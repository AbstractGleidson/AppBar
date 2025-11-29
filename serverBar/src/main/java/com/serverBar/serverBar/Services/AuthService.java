package com.serverBar.serverBar.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${app.garcom.password}")
    private String garcomPassword;

    public boolean isAdmin(String senha) {
        return adminPassword.equals(senha);
    }

    public boolean isGarcom(String senha) {
        return garcomPassword.equals(senha);
    }
}
