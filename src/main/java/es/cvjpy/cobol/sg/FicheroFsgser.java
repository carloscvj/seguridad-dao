package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;
import java.util.*;

public class FicheroFsgser extends Fichero implements InterFsgser {
  protected RegistroFsgser record;
  private final BeanFsgser beanfsgser;
  public FicheroFsgser() {
    super("FSGSER", "mnu/SG/FSGSER", "SSGSER");
    record = new RegistroFsgser();
    setRegistro(record);
    beanfsgser = new BeanFsgser(record);
  }

  @Override
  public Bsgser getBsgser() {
    return beanfsgser;
  }

}
