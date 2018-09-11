package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;
import java.math.BigDecimal;
import java.util.Date;

public class FSGNR extends FicheroFsgnr {
  public FSGNR() {
  }
  public FSGNR(ProcesoCobolPro enl) {
    this.setEnlace(enl);
  }

  public FSGNR(Object enl) {
    this((ProcesoCobolPro) enl);
   }

  public String getRSGNR() {
    return record.getAll();
  }

  public void setRSGNR(String todo) {
    record.setAll(todo);
  }

  public String getCLA() {
    return record.cla.getCobolText();
  }
  public void setCLA(String s) {
    record.cla.setCobolText(s);
  }
  public String getCLA_NODO() {
    return record.cla_nodo.get();
  }
  public void setCLA_NODO(String s) {
    record.cla_nodo.set(s);
  }
  public String getCLA_ROLE() {
    return record.cla_role.get();
  }
  public void setCLA_ROLE(String s) {
    record.cla_role.set(s);
  }

}
