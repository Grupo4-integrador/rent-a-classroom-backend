package br.com.uniamerica.rentaclassroom.entitys;

public enum Situacao {
  DEFERIDO (3, "Deferido"),
  INDEFERIDO (3, "Indeferido"),
  EM_AVALIACAO (3, "Em avaliação");

  public final int valorNumerico;
  public final String valorString;

  private Situacao(int valorNumerico, String valorString){
    this.valorNumerico=valorNumerico;
    this.valorString=valorString;
  }
}