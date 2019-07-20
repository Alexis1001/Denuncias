package com.e.denuncias;

import com.e.denuncias.Reportes.Denuncia;
import com.e.denuncias.Reportes.ReporteServicio;
import com.e.denuncias.Usuarios.UsuarioServicio;

public class Conexion {

    public static String API_URL = "http://192.168.0.8:8000/api/v1/";

    public static UsuarioServicio getServiceRemoteUsuarios(){

        return Cliente.getCliente(API_URL).create(UsuarioServicio.class);

    }

    public static ReporteServicio getServiceRemoteReportes(){

        return Cliente.getDenucia(API_URL).create(ReporteServicio.class);

    }





}
