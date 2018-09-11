/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.seguridad;

import es.cvjpy.Clasificado;
import es.cvjpy.CblMngrAbs;
import es.cvjpy.PrincipalCBLPro;
import es.cvjpy.cobol.Ficherable;
import es.cvjpy.cobol.sg.Bsgser;
import es.cvjpy.cobol.sg.InterFsgser;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author carlos
 */
public abstract class UsuarioMngrAbs extends CblMngrAbs<Usuario> implements UsuarioMngrPro {

    private UsuarioBinding binding;

    private UsuarioBinding getBinding() throws Exception {
        if (binding == null) {
            binding = new UsuarioBinding(getPrincipalPro());
        }
        return binding;
    }

    private Object siguienteId() throws Exception {
        String ret;
        int ind = 0;
        do {
            ind++;
            ret = "usuario" + ind;
        } while (existeId(ret));
        return ret;
    }

    private InterFsgser getFsgser() throws Exception {
        return (InterFsgser) getPrincipalPro().getSesionCobol().getFicheroMantenimiento("es.cvjpy.cobol.sg.FSGSER");
    }

    protected abstract PrincipalCBLPro getPrincipalPro() throws Exception;

    @Override
    protected Ficherable getNavegante() throws Exception {
        return getFsgser();
    }

    @Override
    protected boolean filtrando() throws Exception {
        boolean ret = true;
        if (!getBuscar().equals("")) {
            if (getClasificado().getId().equals("Usuario.bqda.id")) {
                if (!getFsgser().getBsgser().getCla().startsWith(getBuscar())) {
                    ret = false;
                }
            }
            if (getClasificado().getId().equals("Usuario.bqda.descripcion")) {
                if (!getFsgser().getBsgser().getDes().startsWith(getBuscar())) {
                    ret = false;
                }
            }
        }
        return ret;

    }

    @Override
    protected Usuario recuperaParaBuscar() throws Exception {
        Usuario bean = new Usuario();
        Bsgser cobol = getFsgser().getBsgser();
        getBinding().toBeanBus(bean, cobol);
        return bean;
    }

    @Override
    protected Usuario recupera() throws Exception {
        Usuario bean = new Usuario();
        Bsgser cobol = getFsgser().getBsgser();
        getBinding().toBean(bean, cobol);
        return bean;
    }

    @Override
    public Usuario getEntidad(Object id) throws Exception {
        Usuario ret = null;
        getFsgser().getBsgser().setCla(id.toString());
        if (!getFsgser().READ()) {
            ret = recupera();
        }
        return ret;
    }

    @Override
    public Usuario ponteEnEste(Usuario cosa) throws Exception {
        getFsgser().getBsgser().setCla(cosa.getId());
        getFsgser().READ();
        return recupera();
    }

    @Override
    public void grabarEnCobol(Usuario bean) throws Exception {

        getFsgser().getBsgser().setCla(bean.getId());
        if (getFsgser().READ()) {
            getFsgser().getBsgser().initAll();
        }
        Bsgser cobol = getFsgser().getBsgser();
        getBinding().toCobol(cobol, bean);

        if (getFsgser().REWRITE()) {
            getFsgser().WRITE();
        }
    }

    @Override
    public void borrarEnCobol(Usuario cosa) throws Exception {
        getFsgser().getBsgser().setCla(cosa.getId());
        getFsgser().DELETE();
    }

    @Override
    public Usuario nuevo() throws Exception {
        Usuario cosa = new Usuario();
        cosa.setId((String) siguienteId());
        cosa.setDescripcion("");
        setNuevo(true);
        return cosa;
    }

    @Override
    protected Clasificado crearClasificado() {
        return new Clasificado("Usuario.bqda.id", ResourceBundle.getBundle("seguridadlit").getString("Identificador"), "cla");
    }

    @Override
    public List<Clasificado> crearClasificaciones() {
        List<Clasificado> ret = new ArrayList();
        ret.add(crearClasificado());
        ret.add(new Clasificado("Usuario.bqda.descripcion", ResourceBundle.getBundle("seguridadlit").getString("Descripcion"), "des"));
        return ret;
    }

    private RolMngrPro rolMngr;

    private RolMngrPro getRolMngr() throws Exception {
        if (rolMngr == null) {
            rolMngr = new RolMngrAbs() {
                @Override
                protected PrincipalCBLPro getPrincipalPro() throws Exception {
                    return UsuarioMngrAbs.this.getPrincipalPro();
                }
            };
        }
        return rolMngr;
    }

    @Override
    public List<Rol> getRoles() throws Exception {
        return getRolMngr().getTodas();
    }
}
