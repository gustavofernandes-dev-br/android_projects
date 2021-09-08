package br.dev.gustavofernandes.organize.util.io;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesUniversal  {

    public void GravaValor(Context context,String chave, String valor)
    {
        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor sEditor = sPref.edit();
        sEditor.putString(chave,valor);
        sEditor.commit();
        //sEditor.apply(); async sem retorno bool
    }

    public String RetornarValor(Context context, String key)
    {
        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sPref.getString(key,"");
    }
}
