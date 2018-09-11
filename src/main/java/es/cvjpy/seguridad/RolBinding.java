/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.seguridad;

import es.cvjpy.PrincipalCBLPro;
import es.cvjpy.cobol.sg.Bsgrole;

/**
 *
 * @author carlos
 */
public class RolBinding {

    private final PrincipalCBLPro principalPro;

    public RolBinding(PrincipalCBLPro principalPro) {
        this.principalPro = principalPro;
    }

    public void toBean(Rol bean, Bsgrole cobol) throws Exception {
        bean.setId(cobol.getCla());
        bean.setDescripcion(cobol.getDes());
    }

    public void toCobol(Bsgrole cobol, Rol bean) throws Exception {
        cobol.setCla(bean.getId());
        cobol.setDes(bean.getDescripcion());
    }

    public void toBeanBus(Rol bean, Bsgrole cobol) {
        bean.setId(cobol.getCla());
        bean.setDescripcion(cobol.getDes());
    }

}
