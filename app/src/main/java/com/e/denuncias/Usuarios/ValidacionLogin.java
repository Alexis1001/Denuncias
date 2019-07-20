package com.e.denuncias.Usuarios;

import android.content.Context;
import android.content.Intent;
import android.media.session.MediaSession;
import android.widget.EditText;
import android.widget.Toast;

import com.e.denuncias.Conexion;
import com.e.denuncias.Reportes.MainReportesActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValidacionLogin {
    UsuarioServicio usuarioServicio;
    ArrayList<Usuario> lista=new ArrayList<Usuario>();

    public ValidacionLogin(){

    }

    public boolean getValidarCorreo(String email){
        boolean coincidencia=false;
        String correo= email.toLowerCase();

        try {
            String parates_del_correo[] = correo.split("@");
            String parte_apartir_arroba ="@"+parates_del_correo[1];

            if(parte_apartir_arroba.equals("@hotmail.com")||parte_apartir_arroba.equals("@gmail.com")
                    ||parte_apartir_arroba.equals("@outlook.com")){

                coincidencia=true;
            }

            else{
                System.out.println("formato erroneo "+parte_apartir_arroba);
                coincidencia=false;
            }

        }catch(Exception e){
            System.out.println("formato del correo incorrecto "+e);
            coincidencia=false;
        }

        return coincidencia;
    }

    public void ValidarLogin(final String Token, final String email, final String password, final Context context, final int id_usuario, final EditText
                             contrasenia, final EditText correo){
        usuarioServicio= Conexion.getServiceRemoteUsuarios();
        Call<List<Usuario>> call =usuarioServicio.getUsuarios(Token);
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                //System.out.println("Json de los usuarios "+response.body());
                int i=0;
                if(response.isSuccessful()){
                    //System.out.println("sucesfull json de los usuarios "+response.body());
                    lista= (ArrayList<Usuario>) response.body();
                    //String token=Token;
                    for(i=0;i<lista.size();i++){
                        String correo_user=lista.get(i).getCorreo();
                        String contrasenia_user=lista.get(i).getContrasenia();
                        
                        if(email.equals(correo_user)&&password.equals(contrasenia_user)){
                            int id_user=lista.get(i).getId();
                            Intent intent=new Intent(context, MainReportesActivity.class);
                            ArrayList<String> valores=new ArrayList<String>();
                            valores.add(Token);
                            valores.add(String.valueOf(id_usuario));
                            valores.add(String.valueOf(id_user));
                            intent.putExtra("token_IdUsuario",valores);
                            context.startActivity(intent);
                            contrasenia.setText("");
                            correo.setText("");
                            //lista.clear();
                            /*limpiamos todo el arreglo*/
                            break;
                        }
                        
                    }
                    System.out.println(" i "+i);
                    System.out.println("tamanio de la lista "+lista.size());

                    if(i==lista.size()){
                        Toast.makeText(context, "correo invalido o contraseña ", Toast.LENGTH_SHORT).show();
                        correo.setText("");
                        contrasenia.setText("");
                        //lista.clear();
                        /*limpiamos todo el arreglo*/
                    }

                }
                else{
                    System.out.println("no sucessfull");
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                System.out.println("no respuesta "+t);
                Toast.makeText(context, "Error en el servidor ", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void ValidarRegistro(final String email, final String token, final EditText CorreoUsuario, final Context context, final Usuario usuario,
                                final EditText NombreUsuario,final EditText ContraseniaUsuario){
        usuarioServicio= Conexion.getServiceRemoteUsuarios();
        Call<List<Usuario>> call=usuarioServicio.getUsuarios(token);

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                int i=0;
               System.out.println("si response Token "+token);
               System.out.println("response body "+response.body());
                if(response.isSuccessful()){
                    System.out.println("sucess full ");
                    lista=(ArrayList<Usuario>) response.body();
                    for(i=0;i<lista.size();i++){
                        String correo=lista.get(i).getCorreo();
                        if(email.equals(correo)){
                            CorreoUsuario.setText("");
                            Toast.makeText(context, "Correo ya existente ", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }

                    if(i==lista.size()){
                        System.out.println("usuario a gregar perro "+usuario);
                        AddUser(usuario,token,context);
                        NombreUsuario.setText("");
                        CorreoUsuario.setText("");
                        ContraseniaUsuario.setText("");
                        Toast.makeText(context, "Usuario agregado ", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    System.out.println("response no sucess full");
                }

            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                System.out.println("Error "+t);
                Toast.makeText(context, "servidor caido ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void AddUser(final Usuario usuario, final String token,final Context context){
        usuarioServicio= Conexion.getServiceRemoteUsuarios();
        Call<Usuario>call=usuarioServicio.AddUsuarios(token,usuario);
        System.out.println("despues del call ");
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                System.out.println("respuesta antes del sucessfull");
                if(response.isSuccessful()){
                    System.out.println("respuesta con sucessfull "+response.body());
                    Intent intent=new Intent(context,MainReportesActivity.class);
                    int id_usuario=response.body().getId();
                    ArrayList<String> valores=new ArrayList<String>();
                    valores.add(token);
                    valores.add("2");
                    valores.add(String.valueOf(id_usuario));
                    intent.putExtra("token_IdUsuario",valores);
                    context.startActivity(intent);
                    //XXXXXXXXXXXXXXXXXXXXXXXXxXXXXXXXXXXXXXXXXXXX
                }
                else{
                    System.out.println("respuesta no entro en el sucessfull");
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                System.out.println("respuesta fallida "+t);
            }
        });

    }




}



