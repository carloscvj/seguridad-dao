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
public class RolMngrSeguridadEjb extends RolMngrAbs implements RolMngrSeguridadPro {

    private PrincipalCBLPro principalPro;

    public RolMngrSeguridadEjb() {
    }

    public RolMngrSeguridadEjb(PrincipalCBLPro principalPro) {
        this.principalPro = principalPro;
    }

    @Override
    protected PrincipalCBLPro getPrincipalPro() {
        return principalPro;
    }
}
