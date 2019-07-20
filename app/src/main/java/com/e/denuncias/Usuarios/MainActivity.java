package com.e.denuncias.Usuarios;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.e.denuncias.Conexion;
import com.e.denuncias.R;
import com.e.denuncias.Reportes.MainActivityAgregarReportes;
import com.e.denuncias.Reportes.MainReportesActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    UsuarioServicio usuarioServicio;
    EditText contrasenia;
    EditText correo;
    Button IniciarSesion;
    TextView Registrarse;
    ArrayList<Usuario> lista=new ArrayList<Usuario>();
    String Tokenizado="";
    int id_usuario=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contrasenia=findViewById(R.id.contrasenia);
        correo=findViewById(R.id.correo);
        IniciarSesion=findViewById(R.id.IniciarSesion);
        Registrarse=findViewById(R.id.Registrarse);

        Credenciales credenciales=new Credenciales("admin","admin");
        ObtenerToken(credenciales);


        IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(contrasenia.getText().toString().isEmpty()&&correo.getText().toString().isEmpty()){
                    contrasenia.setError("Campo necesario");
                    correo.setError("Campo necesario");
                }

                if(!contrasenia.getText().toString().isEmpty()&&correo.getText().toString().isEmpty() ){
                    correo.setError("Campo necesario");
                }

                if(!correo.getText().toString().isEmpty()&&contrasenia.getText().toString().isEmpty()){
                    contrasenia.setError("Campo necesario");
                }

                if(!contrasenia.getText().toString().isEmpty()&&!correo.getText().toString().isEmpty()){
                    usuarioServicio=Conexion.getServiceRemoteUsuarios();
                    String email=correo.getText().toString();
                    String password=contrasenia.getText().toString();
                    ValidacionLogin validacion=new ValidacionLogin();
                    boolean correo_correcto=validacion.getValidarCorreo(email);
                    //System.out.println("correo validador "+correo_correcto);

                    if(correo_correcto==false){
                        Toast.makeText(MainActivity.this, "Formato incorrecto en el correo", Toast.LENGTH_SHORT).show();
                    }

                    if(correo_correcto==true){
                        validacion.ValidarLogin(Tokenizado,email,password,MainActivity.this,id_usuario,correo,contrasenia);
                    }

                }

            }
        });

        Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent =new Intent(MainActivity.this,MainRegistroActivity.class);
               intent.putExtra("token",Tokenizado);
               startActivity(intent);
            }
        });
    }

    public void ObtenerToken(Credenciales credenciales){
        usuarioServicio= Conexion.getServiceRemoteUsuarios();
        Call<User> call=usuarioServicio.login(credenciales);
        System.out.println("pasa por la llamada call ");

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("entro en el metodo response  "+response.body().getToken());

                if(response.isSuccessful()){
                   // System.out.println("respuesta Succesfull "+response.body().getToken());
                    Tokenizado="Token "+response.body().getToken();
                    id_usuario=response.body().getUser_id();

                    System.out.println("TOKENIZADO "+Tokenizado);
                    System.out.println("Id_Usuario "+id_usuario);
                }

                else {
                    System.out.println("respuesta no Sucessfull ");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("error no respuesta "+t);
            }
        });

    }












}
