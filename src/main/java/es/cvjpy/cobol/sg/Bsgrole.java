package es.cvjpy.cobol.sg;

import java.math.*;
import java.util.*;
import es.cvjpy.cobol.*;

public interface Bsgrole extends java.io.Serializable {

  public void initAll();
  public Registrable getRegistro();

  public String getCla();
  public void setCla(String s);
  public String getDes();
  public void setDes(String s);

}
