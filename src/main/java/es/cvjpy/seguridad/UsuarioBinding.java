/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.seguridad;

import es.cvjpy.PrincipalCBLPro;
import es.cvjpy.cobol.sg.Bsgrole;
import es.cvjpy.cobol.sg.Bsgser;
import es.cvjpy.cobol.sg.InterFsgrole;
import es.cvjpy.cobol.sg.InterFsgur;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos
 */
public class UsuarioBinding {

    private final PrincipalCBLPro principalCBLPro;
    private RolBinding rolBinding;

    public UsuarioBinding(PrincipalCBLPro principalCBLPro) {
        this.principalCBLPro = principalCBLPro;
    }

    private RolBinding getRolBinding() {
        if (rolBinding == null) {
            rolBinding = new RolBinding(principalCBLPro);
        }
        return rolBinding;
    }

    private InterFsgur getFsgur() throws Exception {
        return (InterFsgur) principalCBLPro.getSesionCobol().getFicheroMantenimiento("es.cvjpy.cobol.sg.FSGUR");
    }

    private InterFsgrole getFsgrole() throws Exception {
        return (InterFsgrole) principalCBLPro.getSesionCobol().getFicheroMantenimiento("es.cvjpy.cobol.sg.FSGROLE");
    }

    private void borrarRelacionRol(Usuario bean) throws Exception {
        getFsgur().getBsgur().initAll();
        getFsgur().getBsgur().setCla_user(bean.getId());
        if (!getFsgur().START_NO_MENOR()) {
            while (!getFsgur().NEXT()) {

                if (!getFsgur().getBsgur().getCla_user().trim().equals(bean.getId().trim())) {
                    break;
                }
                getFsgur().DELETE();
            }
        }
    }

    public void toBean(Usuario bean, Bsgser cobol) throws Exception {
        bean.setId(cobol.getCla());
        bean.setDescripcion(cobol.getDes());
        bean.setPassword(cobol.getPass());

        List<Rol> tienes = new ArrayList();

        getFsgur().getBsgur().initAll();
        getFsgur().getBsgur().setCla_user(cobol.getCla());
        if (!getFsgur().START_NO_MENOR()) {
            while (!getFsgur().NEXT()) {
                if (!getFsgur().getBsgur().getCla_user().trim().equals(cobol.getCla().trim())) {
                    break;
                }
                getFsgrole().getBsgrole().setCla(getFsgur().getBsgur().getCla_role());
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

    public void toCobol(Bsgser cobol, Usuario bean) throws Exception {
        cobol.setCla(bean.getId());
        cobol.setDes(bean.getDescripcion());
        cobol.setPass(bean.getPassword());
        borrarRelacionRol(bean);
        for (Rol cada : bean.getRoles()) {
            getFsgur().getBsgur().setCla_user(bean.getId());
            getFsgur().getBsgur().setCla_role(cada.getId());
            if (getFsgur().WRITE()) {
                getFsgur().REWRITE();
            }
        }
    }

    public void toBeanBus(Usuario bean, Bsgser cobol) {
        bean.setId(cobol.getCla());
        bean.setDescripcion(cobol.getDes());
        bean.setPassword(cobol.getPass());
    }

}
