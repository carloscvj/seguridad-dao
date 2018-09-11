/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.cobol.utilidades;

import es.cvjpy.PrincipalCBLPro;

/**
 *
 * @author carlos
 */
public class ReconstruirIdxSeguridadEjb extends ReconstruirIdxAbs implements ReconstruirIdxSeguridadPro {

    private PrincipalCBLPro principalPro;

    public ReconstruirIdxSeguridadEjb() {
    }

    public ReconstruirIdxSeguridadEjb(PrincipalCBLPro prog) {
        this.principalPro = prog;
    }

    @Override
    protected PrincipalCBLPro getPrincipalPro() {
        return principalPro;
    }
}
