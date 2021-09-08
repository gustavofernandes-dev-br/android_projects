package br.dev.gustavofernandes.organize.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import br.dev.gustavofernandes.organize.R;
import br.dev.gustavofernandes.organize.model.Usuario;
import br.dev.gustavofernandes.organize.services.api.UsuarioService;
import br.dev.gustavofernandes.organize.services.firebase.FirebaseService;
import br.dev.gustavofernandes.organize.util.Ref;
import br.dev.gustavofernandes.organize.util.Variaveis;
import br.dev.gustavofernandes.organize.util.io.SharedPreferencesUniversal;

public class CadastroUsuarioActivity extends AppCompatActivity {

    Button btnCadastrar;
    TextInputEditText txtNome, txtSenha, txtEmail, txtConfirmarSenha;
    Switch swExibirSenha;
    TextView txtAlerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
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
                    txtConfirmarSenha.setInputType(InputType.TYPE_CLASS_TEXT );
                }
                else
                {
                    txtSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    txtConfirmarSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }
            }
        });
    }

    private void CarregarControles() {
        txtEmail = findViewById(R.id.cadastro_usuario_txt_email);
        txtSenha = findViewById(R.id.cadastro_usuario_txt_senha);
        txtNome = findViewById(R.id.cadastro_usuario_txt_nome);
        txtConfirmarSenha = findViewById(R.id.cadastro_usuario_txt_confirmar_senha);
        swExibirSenha = findViewById(R.id.cadastro_usuario_sw_exibir_senha);
        btnCadastrar = findViewById(R.id.cadastro_usuario_btn_cadastrar);
        txtAlerta = findViewById(R.id.cadastro_usuario_txt_alerta);

    }

    public void Cadastrar(View view)
    {
        txtAlerta.setVisibility(View.INVISIBLE);
        Usuario usuario = new Usuario();
        usuario.setEmail(txtEmail.getText().toString().trim());
        usuario.setNome(txtNome.getText().toString().trim());
        usuario.setSenha(txtSenha.getText().toString().trim());
        usuario.setSenhaConfirmar(txtConfirmarSenha.getText().toString().trim());
        Ref<String> sRef = new Ref<String>("");
        if(!usuario.ValidarCadastro(sRef))
        {
            txtAlerta.setText(sRef.get());
            txtAlerta.setVisibility(View.VISIBLE);
            return;
        }

        try {
            FirebaseAuth auth = FirebaseService.getAutenticacao();
            auth.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "Usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                            FirebaseUser user =  FirebaseService.getAutenticacao().getCurrentUser();
                            SharedPreferencesUniversal sShared = new SharedPreferencesUniversal();
                            sShared.GravaValor(getApplicationContext(), Variaveis.SharedGlobais.email,usuario.getEmail());
                            finish();
                        }
                        else
                        {
                            try {
                                throw task.getException();
                            }
                            catch (FirebaseAuthWeakPasswordException e)
                            {
                                sRef.set("Digite uma senha mais forte.");
                            }
                            catch (FirebaseAuthInvalidCredentialsException e)
                            {
                                sRef.set("Digite um email válido.");
                            }
                            catch (FirebaseAuthUserCollisionException e)
                            {
                                sRef.set("Já existe um usuário com esse nome");
                            }
                            catch (Exception e)
                            {
                                sRef.set(e.getMessage());
                            }
                            txtAlerta.setText(sRef.get());
                            txtAlerta.setVisibility(View.VISIBLE);
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