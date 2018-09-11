package es.cvjpy.cobol.sg;

import es.cvjpy.cobol.*;

public final class RegistroFsgnr extends Registro {

  public Campo cla;
  public Texto cla_nodo;
  public Texto cla_role;

  public Key key_cla;

  public RegistroFsgnr() {
    super("registrofsgnr", 192);
    crearcampos();
    crearclaves();
    initAll();
  }

  protected void crearcampos() {
    cla = new Campo(this, "cla", 0, 128);
    add(cla);
    cla_nodo = new Texto(this, "cla_nodo", 0, 64);
    add(cla_nodo);
    cla_role = new Texto(this, "cla_role", 64, 64);
    add(cla_role);
  }

  protected void crearclaves() {
    key_cla = new Key("cla");
    key_cla.add(getCampo("cla"));
    setKey(key_cla);
  
  }

}
