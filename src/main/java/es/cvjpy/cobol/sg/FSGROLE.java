package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;
import java.math.BigDecimal;
import java.util.Date;

public class FSGROLE extends FicheroFsgrole {
  public FSGROLE() {
  }
  public FSGROLE(ProcesoCobolPro enl) {
    this.setEnlace(enl);
  }

  public FSGROLE(Object enl) {
    this((ProcesoCobolPro) enl);
   }

  public String getRSGROLE() {
    return record.getAll();
  }

  public void setRSGROLE(String todo) {
    record.setAll(todo);
  }

  public String getCLA() {
    return record.cla.get();
  }
  public void setCLA(String s) {
    record.cla.set(s);
  }
  public String getDES() {
    return record.des.get();
  }
  public void setDES(String s) {
    record.des.set(s);
  }

}
