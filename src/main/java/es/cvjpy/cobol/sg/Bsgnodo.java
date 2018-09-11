package es.cvjpy.cobol.sg;

import java.math.*;
import java.util.*;
import es.cvjpy.cobol.*;

public interface Bsgnodo extends java.io.Serializable {

  public void initAll();
  public Registrable getRegistro();

  public String getCla();
  public void setCla(String s);

}
