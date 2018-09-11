/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.seguridad;

import es.cvjpy.PrincipalCBLPro;
import es.cvjpy.cobol.sg.Bsgrole;
import es.cvjpy.cobol.sg.Bsgnodo;
import es.cvjpy.cobol.sg.InterFsgnr;
import es.cvjpy.cobol.sg.InterFsgrole;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos
 */
public class MenuNodoBinding {

    private final PrincipalCBLPro principalPro;
    private RolBinding rolBinding;

    public MenuNodoBinding(PrincipalCBLPro principalPro) {
        this.principalPro = principalPro;
    }

    private RolBinding getRolBinding() {
        if (rolBinding == null) {
            rolBinding = new RolBinding(principalPro);
        }
        return rolBinding;
    }

    private InterFsgnr getFsgnr() throws Exception {
        return (InterFsgnr) principalPro.getSesionCobol().getFicheroMantenimiento("es.cvjpy.cobol.sg.FSGNR");
    }

    private InterFsgrole getFsgrole() throws Exception {
        return (InterFsgrole) principalPro.getSesionCobol().getFicheroMantenimiento("es.cvjpy.cobol.sg.FSGROLE");
    }

    private void borrarRelacionRol(MenuNodo cosa) throws Exception {
        getFsgnr().getBsgnr().initAll();
        getFsgnr().getBsgnr().setCla_nodo(cosa.getId());
        if (!getFsgnr().START_NO_MENOR()) {
            while (!getFsgnr().NEXT()) {
                if (!getFsgnr().getBsgnr().getCla_nodo().trim().equals(cosa.getId().trim())) {
                    break;
                }
                getFsgnr().DELETE();
            }
        }
    }

    public void toBean(MenuNodo bean, Bsgnodo cobol) throws Exception {
        bean.setId(cobol.getCla());
        List<Rol> tienes = new ArrayList();
        getFsgnr().getBsgnr().initAll();
        getFsgnr().getBsgnr().setCla_nodo(cobol.getCla());
        if (!getFsgnr().START_NO_MENOR()) {
            while (!getFsgnr().NEXT()) {
                if (!getFsgnr().getBsgnr().getCla_nodo().trim().equals(cobol.getCla().trim())) {
                    break;
                }
                getFsgrole().getBsgrole().setCla(getFsgnr().getBsgnr().getCla_role());
                if (!getFsgrole().READ()) {
                    Rol rol = new Rol();
                    Bsgrole cob = getFsgrole().getBsgrole();
                    getRolBinding().toBean(rol, cob);
                    tienes.add(rol);
                }
            }
        }
        bean.setRoles(tienes);
    }

    public void toCobol(Bsgnodo cobol, MenuNodo bean) throws Exception {
        cobol.setCla(bean.getId());
        borrarRelacionRol(bean);
        for (Rol cada : bean.getRoles()) {
            getFsgnr().getBsgnr().setCla_nodo(bean.getId());
            getFsgnr().getBsgnr().setCla_role(cada.getId());
            if (getFsgnr().WRITE()) {
                getFsgnr().REWRITE();
            }
        }
    }

    public void toBeanBus(MenuNodo bean, Bsgnodo cobol) {
        bean.setId(cobol.getCla());
    }

}
