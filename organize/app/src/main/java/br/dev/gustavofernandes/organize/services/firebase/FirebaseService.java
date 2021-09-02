package br.dev.gustavofernandes.organize.services.firebase;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseService {

    public static FirebaseAuth auth;

    public static FirebaseAuth getAutenticacao()
    {
        if (auth == null)
            auth = FirebaseAuth.getInstance();
        return auth;
    }

}
