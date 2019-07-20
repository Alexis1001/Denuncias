package com.e.denuncias.Reportes;

import com.e.denuncias.Usuarios.Credenciales;
import com.e.denuncias.Usuarios.User;
import com.e.denuncias.Usuarios.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReporteServicio {

    @GET("denuncia_lista/")
    Call<List<Denuncia>> GetDenuncias(@Header("Authorization") String token);

    @POST("denuncia_lista/")
    Call<Denuncia> AddDenuncia(@Header("Authorization") String token,@Body Denuncia denuncia);

    @GET("denuncia_detalles/{id}")
    Call<Denuncia> GetByIdDenuncia(@Header("Authorization") String token,@Path("id") int id);

    @PUT("denuncia_detalles/{id}")
    Call<Denuncia> UpdateDenuncia(@Header("Authorization") String token,@Path("id") int id, @Body Denuncia denuncia);

    @DELETE("denuncia_detalles/{id}")
    Call<Denuncia> DeleteDenuncia(@Path("id") int id);

}
