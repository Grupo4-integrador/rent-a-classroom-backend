package br.com.uniamerica.rentaclassroom.entitys;

public enum RoleUsuario {
    USUARIO (1, "Usuario"),
    ADMIN (2, "Admin");

    public final int valorNumerico;
    public final String valorString;

    private RoleUsuario(int valorNumerico, String valorString){
        this.valorNumerico=valorNumerico;
        this.valorString=valorString;
    }
}
