package uol.compasso.estados.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class loginForm {
    private String email;
    private String keyWord;

    public String getEmail() {
        return email;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, keyWord);
    }
}
