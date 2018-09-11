/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.seguridad;

import es.cvjpy.Clasificado;
import es.cvjpy.CblMngrAbs;
import es.cvjpy.PrincipalCBLPro;
import es.cvjpy.cobol.Ficherable;
import es.cvjpy.cobol.sg.Bsgrole;
import es.cvjpy.cobol.sg.InterFsgrole;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author carlos
 */
public abstract class RolMngrAbs extends CblMngrAbs<Rol> implements RolMngrPro {

    private RolBinding binding;

    private RolBinding getBinding() throws Exception {
        if (binding == null) {
            binding = new RolBinding(getPrincipalPro());
        }
        return binding;
    }

    private Object siguienteId() throws Exception {
        String ret;
        int ind = 0;
        do {
            ind++;
            ret = "rol" + ind;
        } while (existeId(ret));
        return ret;
    }

    private InterFsgrole getFsgrole() throws Exception {
        return (InterFsgrole) getPrincipalPro().getSesionCobol().getFicheroMantenimiento("es.cvjpy.cobol.sg.FSGROLE");
    }

    protected abstract PrincipalCBLPro getPrincipalPro() throws Exception;

    @Override
    protected Ficherable getNavegante() throws Exception {
        return getFsgrole();
    }

    @Override
    protected boolean filtrando() throws Exception {
        boolean ret = true;
        if (!getBuscar().equals("")) {
            if (getClasificado().getId().equals("Rol.bqda.id")) {
                if (!getFsgrole().getBsgrole().getCla().startsWith(getBuscar())) {
                    ret = false;
                }
            }
            if (getClasificado().getId().equals("Rol.bqda.descripcion")) {
                if (!getFsgrole().getBsgrole().getDes().startsWith(getBuscar())) {
                    ret = false;
                }
            }
        }
        return ret;

    }

    @Override
    protected Clasificado crearClasificado() {
        return new Clasificado("Rol.bqda.id", ResourceBundle.getBundle("seguridadlit").getString("Identificador"), "cla");
    }

    @Override
    protected Rol recuperaParaBuscar() throws Exception {
        Rol bean = new Rol();
        Bsgrole cobol = getFsgrole().getBsgrole();
        getBinding().toBeanBus(bean, cobol);
        return bean;
    }

    @Override
    protected Rol recupera() throws Exception {
        Rol bean = new Rol();
        Bsgrole cobol = getFsgrole().getBsgrole();
        getBinding().toBean(bean, cobol);
        return bean;
    }

    @Override
    public Rol getEntidad(Object id) throws Exception {
        Rol ret = null;
        getFsgrole().getBsgrole().setCla(id.toString());
        if (!getFsgrole().READ()) {
            ret = recupera();
        }
        return ret;
    }

    @Override
    public Rol ponteEnEste(Rol cosa) throws Exception {
        getFsgrole().getBsgrole().setCla(cosa.getId());
        getFsgrole().READ();
        return recupera();
    }

    @Override
    public void grabarEnCobol(Rol bean) throws Exception {
        getFsgrole().getBsgrole().setCla(bean.getId());
        if (getFsgrole().READ()) {
            getFsgrole().getBsgrole().initAll();
        }
        Bsgrole cobol = getFsgrole().getBsgrole();
        getBinding().toCobol(cobol, bean);
        if (getFsgrole().REWRITE()) {
            getFsgrole().WRITE();
        }
    }

    @Override
    public void borrarEnCobol(Rol cosa) throws Exception {
        getFsgrole().getBsgrole().setCla(cosa.getId());
        getFsgrole().DELETE();
    }

    @Override
    public Rol nuevo() throws Exception {
        Rol cosa = new Rol();
        cosa.setId((String) siguienteId());
        cosa.setDescripcion("");
        setNuevo(true);
        return cosa;

    }

    @Override
    public List<Clasificado> crearClasificaciones() {
        List<Clasificado> ret = new ArrayList();
        ret.add(crearClasificado());
        ret.add(new Clasificado("Rol.bqda.descripcion", ResourceBundle.getBundle("seguridadlit").getString("Descripcion"), "des"));
        return ret;
    }

    @Override
    public List<Rol> getTodas() throws Exception {
        List<Rol> ret = new ArrayList();
        InterFsgrole fsgrole = getFsgrole();
        fsgrole.getBsgrole().initAll();
        if (!fsgrole.START_NO_MENOR()) {
            while (!fsgrole.NEXT()) {
                Rol bean = new Rol();
                Bsgrole cobol = fsgrole.getBsgrole();
                getBinding().toBean(bean, cobol);
                ret.add(bean);
            }
        }
        return ret;
    }

}
