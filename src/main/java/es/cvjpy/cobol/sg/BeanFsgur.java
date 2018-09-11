package es.cvjpy.cobol.sg;

import java.math.BigDecimal;
import java.util.*;
import es.cvjpy.cobol.*;

public class BeanFsgur implements Bsgur {

  private final RegistroFsgur campos;

  public BeanFsgur(RegistroFsgur campos) {
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
    return campos.cla.getCobolText();
  }
  @Override
  public void setCla(String s) {
    campos.cla.setCobolText(s);
  }
  @Override
  public String getCla_user() {
    return campos.cla_user.get();
  }
  @Override
  public void setCla_user(String s) {
    campos.cla_user.set(s);
  }
  @Override
  public String getCla_role() {
    return campos.cla_role.get();
  }
  @Override
  public void setCla_role(String s) {
    campos.cla_role.set(s);
  }

}
