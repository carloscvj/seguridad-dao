package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;
import java.util.*;

public class FicheroFsgnr extends Fichero implements InterFsgnr {
  protected RegistroFsgnr record;
  private final BeanFsgnr beanfsgnr;
  public FicheroFsgnr() {
    super("FSGNR", "mnu/SG/FSGNR", "SSGNR");
    record = new RegistroFsgnr();
    setRegistro(record);
    beanfsgnr = new BeanFsgnr(record);
  }

  @Override
  public Bsgnr getBsgnr() {
    return beanfsgnr;
  }

}
