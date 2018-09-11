package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;

public final class RegistroFsgnodo extends Registro {

  public Texto cla;

  public Key key_cla;

  public RegistroFsgnodo() {
    super("registrofsgnodo", 128);
    crearcampos();
    crearclaves();
    initAll();
  }

  protected void crearcampos() {
    cla = new Texto(this, "cla", 0, 64);
    add(cla);
  }

  protected void crearclaves() {
    key_cla = new Key("cla");
    key_cla.add(getCampo("cla"));
    setKey(key_cla);
  
  }

}
