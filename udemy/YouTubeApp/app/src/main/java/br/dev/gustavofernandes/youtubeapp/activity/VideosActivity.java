package br.dev.gustavofernandes.youtubeapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.dev.gustavofernandes.youtubeapp.R;
import br.dev.gustavofernandes.youtubeapp.adapter.VideosAdapter;
import br.dev.gustavofernandes.youtubeapp.api.TodaRequisicao2;
import br.dev.gustavofernandes.youtubeapp.api.YouTubeService;
import br.dev.gustavofernandes.youtubeapp.listener.RecyclerItemClickListener;
import br.dev.gustavofernandes.youtubeapp.model.Video;
import br.dev.gustavofernandes.youtubeapp.services.RetrofitConfig;
import br.dev.gustavofernandes.youtubeapp.services.YouTubeConfiguracao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VideosActivity extends AppCompatActivity {
    VideosAdapter adapter;
    MaterialSearchView searchView;
    List<TodaRequisicao2.Item> lstListaVideos = new ArrayList<>();
    RecyclerView recyclerVideos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canal_pdv_net);
        ConfigurarSuporteToobar();
        ConfigurarLista();
        ConfigurarEventosSeachToobar();
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void ConfigurarEventosSeachToobar() {

        searchView.setVoiceSearch(true);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                RecuperarVideos(query.toString().replace(" ", "+"));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic

            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    private void ConfigurarSuporteToobar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("You tube");
        setSupportActionBar(toolbar);
    }

    private void ConfigurarLista() {
        recyclerVideos = findViewById(R.id.recycler_lista_videos);
        searchView = (MaterialSearchView)findViewById(R.id.search_view);
        recyclerVideos.setHasFixedSize(true);
        recyclerVideos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VideosAdapter(lstListaVideos,this);
        recyclerVideos.setAdapter(adapter);
        recyclerVideos.addOnItemTouchListener(new RecyclerItemClickListener(
                this,
                recyclerVideos,
                new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TodaRequisicao2.Item item = lstListaVideos.get(position);
                Intent intent = new Intent(VideosActivity.this,PlayerActivity.class);
                String texto = item.getID().getVideoId();
                intent.putExtra("idVideo",item.getID().getVideoId());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

        RecuperarVideos("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_youtube, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    public void RecuperarVideos(String pesquisa)
    {
        Retrofit retrofit = RetrofitConfig.getRetrofit();
        YouTubeService youTubeService = retrofit.create(YouTubeService.class);
        youTubeService.buscarVideos("snippet","date","100",
                YouTubeConfiguracao.CHAVE_API
                ,YouTubeConfiguracao.CANAL_ID,pesquisa)
                .enqueue(new Callback<TodaRequisicao2.ListaVideosResponse>() {
                    @Override
                    public void onResponse(Call<TodaRequisicao2.ListaVideosResponse> call, Response<TodaRequisicao2.ListaVideosResponse> response) {

                        TodaRequisicao2.ListaVideosResponse lista = response.body();
                        lstListaVideos = Arrays.asList(lista.getItems());
                        adapter.AtualizarLista(lstListaVideos);
                    }

                    @Override
                    public void onFailure(Call<TodaRequisicao2.ListaVideosResponse> call, Throwable t) {

                    }
                });


    }
}