package es.cvjpy.cobol.sg;

import java.math.BigDecimal;
import java.util.*;
import es.cvjpy.cobol.*;

public class BeanFsgnodo implements Bsgnodo {

  private final RegistroFsgnodo campos;

  public BeanFsgnodo(RegistroFsgnodo campos) {
    this.campos = campos;
  }

  @Override
  public Registro getRegistro() {
    return campos;
  }

  @Override
  public void initAll() {
    campos.initAll();
  }

  @Override
  public String getCla() {
    return campos.cla.get();
  }
  @Override
  public void setCla(String s) {
    campos.cla.set(s);
  }

}
