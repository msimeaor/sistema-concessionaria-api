package io.github.msimeaor.sistemaconcessionariaapi.exceptions;

public class ModelNaoEncontrado extends RuntimeException{

  public ModelNaoEncontrado() {
  }

  public ModelNaoEncontrado(String message) {
    super(message);
  }

  public ModelNaoEncontrado(String message, Throwable cause) {
    super(message, cause);
  }

  public ModelNaoEncontrado(Throwable cause) {
    super(cause);
  }

  public ModelNaoEncontrado(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
