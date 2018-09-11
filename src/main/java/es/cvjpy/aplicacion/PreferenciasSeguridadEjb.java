/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.aplicacion;

import es.cvjpy.PrincipalCBLPro;

/**
 *
 * @author carlos
 */
public class PreferenciasSeguridadEjb extends PreferenciasCBLAbs implements PreferenciasSeguridadPro {

    private PrincipalCBLPro principalPro;

    public PreferenciasSeguridadEjb() {
    }

    public PreferenciasSeguridadEjb(PrincipalCBLPro principalPro) {
        this.principalPro = principalPro;
    }

    @Override
    protected PrincipalCBLPro getPrincipalPro() {
        return principalPro;
    }

}
