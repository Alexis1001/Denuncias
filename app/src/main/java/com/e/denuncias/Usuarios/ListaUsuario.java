package com.e.denuncias.Usuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaUsuario {
    private ArrayList<Usuario> data;

    public ArrayList<Usuario> getData() {
        return data;
    }

    public void setData(ArrayList<Usuario> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ListaUsuario{" +
                "data=" + data +
                '}';
    }
}
