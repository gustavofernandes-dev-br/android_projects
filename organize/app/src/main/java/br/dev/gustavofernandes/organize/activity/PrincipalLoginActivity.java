package br.dev.gustavofernandes.organize.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.Principal;

import br.dev.gustavofernandes.organize.PrincipalActivity;
import br.dev.gustavofernandes.organize.R;
import br.dev.gustavofernandes.organize.services.firebase.FirebaseService;

public class PrincipalLoginActivity extends AppCompatActivity {

    Button btnEntrar , btnCadastrar;
    EditText txtEmail, txtSenha;
    Switch swExibirSenha;
    TextView txtAlerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_login);
        CarregarControles();
        ConfigurarEventos();
    }

    private void ConfigurarEventos() {
        swExibirSenha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(swExibirSenha.isChecked())
                {
                    txtSenha.setInputType(InputType.TYPE_CLASS_TEXT );
                }
                else
                {
                    txtSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    private void CarregarControles() {
        btnCadastrar = findViewById(R.id.login_btn_cadastrar);
        btnEntrar = findViewById(R.id.login_btn_entrar);
        txtEmail = findViewById(R.id.login_txt_email);
        txtSenha = findViewById(R.id.login_txt_senha);
        swExibirSenha = findViewById(R.id.login_sw_exibir_senha);
        txtAlerta = findViewById(R.id.login_txt_alerta);
    }

    public void NovoCadastro(View view)
    {
        startActivity(new Intent(this, CadastroUsuarioActivity.class));
    }

    public void FazerLogin(View view)
    {
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }
}