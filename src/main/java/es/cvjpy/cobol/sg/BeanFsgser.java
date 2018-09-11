package es.cvjpy.cobol.sg;

import java.math.BigDecimal;
import java.util.*;
import es.cvjpy.cobol.*;

public class BeanFsgser implements Bsgser {

  private final RegistroFsgser campos;

  public BeanFsgser(RegistroFsgser campos) {
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
  @Override
  public String getDes() {
    return campos.des.get();
  }
  @Override
  public void setDes(String s) {
    campos.des.set(s);
  }
  @Override
  public String getPass() {
    return campos.pass.get();
  }
  @Override
  public void setPass(String s) {
    campos.pass.set(s);
  }

}
