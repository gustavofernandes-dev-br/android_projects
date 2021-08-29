package br.dev.gustavofernandes.tarefas.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbTarefas extends SQLiteOpenHelper {
    final static int fVersao = 1;
    final  static String fBaseDados = "BDTAREFAS";

    public DbTarefas(@Nullable Context context) {
        super(context, fBaseDados, null, fVersao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sSql = "CREATE TABLE IF NOT EXISTS TAREFA ";
        sSql += "( ID INTEGER PRIMARY KEY AUTOINCREMENT, DESCRICAO TEXT NOT NULL, INATIVO BIT DEFAULT 0 );";
        try{
            db.execSQL(sSql);
            Log.i("INFO","Sucesso ao criar tabela TAREFA");
        }catch (Exception ex)
        {
            ex.printStackTrace();
            Log.i("INFO","Erro ao criar tabela TAREFA \n" + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
