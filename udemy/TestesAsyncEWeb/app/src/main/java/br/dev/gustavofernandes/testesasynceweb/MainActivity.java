package br.dev.gustavofernandes.testesasynceweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.dev.gustavofernandes.testesasynceweb.retrofit.Api;
import br.dev.gustavofernandes.testesasynceweb.retrofit.DataService;
import br.dev.gustavofernandes.testesasynceweb.retrofit.model.CEP;
import br.dev.gustavofernandes.testesasynceweb.retrofit.model.Foto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MaterialButton btnCep;
    MaterialButton btnProgress;
    TextInputEditText txtCep;
    MaterialTextView lblCep;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCep = findViewById(R.id.btnCep);
        txtCep = findViewById(R.id.txtCep);
        lblCep = findViewById(R.id.lblResutado);
        btnProgress = findViewById(R.id.btnProgress);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        progressBar.setMax(10);

    }

    public void BuscarCep(View view){
        String sUrl = "https://viacep.com.br/ws/" + txtCep.getText().toString().trim() + "/json/";
        MyTaskWeb task = new MyTaskWeb();
        task.execute(sUrl);
    }
    public void Progress(View view){
        MyTaskProgress task = new MyTaskProgress();
        task.execute(10);
    }

    class MyTaskWeb extends AsyncTask<String,String,String>{

        InputStreamReader reader = null;
        @Override
        protected String doInBackground(String... strings) {
            String sUrlText = strings[0];
            StringBuffer sBuffer = new StringBuffer();
            try {
                URL sUrl = new URL(sUrlText);
                HttpURLConnection sConection = (HttpURLConnection)sUrl.openConnection();
                InputStream inputStream = sConection.getInputStream();
                reader = new InputStreamReader(inputStream);
                BufferedReader buffer = new BufferedReader(reader);
                String linha = null;

                while ((linha = buffer.readLine()) != null)
                {
                    sBuffer.append(linha);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return sBuffer.toString();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String logradouro = "";
            try {
                JSONObject sJson = new JSONObject(s);
                logradouro = sJson.getString("logradouro");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            lblCep.setText(logradouro);
        }

    }

    public void BuscarCepRetroFit(View view)
    {
        DataService cepService = Api.retrofitCep.create(DataService.class);
        Call<CEP> call = cepService.BuscarCep(txtCep.getText().toString().trim());
        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if(response.isSuccessful())
                {
                    CEP cep = response.body();
                    lblCep.setText(cep.getLogradouro() + "-" + cep.getBairro() + "-" + cep.getLocalidade());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });

        cepService = Api.retrofitPlaceHolder.create(DataService.class);
        Call<List<Foto>> call2 = cepService.BuscarFotos();
        call2.enqueue(new Callback<List<Foto>>() {
            @Override
            public void onResponse(Call<List<Foto>> call, Response<List<Foto>> response) {
                int codigo = response.code();
                if(response.isSuccessful())
                {
                    List<Foto> sLista = new ArrayList<>();
                    sLista = response.body();
                    int i = 0;
                    for (Foto item:sLista
                         ) {
                        i++;
                        Log.i("item",item.getURL() + "-" + i);
                        lblCep.setText(item.getURL() + "-" + i);

                    }

                }
            }

            @Override
            public void onFailure(Call<List<Foto>> call, Throwable t) {

            }
        });


    }

    class MyTaskProgress extends AsyncTask<Integer, Integer,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            Integer numero = integers[0];
            for (int i = 0; i < numero; i++)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }

            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Integer i = values[0];
            progressBar.setProgress(i);


        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "Progress finalizado", Toast.LENGTH_SHORT).show();
        }
    }

}