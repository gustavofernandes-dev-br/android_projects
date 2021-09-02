package br.dev.gustavofernandes.organize.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_login);
        btnCadastrar = findViewById(R.id.login_btn_cadastrar);
        btnEntrar = findViewById(R.id.login_btn_entrar);
        txtEmail = findViewById(R.id.login_txt_email);
        txtSenha = findViewById(R.id.login_txt_senha);


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