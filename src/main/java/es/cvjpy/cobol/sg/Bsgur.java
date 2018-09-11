package es.cvjpy.cobol.sg;

import java.math.*;
import java.util.*;
import es.cvjpy.cobol.*;

public interface Bsgur extends java.io.Serializable {

  public void initAll();
  public Registrable getRegistro();

  public String getCla();
  public void setCla(String s);
  public String getCla_user();
  public void setCla_user(String s);
  public String getCla_role();
  public void setCla_role(String s);

}
