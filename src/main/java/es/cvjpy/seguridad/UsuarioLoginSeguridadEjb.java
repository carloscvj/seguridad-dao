/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.seguridad;

import es.cvjpy.PrincipalCBLPro;

/**
 *
 * @author carlos
 */
public class UsuarioLoginSeguridadEjb extends UsuarioLoginAbs implements UsuarioLoginSeguridadPro {

    private PrincipalCBLPro principalPro;

    public UsuarioLoginSeguridadEjb() {
    }

    public UsuarioLoginSeguridadEjb(PrincipalCBLPro principalPro) {
        this.principalPro = principalPro;
    }

    @Override
    protected PrincipalCBLPro getPrincipalPro() {
        return principalPro;
    }

}
