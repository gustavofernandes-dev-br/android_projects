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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.security.Principal;

import br.dev.gustavofernandes.organize.PrincipalActivity;
import br.dev.gustavofernandes.organize.R;
import br.dev.gustavofernandes.organize.model.Usuario;
import br.dev.gustavofernandes.organize.services.firebase.FirebaseService;
import br.dev.gustavofernandes.organize.util.Ref;

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
        txtAlerta.setVisibility(View.INVISIBLE);
        Usuario usuario = new Usuario();
        usuario.setSenha(txtSenha.getText().toString().trim());
        usuario.setEmail(txtEmail.getText().toString().trim());
        Ref<String> ref = new Ref("");
        if(!usuario.ValidarLogin(ref))
        {
            txtAlerta.setText(ref.get());
            txtAlerta.setVisibility(View.VISIBLE);
            return;
        }

        FirebaseAuth auth = FirebaseService.getAutenticacao();
        auth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(PrincipalLoginActivity.this, PrincipalActivity.class));
                        finish();
                    }
                    else
                    {
                        try {
                            throw task.getException();
                        }
                        catch (FirebaseAuthInvalidUserException e)
                        {
                            ref.set("Usuário não existe");
                        }
                        catch (FirebaseAuthInvalidCredentialsException e)
                        {
                            ref.set("Usuário ou senha inválidos");
                        }
                        catch (Exception e)
                        {
                            ref.set("Usuário não existe");
                        }
                        txtAlerta.setText(ref.get());
                        txtAlerta.setVisibility(View.VISIBLE);

                    }
                }
            });

    }
}