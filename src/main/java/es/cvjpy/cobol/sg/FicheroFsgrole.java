package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;
import java.util.*;

public class FicheroFsgrole extends Fichero implements InterFsgrole {
  protected RegistroFsgrole record;
  private final BeanFsgrole beanfsgrole;
  public FicheroFsgrole() {
    super("FSGROLE", "mnu/SG/FSGROLE", "SSGROLE");
    record = new RegistroFsgrole();
    setRegistro(record);
    beanfsgrole = new BeanFsgrole(record);
  }

  @Override
  public Bsgrole getBsgrole() {
    return beanfsgrole;
  }

}
