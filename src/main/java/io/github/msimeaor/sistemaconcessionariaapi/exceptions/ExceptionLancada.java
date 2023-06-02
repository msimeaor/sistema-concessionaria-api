package io.github.msimeaor.sistemaconcessionariaapi.exceptions;

public class ExceptionLancada extends RuntimeException{

  public ExceptionLancada() {
  }

  public ExceptionLancada(String message) {
    super(message);
  }

  public ExceptionLancada(String message, Throwable cause) {
    super(message, cause);
  }

  public ExceptionLancada(Throwable cause) {
    super(cause);
  }

  public ExceptionLancada(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
