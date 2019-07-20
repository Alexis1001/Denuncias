package com.e.denuncias.Usuarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Credenciales {

    @SerializedName("username")
    @Expose
    String username;
    @SerializedName("password")
    @Expose
    String password;

    public Credenciales(String username,String password ){
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credenciales{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
