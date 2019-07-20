package com.e.denuncias.Usuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioServicio {

    @POST("login/")
    Call<User> login(@Body Credenciales credenciales);

    @GET("usuario_lista/")
    Call<List<Usuario>> getUsuarios(@Header("Authorization") String token);

    @GET("usuario_detalles/{id}")
    Call<Usuario> getByIdUsuarios(@Header("Authorization") String token,@Path("id") int id);

    @POST("usuario_lista/")
    Call<Usuario> AddUsuarios(@Header("Authorization") String token,@Body Usuario usuario);

    @PUT("usuario_detalles/{id}")
    Call<Usuario> updateUsuarios(@Header("Authorization") String token,@Path("id") int id, @Body Usuario usuario);

    @DELETE("usuarios_detalles/{id}")
    Call<Usuario> deleteUsuarios(@Path("id") int id);

}
