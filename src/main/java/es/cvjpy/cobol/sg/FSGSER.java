package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;
import java.math.BigDecimal;
import java.util.Date;

public class FSGSER extends FicheroFsgser {
  public FSGSER() {
  }
  public FSGSER(ProcesoCobolPro enl) {
    this.setEnlace(enl);
  }

  public FSGSER(Object enl) {
    this((ProcesoCobolPro) enl);
   }

  public String getRSGSER() {
    return record.getAll();
  }

  public void setRSGSER(String todo) {
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
  public String getPASS() {
    return record.pass.get();
  }
  public void setPASS(String s) {
    record.pass.set(s);
  }

}
