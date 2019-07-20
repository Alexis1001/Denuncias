package com.e.denuncias.Usuarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("email")
    @Expose
    private String correo;

    @SerializedName("contrasenia")
    @Expose
    private String contrasenia;

    @SerializedName("delete")
    @Expose
    boolean delete;

    public Usuario(){
    }

    public Usuario(String nombre, String correo, String contrasena,boolean delete) {// Para Insertar
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia= contrasena;
        this.delete=delete;
    }
    public Usuario(int id,String nombre, String correo, String contrasena) { // Para obtener
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia= contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", delete=" + delete +
                '}';
    }
}
