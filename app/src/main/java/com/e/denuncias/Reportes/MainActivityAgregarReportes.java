package com.e.denuncias.Reportes;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.e.denuncias.R;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivityAgregarReportes extends AppCompatActivity {

    Button ButtonAgregarReporte,ButtonTomarFoto;
    EditText Title,description,date;

    //nuevas variables
    ImageView imageFoto;
    Uri picUri;
    String urlImage;
    private final static int ALL_PERMISSIONS_RESULT = 107;
    private final static int IMAGE_RESULT = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_agregar_reportes);

        ButtonAgregarReporte = findViewById(R.id.ButtonAgregarReporte);
        ButtonTomarFoto=findViewById(R.id.ButtonTomarFoto);
        Title = findViewById(R.id.TituloReporte);
        description = findViewById(R.id.DescripcionReporte);
        date = findViewById(R.id.FechaReporte);

        ArrayList<String> Token_IdUsuario = getIntent().getStringArrayListExtra("datos");
        final String Token = Token_IdUsuario.get(0);
        int id_usuario = Integer.parseInt(Token_IdUsuario.get(1));
        int id_user = Integer.parseInt(Token_IdUsuario.get(2));

        System.out.println("token registr d ccc " + Token);
        System.out.println("id_usuario del super user d ccc " + id_usuario);
        System.out.println("id_usuario d ccc " + id_user);


        ButtonTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivityAgregarReportes.this, "Tomar foto ", Toast.LENGTH_LONG).show();


            }
        });

        ButtonAgregarReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = Title.getText().toString();
                String descripcion = description.getText().toString();
                String fecha = date.getText().toString();

                if (!TextUtils.isEmpty(titulo)) {
                    if (!TextUtils.isEmpty(descripcion)) {
                        if (!TextUtils.isEmpty(fecha)) {

                            Denuncia report=new Denuncia("Bibloteca","sillas en mal estado ","2019-07-16",false,1);
                            Peticiones peticiones=new Peticiones();
                            peticiones.AgregarDenuncia(Token,report);

                            Toast.makeText(MainActivityAgregarReportes.this, "Reporte Agregado ", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivityAgregarReportes.this, "Ingrese una fecha correcta ", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivityAgregarReportes.this, "Ingrese una descripcion", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(MainActivityAgregarReportes.this, "Ingrese un Titulo", Toast.LENGTH_LONG).show();
                }

            }
        });









    }
}
