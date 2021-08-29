package br.dev.gustavofernandes.tarefas.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import br.dev.gustavofernandes.tarefas.R;
import br.dev.gustavofernandes.tarefas.adapter.TarefasAdapter;
import br.dev.gustavofernandes.tarefas.dao.TarefasDao;
import br.dev.gustavofernandes.tarefas.util.DbTarefas;
import br.dev.gustavofernandes.tarefas.util.RecyclerItemClickListener;
import br.dev.gustavofernandes.tarefas.util.VariaveisGlobais;

import static br.dev.gustavofernandes.tarefas.dao.TarefasDao.mlistaTarefas;

public class MainActivity extends AppCompatActivity {

    public static final String POSICAO_TAREFA = "POSICAO_TAREFA";
    RecyclerView rcvListaTarefas;
    TarefasAdapter fTarefasAdapter;
    int fPosicao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        VariaveisGlobais.contextoGlobal = this;
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fPosicao = 0;
                Intent sIntent = new Intent(MainActivity.this,CriarTarefaActivity.class);
                startActivity(sIntent);
            }
        });

        rcvListaTarefas = findViewById(R.id.main_recycler_tarefas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvListaTarefas.setLayoutManager(layoutManager);
        rcvListaTarefas.setHasFixedSize(true);
        //rcvListaTarefas.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        rcvListaTarefas.addOnItemTouchListener(
                new RecyclerItemClickListener(this, rcvListaTarefas, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        fPosicao = position;
                        Intent sIntent = new Intent(MainActivity.this,CriarTarefaActivity.class);
                        sIntent.putExtra(POSICAO_TAREFA,fPosicao);
                        startActivity(sIntent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                })
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        TarefasDao sTarefasDao = new TarefasDao(this);
        mlistaTarefas = sTarefasDao.ListarTarefas();
        fTarefasAdapter = new TarefasAdapter(mlistaTarefas);
        rcvListaTarefas.setAdapter(fTarefasAdapter);
    }
}