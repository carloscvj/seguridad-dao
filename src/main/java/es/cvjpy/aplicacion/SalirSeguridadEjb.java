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
public class SalirSeguridadEjb extends SalirCBLAbs implements SalirSeguridadPro {

    private PrincipalCBLPro principalPro;

    public SalirSeguridadEjb() {
    }

    public SalirSeguridadEjb(PrincipalCBLPro principalPro) {
        this.principalPro = principalPro;
    }

    @Override
    protected PrincipalCBLPro getPrincipalPro() {
        return principalPro;
    }

}
