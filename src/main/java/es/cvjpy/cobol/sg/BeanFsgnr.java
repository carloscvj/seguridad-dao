package es.cvjpy.cobol.sg;

import java.math.BigDecimal;
import java.util.*;
import es.cvjpy.cobol.*;

public class BeanFsgnr implements Bsgnr {

  private final RegistroFsgnr campos;

  public BeanFsgnr(RegistroFsgnr campos) {
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
  public String getCla_nodo() {
    return campos.cla_nodo.get();
  }
  @Override
  public void setCla_nodo(String s) {
    campos.cla_nodo.set(s);
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
