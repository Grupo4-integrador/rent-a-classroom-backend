package br.com.uniamerica.rentaclassroom.entitys;

public enum Periodo {
  MANHA (1, "Manhã"),
  TARDE (2, "Tarde"),
  NOITE (3, "Noite");

  public final int valorNumerico;
  public final String valorString;

  private Periodo(int valorNumerico, String valorString){
    this.valorNumerico=valorNumerico;
    this.valorString=valorString;
  }
}
