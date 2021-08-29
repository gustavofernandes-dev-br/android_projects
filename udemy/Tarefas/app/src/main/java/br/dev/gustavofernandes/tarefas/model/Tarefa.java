package br.dev.gustavofernandes.tarefas.model;

import java.io.Serializable;

public class Tarefa implements Serializable {

    long Id;
    String Descricao;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}
