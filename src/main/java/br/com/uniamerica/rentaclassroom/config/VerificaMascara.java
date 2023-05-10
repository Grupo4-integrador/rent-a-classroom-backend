package br.com.uniamerica.rentaclassroom.config;

import org.springframework.stereotype.Component;

@Component
public class VerificaMascara {
    public boolean email(String email) {
        String txt = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        return email.matches(txt);
    }

}
