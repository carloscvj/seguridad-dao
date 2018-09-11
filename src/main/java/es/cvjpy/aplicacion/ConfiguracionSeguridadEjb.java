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
public class ConfiguracionSeguridadEjb extends ConfiguracionCBLAbs implements ConfiguracionSeguridadPro {

    private PrincipalCBLPro principalPro;

    public ConfiguracionSeguridadEjb() {
    }

    public ConfiguracionSeguridadEjb(PrincipalCBLPro principalPro) {
        this.principalPro = principalPro;
    }

    @Override
    protected PrincipalCBLPro getPrincipalPro() {
        return principalPro;
    }

}
