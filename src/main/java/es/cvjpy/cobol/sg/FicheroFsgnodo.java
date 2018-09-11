package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;
import java.util.*;

public class FicheroFsgnodo extends Fichero implements InterFsgnodo {
  protected RegistroFsgnodo record;
  private final BeanFsgnodo beanfsgnodo;
  public FicheroFsgnodo() {
    super("FSGNODO", "mnu/SG/FSGNODO", "SSGNODO");
    record = new RegistroFsgnodo();
    setRegistro(record);
    beanfsgnodo = new BeanFsgnodo(record);
  }

  @Override
  public Bsgnodo getBsgnodo() {
    return beanfsgnodo;
  }

}
