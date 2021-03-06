package br.dev.gustavofernandes.organize.services.api;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Struct;

import br.dev.gustavofernandes.organize.model.Usuario;
import br.dev.gustavofernandes.organize.services.firebase.FirebaseService;
import br.dev.gustavofernandes.organize.util.Ref;

public class UsuarioService {

    Context _Context;

    public UsuarioService(Context context)
    {
        this._Context = context;
    }

    public boolean FazerLogin(Usuario usuario)
    {

        return  true;
    }

    public boolean  CadastrarUsuario(Usuario usuario, Ref<String> mensagem)
    {
        final Boolean[] sRetorno = new Boolean[1];

        if(!usuario.ValidarCadastro(mensagem))
            return false;

        try {

               FirebaseAuth auth = FirebaseService.getAutenticacao();
               auth.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(_Context, "Usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                            FirebaseUser user =  FirebaseService.getAutenticacao().getCurrentUser();
                            sRetorno[0] = true;
                            // updateUI(user);

                        }
                        else
                        {
                            try {
                                throw task.getException();
                            }
                            catch (FirebaseAuthWeakPasswordException e)
                            {
                                mensagem.set("Digite uma senha mais forte.");
                            }
                            catch (FirebaseAuthInvalidCredentialsException e)
                            {
                                mensagem.set("Digite um email v??lido.");
                            }
                            catch (FirebaseAuthUserCollisionException e)
                            {
                                mensagem.set("J?? existe um usu??rio com esse nome");
                            }
                            catch (Exception e)
                            {
                                mensagem.set(e.getMessage());
                            }
                            sRetorno[0] = false;
                        }
                    }
                });
                return sRetorno[0];
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return true;
    }

    public Usuario RetornarUsuarioLogado()
    {
        Usuario sUsuario = new Usuario();
        return sUsuario;
    }
    public boolean logoff()
    {
        return  true;
    }



}
