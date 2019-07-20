package com.e.denuncias.Reportes;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.e.denuncias.Conexion;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Peticiones {

    ReporteServicio reporteServicio;

    public Peticiones(){ }


    public void AgregarDenuncia(String token,Denuncia denuncia){
        reporteServicio=Conexion.getServiceRemoteReportes();
        System.out.println("denuncia "+denuncia+" token "+token);
        reporteServicio=Conexion.getServiceRemoteReportes();
        Call<Denuncia> call =reporteServicio.AddDenuncia(token,denuncia);
        System.out.println("antes del call denuncia "+denuncia+" token "+token);

        call.enqueue(new Callback<Denuncia>() {
            @Override
            public void onResponse(Call<Denuncia> call, Response<Denuncia> response) {

                if(response.isSuccessful()) {
                    System.out.println("respuesta " + response.body());
                }
                else{
                    System.out.println("no  respuesta " + response.body());
                }
            }

            @Override
            public void onFailure(Call<Denuncia> call, Throwable t) {

            }
        });


    }

    public void ActualizarDenuncia(int id_denuncia,final String token,final int id_usuario,final int id_user,Denuncia denuncia,final Context context){
        reporteServicio=Conexion.getServiceRemoteReportes();
        Call<Denuncia> call=reporteServicio.UpdateDenuncia(token,id_denuncia,denuncia);

        call.enqueue(new Callback<Denuncia>() {
            @Override
            public void onResponse(Call<Denuncia> call, Response<Denuncia> response) {

                if(response.isSuccessful()){
                    System.out.println("denuncia actualizada "+response.body());
                    Intent intent=new Intent(context,CargandoReportes.class);
                    ArrayList<String> datos=new ArrayList<String>();
                    datos.add(token);
                    datos.add(String.valueOf(id_usuario));
                    datos.add(String.valueOf(id_user));
                    intent.putExtra("token_IdUsuario",datos);///
                    context.startActivity(intent);//////
                }
                else{
                    System.out.println("de nuncia no actualizada");
                }

            }
            @Override
            public void onFailure(Call<Denuncia> call, Throwable t) {
                System.out.println("Error "+t+" llamada "+call);
            }
        });

    }

    public void BorrarDenuncia(int id_denuncia,final String token,final int id_usuario,final int id_user,Denuncia denuncia,final Context context){
        reporteServicio=Conexion.getServiceRemoteReportes();
        Call<Denuncia> call=reporteServicio.UpdateDenuncia(token,id_denuncia,denuncia);

        call.enqueue(new Callback<Denuncia>() {
            @Override
            public void onResponse(Call<Denuncia> call, Response<Denuncia> response) {
                if(response.isSuccessful()){
                    System.out.println("denuncia borrada "+response.body());
                    Intent intent=new Intent(context,CargandoReportes.class);
                    ArrayList<String> datos=new ArrayList<String>();
                    datos.add(token);
                    datos.add(String.valueOf(id_usuario));
                    datos.add(String.valueOf(id_user));
                    intent.putExtra("token_IdUsuario",datos);
                    context.startActivity(intent);
                }
                else{
                    System.out.println("de nuncia no borrada");
                }

            }

            @Override
            public void onFailure(Call<Denuncia> call, Throwable t) {
                System.out.println("Error "+t+" llamada "+call);
            }
        });

    }



}
