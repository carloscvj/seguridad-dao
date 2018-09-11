package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;

public final class RegistroFsgrole extends Registro {

  public Texto cla;
  public Texto des;

  public Key key_cla;
  public Key key_des;

  public RegistroFsgrole() {
    super("registrofsgrole", 192);
    crearcampos();
    crearclaves();
    initAll();
  }

  protected void crearcampos() {
    cla = new Texto(this, "cla", 0, 64);
    add(cla);
    des = new Texto(this, "des", 64, 64);
    add(des);
  }

  protected void crearclaves() {
    key_cla = new Key("cla");
    key_cla.add(getCampo("cla"));
    setKey(key_cla);
    key_des = new Key("des");
    key_des.add(getCampo("des"));
    addAlterKey(key_des);
  
  }

}
