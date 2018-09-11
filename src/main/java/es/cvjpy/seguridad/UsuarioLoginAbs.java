/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.seguridad;

import es.cvjpy.PrincipalCBLPro;
import es.cvjpy.cobol.SesionCobol;
import es.cvjpy.cobol.sg.Bsgser;
import es.cvjpy.cobol.sg.InterFsgser;

/**
 *
 * @author carlos
 */
public abstract class UsuarioLoginAbs implements UsuarioLoginPro {

    private UsuarioBinding binding;

    private UsuarioBinding getBinding() throws Exception {
        if (binding == null) {
            binding = new UsuarioBinding(getPrincipalPro());
        }
        return binding;
    }

    private InterFsgser getFsgser() throws Exception {
        return (InterFsgser) getPrincipalPro().getSesionCobol().getFicheroMantenimiento("es.cvjpy.cobol.sg.FSGSER");
    }

    protected abstract PrincipalCBLPro getPrincipalPro() throws Exception;

    protected Usuario recupera() throws Exception {
        Usuario bean = new Usuario();
        Bsgser cobol = getFsgser().getBsgser();
        getBinding().toBean(bean, cobol);
        return bean;
    }

    @Override
    public Usuario getUsuario(String id) throws Exception {
        Usuario ret = null;
        getFsgser().getBsgser().setCla(id);
        if (!getFsgser().READ()) {
            ret = recupera();
        }
        return ret;
    }

}
