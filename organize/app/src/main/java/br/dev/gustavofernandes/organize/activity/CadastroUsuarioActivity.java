package br.dev.gustavofernandes.organize.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import br.dev.gustavofernandes.organize.R;
import br.dev.gustavofernandes.organize.services.firebase.FirebaseService;

public class CadastroUsuarioActivity extends AppCompatActivity {

    Button btnCadastrar;
    TextInputEditText txtNome, txtSenha, txtEmail, txtConfirmarSenha;
    Switch swExibirSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        CarregarControles();

    }

    private void CarregarControles() {
        txtEmail = findViewById(R.id.cadastro_usuario_txt_email);
        txtSenha = findViewById(R.id.cadastro_usuario_txt_senha);
        txtNome = findViewById(R.id.cadastro_usuario_txt_nome);
        txtConfirmarSenha = findViewById(R.id.cadastro_usuario_txt_confirmar_senha);
        swExibirSenha = findViewById(R.id.cadastro_usuario_sw_exibir_senha);
        btnCadastrar = findViewById(R.id.cadastro_usuario_btn_cadastrar);

    }

    public void Cadatrar(View view)
    {
        String sEmail = txtEmail.getText().toString().trim();
        String sSenha = txtSenha.getText().toString().trim();
        try {
            FirebaseService.getAutenticacao()
                    .createUserWithEmailAndPassword(sEmail,sSenha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                                FirebaseUser user =  FirebaseService.getAutenticacao().getCurrentUser();
                                // updateUI(user);
                            }
                        }
                    });

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}