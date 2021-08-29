package br.dev.gustavofernandes.tarefas.dao;

import java.util.List;

import br.dev.gustavofernandes.tarefas.model.Tarefa;

public interface ITarefasDao {
     boolean Criar(Tarefa tarefa);
     boolean Alterar(Tarefa tarefa);
     boolean Inativar(Tarefa tarefa);
     List<Tarefa> ListarTarefas();
}
