package br.dev.gustavofernandes.organize.activity;

import android.app.Activity;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import br.dev.gustavofernandes.organize.PrincipalActivity;
import br.dev.gustavofernandes.organize.R;
import br.dev.gustavofernandes.organize.model.Usuario;
import br.dev.gustavofernandes.organize.services.firebase.FirebaseService;
import br.dev.gustavofernandes.organize.util.Ref;
import br.dev.gustavofernandes.organize.util.Variaveis;
import br.dev.gustavofernandes.organize.util.io.SharedPreferencesUniversal;


public class PrincipalLoginActivity extends AppCompatActivity {

    Button btnEntrar , btnCadastrar;
    EditText txtEmail, txtSenha;
    Switch swExibirSenha;
    TextView txtAlerta;

    private GoogleSignInClient mSignInClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_login);
        CarregarControles();
        ConfigurarEventos();

        SharedPreferencesUniversal sShared = new SharedPreferencesUniversal();
        txtEmail.setText(sShared.RetornarValor(this, Variaveis.SharedGlobais.email));

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("703502307712-ik0ki6anj54u6ncvtj15lsso6a35h7cs.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser =  FirebaseService.getAutenticacao().getCurrentUser();
       // updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {

        if (user != null) {
            //mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
            //mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            SharedPreferencesUniversal sShared = new SharedPreferencesUniversal();
            sShared.GravaValor(getApplicationContext(), Variaveis.SharedGlobais.email,user.getEmail());
            startActivity(new Intent(PrincipalLoginActivity.this, PrincipalActivity.class));
            finish();

        } else {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!txtEmail.getText().toString().trim().equals(""))
            txtSenha.requestFocus();
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

    public void FazerLoginGoogle(View view)
    {
        Task<GoogleSignInAccount> task = mSignInClient.silentSignIn();
        if (task.isSuccessful()) {
            // There's an immediate result available
            GoogleSignInAccount account = task.getResult();
            if (account != null) {
                //setupClientFromAccount(activity, account);
            }
        }
        else
        {
            startActivityForResult(mSignInClient.getSignInIntent(), RC_SIGN_IN);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

                //handleSignInResult(this, resultData);
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                // Google Sign In failed, update UI appropriately
               // Log.w(TAG, "Google sign in failed", e);
            }



        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        FirebaseService.getAutenticacao().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(getApplicationContext(),"Logou", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = FirebaseService.getAutenticacao().getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                           // Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
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
                       // FirebaseUser currentUser =  FirebaseService.getAutenticacao().getCurrentUser();
                        updateUI(task.getResult().getUser());
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