package br.dev.gustavofernandes.organize.model;

import androidx.annotation.Dimension;

public class Usuario {
    String Nome;
    String Email;
    String Senha;
    String SenhaConfirmar;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getSenhaConfirmar() {
        return SenhaConfirmar;
    }

    public void setSenhaConfirmar(String senhaConfirmar) {
        SenhaConfirmar = senhaConfirmar;
    }

    public boolean ValidarCadastro(String[] msg)
    {
        msg[0] = "";
        if(Email.equals(""))
            msg[0] = "E-mail inválido";

        if(Nome.equals(""))
            msg[0] += "\n Nome inválido";

        if(Senha.equals(""))
            msg[0]  += "\n Senha inválida";

        if(SenhaConfirmar.equals(""))
            msg[0]  += "\n Necessário confirmar a senha.";

        if(Senha.equals(SenhaConfirmar))
            msg[0]  += "\n As senhas não coincidem.";

        return msg[0].equals("");
    }


    public boolean ValidarLogin(String[] msg)
    {
        msg[0] = "";
        if(Email.equals(""))
            msg[0] = "E-mail inválido";

        if(Senha.equals(""))
            msg[0]  += "\n Senha inválida";

        return msg[0].equals("");
    }
}
