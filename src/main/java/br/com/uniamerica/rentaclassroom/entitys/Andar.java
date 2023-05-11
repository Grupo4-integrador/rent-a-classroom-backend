package br.com.uniamerica.rentaclassroom.entitys;

public enum Andar {
  SUBSOLO(1, "Subsolo"),
  PRIMEIRO(2, "Primeiro"),
  SEGUNDO(3, "Segundo"),
  TERCEIRO(4, "Terceiro");

  public final int valorNum;
  public final String valor;

  private Andar(int valorNum, String valor){
    this.valorNum = valorNum;
    this.valor = valor;
  }
}
