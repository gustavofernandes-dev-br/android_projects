package br.dev.gustavofernandes.tarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.dev.gustavofernandes.tarefas.R;
import br.dev.gustavofernandes.tarefas.dao.TarefasDao;
import br.dev.gustavofernandes.tarefas.model.Tarefa;

public class CriarTarefaActivity extends AppCompatActivity {
    int fPosicao = -1;
    EditText edtTarefa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_tarefa);

        edtTarefa = findViewById(R.id.edt_tarefa);
        Intent sIntent = getIntent();
        if(sIntent.hasExtra(MainActivity.POSICAO_TAREFA))
        {
            int sPosicao = sIntent.getIntExtra(MainActivity.POSICAO_TAREFA,-1);
            if(sPosicao > -1)
            {
                fPosicao = sPosicao;
                edtTarefa.setText(TarefasDao.mlistaTarefas.get(sPosicao).getDescricao());

            }
        }

        if(edtTarefa.getText().equals(""))
            setTitle("Inclusão");
        else
            setTitle("Alteração");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_cadastro_salvar) {

            TarefasDao sTarefaDao = new TarefasDao(getApplicationContext());
            if(fPosicao > -1)
            {
                Tarefa tarefa = new Tarefa();
                tarefa.setDescricao(edtTarefa.getText().toString());
                if(!sTarefaDao.Criar(tarefa));
                {
                    new Exception("Erro ao gravar dados");
                }
            }
            else
            {
                Tarefa tarefa = new Tarefa();
                tarefa.setDescricao(edtTarefa.getText().toString());
                if(!sTarefaDao.Criar(tarefa));
                {
                    new Exception("Erro ao gravar dados");
                }

            }
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}