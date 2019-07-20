package com.e.denuncias.Reportes;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.e.denuncias.Conexion;
import com.e.denuncias.R;
import com.e.denuncias.Usuarios.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainReportesActivity extends AppCompatActivity {
    ReporteServicio reporteServicio;
    ListView listViewDenuncias;//  list view
    List<Denuncia> denuncias=new ArrayList<>();// lista  denuncias
    String token;
    Button AddDenuncia,Salir;
    DenunciaLista DenunciatAdapter; // instancia perro
    int id_user=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reportes);
        ArrayList<String> Token_IdUsuario=getIntent().getStringArrayListExtra("token_IdUsuario");
        token=Token_IdUsuario.get(0);
        final int id_usuario=Integer.parseInt(Token_IdUsuario.get(1));
        id_user=Integer.parseInt(Token_IdUsuario.get(2));
        System.out.println("token "+token);
        System.out.println("id_usuario del super user "+id_usuario);
        System.out.println("id_usuario "+id_user);
        listViewDenuncias= (ListView) findViewById(R.id.listViewDenuncias);
        AddDenuncia=findViewById(R.id.AddDenuncia);
        Salir=findViewById(R.id.ButtonSalir);




        ListaReportes(token,id_user);

        AddDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainReportesActivity.this,MainActivityAgregarReportes.class);
                ArrayList<String> datos=new ArrayList<String>();
                datos.add(token);
                datos.add(String.valueOf(id_usuario));
                datos.add(String.valueOf(id_user));
                intent.putExtra("datos",datos);
                startActivity(intent);
            }
        });


        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainReportesActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        listViewDenuncias.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Denuncia reportes = denuncias.get(i);
                System.out.println("eligio el numero cuarto perro "+i);
                LlamarActualizarYBorrarDialogo(reportes.getId(),reportes.getTitulo(),reportes.getDescripcion(),reportes.getImagen(),
                        reportes.getDate_joined(),reportes.isDelete(),
                        reportes.getRe_usuario_id(),token,id_usuario,id_user);
            }
        });


    }


    public void ListaReportes(String token , final int id_user){
        reporteServicio=Conexion.getServiceRemoteReportes();
        Call<List<Denuncia>> call=reporteServicio.GetDenuncias(token);
        System.out.println("primero lista reportes ");
        call.enqueue(new Callback<List<Denuncia>>() {
            @Override
            public void onResponse(Call<List<Denuncia>> call, Response<List<Denuncia>> response) {
                System.out.println("segundo lista reportes  ");
                if(response.isSuccessful()){
                    System.out.println("tercero lista reportes "+response.body());
                    ArrayList<Denuncia> ListaDenuncias= (ArrayList<Denuncia>) response.body();
                    for(Denuncia reportes:ListaDenuncias){
                        if(id_user==reportes.getRe_usuario_id()){
                            denuncias.add(reportes);
                        }
                    }
                    //DenunciaLista DenunciatAdapter = new DenunciaLista(MainReportesActivity.this,denuncias);// modificando esto perro

                    DenunciatAdapter = new DenunciaLista(MainReportesActivity.this,denuncias);
                    listViewDenuncias.setAdapter(DenunciatAdapter);

                }
                else{
                    System.out.println("respuesta no sucessfull ");
                }

            }

            @Override
            public void onFailure(Call<List<Denuncia>> call, Throwable t) {

                System.out.println("Error no respuesta "+t +" call "+call);
            }
        });
    }


    public void LlamarActualizarYBorrarDialogo(final int id_denuncia, final String titulo, final String descripcion, final String imagen,
                                               final String date_joined, final boolean delete, final int re_usuario, final String token,
                                               final int id_usuario, final int id_user){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.activity_update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText ActualizarTitulo= (EditText) dialogView.findViewById(R.id.ActualizarTitulo);
        final EditText ActualizarDescripcion = (EditText) dialogView.findViewById(R.id.ActualizarDescripcion);
        final EditText ActualizarFecha= (EditText) dialogView.findViewById(R.id.ActualizarFecha);
        final ImageView ActualizarImagen=(ImageView)dialogView.findViewById(R.id.ActualizarImagen);
        final EditText ActualizarUrl= (EditText) dialogView.findViewById(R.id.ActualizarUrl);// agregado de mas para prueba


        ActualizarTitulo.setText(titulo);
        ActualizarDescripcion.setText(descripcion);
        ActualizarFecha.setText(date_joined);
        ActualizarUrl.setText(imagen);//agregado para prueba

        final Button BotonACtualizar = (Button) dialogView.findViewById(R.id.BotonActualizarDenuncias);
        final Button BotonEliminar = (Button) dialogView.findViewById(R.id.BotonBorrarDenuncias);

        dialogBuilder.setTitle(titulo);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        BotonACtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titulo = ActualizarTitulo.getText().toString().trim();
                String descripcion = ActualizarDescripcion.getText().toString().trim();
                String date_joined =ActualizarFecha.getText().toString().trim();
                String url=ActualizarUrl.getText().toString().trim();

                Denuncia denuncia =new Denuncia(titulo,descripcion,date_joined,delete,re_usuario);


                if (!TextUtils.isEmpty(titulo)) {
                    if (!TextUtils.isEmpty(descripcion)) {
                        if (!TextUtils.isEmpty(date_joined)) {
                             Peticiones peticiones=new Peticiones();
                             peticiones.ActualizarDenuncia(id_denuncia,token,id_usuario,id_user,denuncia,MainReportesActivity.this);
                             b.dismiss();

                        }
                    }
                }

            }


        });

        BotonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean borrar=true;
                Denuncia denuncia1 =new Denuncia(titulo,descripcion,date_joined,borrar,re_usuario);
                Peticiones peticiones=new Peticiones();
                peticiones.BorrarDenuncia(id_denuncia,token,id_usuario,id_user,denuncia1,MainReportesActivity.this);
                b.dismiss();
            }
        });


    }









}
