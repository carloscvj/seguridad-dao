package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;
import java.math.BigDecimal;
import java.util.Date;

public class FSGNODO extends FicheroFsgnodo {
  public FSGNODO() {
  }
  public FSGNODO(ProcesoCobolPro enl) {
    this.setEnlace(enl);
  }

  public FSGNODO(Object enl) {
    this((ProcesoCobolPro) enl);
   }

  public String getRSGNODO() {
    return record.getAll();
  }

  public void setRSGNODO(String todo) {
    record.setAll(todo);
  }

  public String getCLA() {
    return record.cla.get();
  }
  public void setCLA(String s) {
    record.cla.set(s);
  }

}
