package io.github.msimeaor.sistemaconcessionariaapi.domain.enums;

public enum Uf {

  AC("Acre"), AL("Alagoas"), AP("Amapa"), AM("Amazonas"), BA("Bahia"), CE("Ceara"), DF("Distrito Federal"),
  ES("Espirito Santo"), GO("Goias"), MA("Maranhao"), MT("Mato Grosso"), MS("Mato Grosso do Sul"),
  MG("Minas Gerais"), PA("Para"), PB("Paraiba"), PR("Parana"), PE("Pernambuco"), PI("Piaui"),
  RJ("Rio de Janeiro"), RN("Rio Grande do Norte"), RS("Rio Grande do Sul"), RO("Rondonia"), RR("Roraima"),
  SC("Santa Catarina"), SP("Sao Paulo"), SE("Segipe"), TO("Tocantins");

  private final String uf;

  Uf(String uf) {
    this.uf = uf;
  }

  public String getUf() {
    return this.uf;
  }

}
