package br.dev.gustavofernandes.tarefas.dao;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import br.dev.gustavofernandes.tarefas.model.Tarefa;
import br.dev.gustavofernandes.tarefas.util.DbTarefas;
import br.dev.gustavofernandes.tarefas.util.VariaveisGlobais;

public class TarefasDao implements  ITarefasDao {
    public static List<Tarefa> mlistaTarefas = new ArrayList<>();
    SQLiteDatabase escreve;
    SQLiteDatabase le;
    public TarefasDao(Context context) {
        DbTarefas db = new DbTarefas(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean Criar(Tarefa tarefa) {
        try{
            ContentValues values = new ContentValues();
            values.put("DESCRICAO",tarefa.getDescricao());
            values.put("INATIVO",0);
            escreve.insert("TAREFA",null,values);
        }catch (Exception ex)
        {
            return  false;
        }


        return true;
    }

    @Override
    public boolean Alterar(Tarefa tarefa) {
        return false;
    }

    @Override
    public boolean Inativar(Tarefa tarefa) {
        return false;
    }

    @Override
    public List<Tarefa> ListarTarefas() {
        List<Tarefa> sLstTarefas = new ArrayList<>();
        String sSql = "SELECT * FROM TAREFA WHERE INATIVO = 0 ";
        Cursor cursor = le.rawQuery(sSql,null);
        while (cursor.moveToNext())
        {
            Tarefa tarefa = new Tarefa();
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            String Descricao = cursor.getString(cursor.getColumnIndex("DESCRICAO"));

            tarefa.setDescricao(Descricao);
            tarefa.setId(id);
            sLstTarefas.add(tarefa);
        }
        return  sLstTarefas;
    }


//
//    public final static List<Tarefa> mlistaTarefas = new ArrayList<>();
//    public boolean Adicionar(Tarefa tarefa)
//    {
//        try {
//            mlistaTarefas.add(tarefa);
//            return  true;
//        }
//        catch (Exception ex)
//        {
//            new AlertDialog.Builder(VariaveisGlobais.contextoGlobal)
//                    .setTitle("Erro")
//                    .setMessage(ex.getMessage())
//                    .setPositiveButton("OK", null)
//                    .show();
//            return false;
//        }
//    }
//    public boolean Remover(Tarefa tarefa)
//    {
//        try {
//            mlistaTarefas.remove(tarefa);
//            return  true;
//        }
//        catch (Exception ex)
//        {
//            new AlertDialog.Builder(VariaveisGlobais.contextoGlobal)
//                    .setTitle("Erro")
//                    .setMessage(ex.getMessage())
//                    .setPositiveButton("OK", null)
//                    .show();
//            return false;
//        }
//    }
//    public boolean Atualizar(Tarefa tarefa)
//    {
//        try {
//            mlistaTarefas.set(PegarPosicaoPeloId(tarefa.getId()),tarefa);
//            return  true;
//        }
//        catch (Exception ex)
//        {
//            new AlertDialog.Builder(VariaveisGlobais.contextoGlobal)
//                    .setTitle("Erro")
//                    .setMessage(ex.getMessage())
//                    .setPositiveButton("OK", null)
//                    .show();
//            return false;
//        }
//    }
//    public int PegarPosicaoPeloId(int id)
//    {
//        try {
//            int scont = 0;
//            for (Tarefa item: mlistaTarefas)
//            {
//                scont++;
//                if(item.getId() == id)
//                    return scont;
//            }
//            return 0;
//        }
//        catch (Exception ex)
//        {
//            new AlertDialog.Builder(VariaveisGlobais.contextoGlobal)
//                    .setTitle("Erro")
//                    .setMessage(ex.getMessage())
//                    .setPositiveButton("OK", null)
//                    .show();
//            return 0;
//        }
//    }


}
