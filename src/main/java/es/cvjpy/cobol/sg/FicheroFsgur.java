package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;
import java.util.*;

public class FicheroFsgur extends Fichero implements InterFsgur {
  protected RegistroFsgur record;
  private final BeanFsgur beanfsgur;
  public FicheroFsgur() {
    super("FSGUR", "mnu/SG/FSGUR", "SSGUR");
    record = new RegistroFsgur();
    setRegistro(record);
    beanfsgur = new BeanFsgur(record);
  }

  @Override
  public Bsgur getBsgur() {
    return beanfsgur;
  }

}
