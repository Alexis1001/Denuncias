package com.e.denuncias.Reportes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Denuncia {

    @SerializedName("id")
    @Expose
    private  int  id;

    @SerializedName("titulo")
    @Expose
    private  String titulo;

    @SerializedName("descripcion")
    @Expose
    private  String descripcion;

    @SerializedName("imagen")
    @Expose
    private  String imagen;

    @SerializedName("date_joined")
    @Expose
    private  String date_joined;

    @SerializedName("delete")
    @Expose
    private  boolean delete;

    @SerializedName("re_usuario")
    @Expose
    private  int re_usuario_id;

    public Denuncia(){

    }

    public Denuncia(String titulo, String descripcion,String date_joined, boolean delete, int re_usuario_id) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.date_joined = date_joined;
        this.delete = delete;
        this.re_usuario_id = re_usuario_id;
    }


    public Denuncia(String titulo, String descripcion, String imagen, String date_joined, boolean delete, int re_usuario_id) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.date_joined = date_joined;
        this.delete = delete;
        this.re_usuario_id = re_usuario_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public int getRe_usuario_id() {
        return re_usuario_id;
    }

    public void setRe_usuario_id(int re_usuario_id) {
        this.re_usuario_id = re_usuario_id;
    }

    @Override
    public String toString() {
        return "Denuncia{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", date_joined='" + date_joined + '\'' +
                ", delete=" + delete +
                ", re_usuario_id=" + re_usuario_id +
                '}';
    }
}
