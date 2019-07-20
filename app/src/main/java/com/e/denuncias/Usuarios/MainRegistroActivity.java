package com.e.denuncias.Usuarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.denuncias.Conexion;
import com.e.denuncias.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRegistroActivity extends AppCompatActivity {

    EditText nombre_usuario,correo_usuario,contrasenia_usuario;
    Button crear_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro);
        setTitle("Registro De Usuarios");

        nombre_usuario=findViewById(R.id.nombre_usuario);
        correo_usuario=findViewById(R.id.correo_usuario);
        contrasenia_usuario=findViewById(R.id.contrasenia_usuario);
        crear_usuario=findViewById(R.id.crear_usuario);

        final String token=getIntent().getStringExtra("token");
        System.out.println("Token perro "+token);




        crear_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name_user=nombre_usuario.getText().toString();
                String correo_user= correo_usuario.getText().toString();
                String contrasenia_user=contrasenia_usuario.getText().toString();
                Usuario usuario=new Usuario(name_user,correo_user,contrasenia_user,false);
                if(name_user.length()>0&&correo_user.length()>0&&contrasenia_user.length()>0){
                    String correo=correo_user.toLowerCase();
                    try{
                        String[] email=correo.split("@");
                        String email_dominio="@"+email[1];
                        if(email_dominio.equals("@gmail.com")||email_dominio.equals("@hotmail.com")||email_dominio.equals("@outlook.com")){
                            ValidacionLogin validar=new ValidacionLogin();
                            System.out.println("usuario xxx xxxxx "+usuario);
                            validar.ValidarRegistro(correo_user,token,correo_usuario,MainRegistroActivity.this,usuario,nombre_usuario
                                    ,contrasenia_usuario);
                        }
                        else{
                            Toast.makeText(MainRegistroActivity.this, "ingrese el formato correcto para un email ", Toast.LENGTH_SHORT).show();
                        }
                    }catch(Exception e ){
                        Toast.makeText(MainRegistroActivity.this, "ingrese el formato correcto para un email ", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainRegistroActivity.this, "Asegurese de llenar todos los campos", Toast.LENGTH_SHORT).show();
                    nombre_usuario.setText("");
                    correo_usuario.setText("");
                    contrasenia_usuario.setText("");
                }

            }
        });


    }
}
