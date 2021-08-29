package br.dev.gustavofernandes.testesasynceweb.retrofit;
import java.util.List;

import br.dev.gustavofernandes.testesasynceweb.retrofit.model.CEP;
import br.dev.gustavofernandes.testesasynceweb.retrofit.model.Foto;
import br.dev.gustavofernandes.testesasynceweb.retrofit.model.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataService {
    @GET("{cep}/json")
    Call<CEP> BuscarCep(@Path("cep") String cep);

    @GET("photos")
    Call<List<Foto>> BuscarFotos();

    @GET("posts")
    Call<List<Post>> BuscarPosts();

    @POST("teste")
    Call<POST> Postar(@Body Post post);

    @PUT("teste/{id}")
    Call<POST> Atualizar(@Path("id") String id, @Body Post post);

    @DELETE("teste/{id}")
    Call<Void> apagar(@Path("id") String id);

    @FormUrlEncoded
    @POST("teste")
    Call<POST> PostarUrlParametros(
            @Field("usuario") String usuario,
            @Field("senha") String senha);
}
