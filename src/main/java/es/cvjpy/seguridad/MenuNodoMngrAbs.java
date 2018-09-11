/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.seguridad;

import es.cvjpy.Clasificado;
import es.cvjpy.CblMngrAbs;
import es.cvjpy.PrincipalCBLPro;
import es.cvjpy.cobol.Ficherable;
import es.cvjpy.cobol.sg.Bsgnodo;
import es.cvjpy.cobol.sg.InterFsgnodo;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author carlos
 */
public abstract class MenuNodoMngrAbs extends CblMngrAbs<MenuNodo> implements MenuNodoMngrPro {//Probando charset Ă¡Ă©Ă­Ă³ĂºĂ¼Ă±Ă?Ă‰Ă?Ă“Ă?ĂœĂ‘

    private MenuNodoBinding binding;

    private MenuNodoBinding getBinding() throws Exception {
        if (binding == null) {
            binding = new MenuNodoBinding(getPrincipalPro());
        }
        return binding;
    }

    private InterFsgnodo getFsgnodo() throws Exception {
        return (InterFsgnodo) getPrincipalPro().getSesionCobol().getFicheroMantenimiento("es.cvjpy.cobol.sg.FSGNODO");
    }

    protected abstract PrincipalCBLPro getPrincipalPro() throws Exception;

    @Override
    protected Ficherable getNavegante() throws Exception {
        return getFsgnodo();
    }

    @Override
    protected boolean filtrando() throws Exception {
        boolean ret = true;
        if (!getBuscar().equals("")) {
            if (getClasificado().getId().equals("MenuNodo.bqda.id")) {
                if (!getFsgnodo().getBsgnodo().getCla().startsWith(getBuscar())) {
                    ret = false;
                }
            }
        }
        return ret;

    }

    @Override
    protected MenuNodo recuperaParaBuscar() throws Exception {
        MenuNodo bean = new MenuNodo();
        Bsgnodo cobol = getFsgnodo().getBsgnodo();
        getBinding().toBeanBus(bean, cobol);
        return bean;
    }

    @Override
    protected MenuNodo recupera() throws Exception {
        MenuNodo bean = new MenuNodo();
        Bsgnodo cobol = getFsgnodo().getBsgnodo();
        getBinding().toBean(bean, cobol);
        return bean;
    }

    @Override
    public MenuNodo getEntidad(Object id) throws Exception {
        MenuNodo ret = null;
        getFsgnodo().getBsgnodo().setCla(id.toString());
        if (!getFsgnodo().READ()) {
            ret = recupera();
        }
        return ret;
    }

    @Override
    public MenuNodo ponteEnEste(MenuNodo cosa) throws Exception {
        getFsgnodo().getBsgnodo().setCla(cosa.getId());
        getFsgnodo().READ();
        return recupera();
    }

    @Override
    public void grabarEnCobol(MenuNodo bean) throws Exception {
        getFsgnodo().getBsgnodo().setCla(bean.getId());
        if (getFsgnodo().READ()) {
            getFsgnodo().getBsgnodo().initAll();
        }
        Bsgnodo cobol = getFsgnodo().getBsgnodo();
        getBinding().toCobol(cobol, bean);
        if (getFsgnodo().REWRITE()) {
            getFsgnodo().WRITE();
        }
    }

    @Override
    public void borrarEnCobol(MenuNodo cosa) throws Exception {
        getFsgnodo().getBsgnodo().setCla(cosa.getId());
        getFsgnodo().DELETE();
    }

    @Override
    public MenuNodo nuevo() throws Exception {
        MenuNodo cosa = new MenuNodo();
        setNuevo(true);
        return cosa;
    }

    @Override
    protected Clasificado crearClasificado() {
        return new Clasificado("MenuNodo.bqda.id", ResourceBundle.getBundle("seguridadlit").getString("Identificador"), "cla");
    }

    @Override
    public List<Clasificado> crearClasificaciones() {
        List<Clasificado> ret = new ArrayList();
        ret.add(crearClasificado());
        return ret;
    }

    private RolMngrPro rolMngr;

    private RolMngrPro getRolMngr() throws Exception {
        if (rolMngr == null) {
            rolMngr = new RolMngrAbs() {
                @Override
                protected PrincipalCBLPro getPrincipalPro() throws Exception {
                    return MenuNodoMngrAbs.this.getPrincipalPro();
                }
            };
        }
        return rolMngr;
    }

    @Override
    public List<Rol> getRoles() throws Exception {
        return getRolMngr().getTodas();
    }

    @Override
    public List<Rol> getPermitidos(String deste) throws Exception {
        List<Rol> ret = null;
        MenuNodo mn = getEntidad(deste);
        if (mn != null) {
            ret = mn.getRoles();
        }
        return ret;
    }

}
