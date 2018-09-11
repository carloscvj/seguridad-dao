package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;

public final class RegistroFsgser extends Registro {

  public Texto cla;
  public Texto des;
  public Texto pass;

  public Key key_cla;
  public Key key_des;

  public RegistroFsgser() {
    super("registrofsgser", 256);
    crearcampos();
    crearclaves();
    initAll();
  }

  protected void crearcampos() {
    cla = new Texto(this, "cla", 0, 64);
    add(cla);
    des = new Texto(this, "des", 64, 64);
    add(des);
    pass = new Texto(this, "pass", 128, 64);
    add(pass);
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
