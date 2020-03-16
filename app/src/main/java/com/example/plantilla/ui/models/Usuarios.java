package com.example.plantilla.ui.models;

public class Usuarios {

    private String id;
    private String nombre;
    private String telefono;
    private String correo;
    private String password;
    private String date;
    private String time;
    private String tipoPersona;
    private String imageurl;
    private String empresa;
    private String curriculom;
    private String ubicacion;

    public Usuarios() {
    }

    public Usuarios(String id, String nombre, String telefono, String password, String date, String time, String tipoPersona, String imageurl, String empresa, String curriculom, String correo, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.password = password;
        this.date = date;
        this.time = time;
        this.tipoPersona = tipoPersona;
        this.imageurl = imageurl;
        this.empresa = empresa;
        this.curriculom = curriculom;
        this.correo = correo;
        this.ubicacion = ubicacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCurriculom() {
        return curriculom;
    }

    public void setCurriculom(String curriculom) {
        this.curriculom = curriculom;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
