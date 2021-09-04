package br.dev.gustavofernandes.organize.model;

import androidx.annotation.Dimension;

import br.dev.gustavofernandes.organize.util.Ref;

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

    public boolean ValidarCadastro(Ref<String> mensagem)
    {
        mensagem.set("");
        if(Email.equals(""))
            mensagem.set(" E-mail inválido");

        if(Nome.equals(""))
            mensagem.set(mensagem.get() + "\n Nome inválido");

        if(Senha.equals(""))
            mensagem.set(mensagem.get() + "\n Senha inválida");
        else if(SenhaConfirmar.equals(""))
            mensagem.set(mensagem.get() + "\n Necessário confirmar a senha.");
        else if(!Senha.equals(SenhaConfirmar))
            mensagem.set(mensagem.get() + "\n As senhas não coincidem.");

        return mensagem.get().equals("");
    }


    public boolean ValidarLogin(Ref<String> mensagem)
    {
        mensagem.set("");
        if(Email.equals(""))
            mensagem.set(" E-mail inválido");

        if(Senha.equals(""))
            mensagem.set(mensagem.get() + "\n Senha inválida");

        return mensagem.equals("");
    }
}
