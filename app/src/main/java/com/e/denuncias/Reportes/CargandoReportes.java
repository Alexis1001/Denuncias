package com.e.denuncias.Reportes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.e.denuncias.R;

import java.util.ArrayList;

public class CargandoReportes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargando_reportes);

        ArrayList<String> Token_IdUsuario=getIntent().getStringArrayListExtra("token_IdUsuario");
        String token=Token_IdUsuario.get(0);
        final int id_usuario=Integer.parseInt(Token_IdUsuario.get(1));
        int id_user=Integer.parseInt(Token_IdUsuario.get(2));
        System.out.println("token carga reportes "+token);
        System.out.println("id_usuario del super user carga reportes "+id_usuario);
        System.out.println("id_usuario carga reportes "+id_user);



        ArrayList<String> datos=new ArrayList<String>();
        datos.add(token);
        datos.add(String.valueOf(id_usuario));
        datos.add(String.valueOf(id_user));

        Intent intent=new Intent(CargandoReportes.this,MainReportesActivity.class);
        intent.putExtra("token_IdUsuario",datos);
        startActivity(intent);


    }
}
