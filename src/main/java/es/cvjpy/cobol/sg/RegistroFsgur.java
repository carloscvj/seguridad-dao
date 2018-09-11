package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;

public final class RegistroFsgur extends Registro {

  public Campo cla;
  public Texto cla_user;
  public Texto cla_role;

  public Key key_cla;

  public RegistroFsgur() {
    super("registrofsgur", 192);
    crearcampos();
    crearclaves();
    initAll();
  }

  protected void crearcampos() {
    cla = new Campo(this, "cla", 0, 128);
    add(cla);
    cla_user = new Texto(this, "cla_user", 0, 64);
    add(cla_user);
    cla_role = new Texto(this, "cla_role", 64, 64);
    add(cla_role);
  }

  protected void crearclaves() {
    key_cla = new Key("cla");
    key_cla.add(getCampo("cla"));
    setKey(key_cla);
  
  }

}
