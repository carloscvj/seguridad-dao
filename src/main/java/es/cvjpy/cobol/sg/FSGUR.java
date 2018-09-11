package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;
import java.math.BigDecimal;
import java.util.Date;

public class FSGUR extends FicheroFsgur {
  public FSGUR() {
  }
  public FSGUR(ProcesoCobolPro enl) {
    this.setEnlace(enl);
  }

  public FSGUR(Object enl) {
    this((ProcesoCobolPro) enl);
   }

  public String getRSGUR() {
    return record.getAll();
  }

  public void setRSGUR(String todo) {
    record.setAll(todo);
  }

  public String getCLA() {
    return record.cla.getCobolText();
  }
  public void setCLA(String s) {
    record.cla.setCobolText(s);
  }
  public String getCLA_USER() {
    return record.cla_user.get();
  }
  public void setCLA_USER(String s) {
    record.cla_user.set(s);
  }
  public String getCLA_ROLE() {
    return record.cla_role.get();
  }
  public void setCLA_ROLE(String s) {
    record.cla_role.set(s);
  }

}
