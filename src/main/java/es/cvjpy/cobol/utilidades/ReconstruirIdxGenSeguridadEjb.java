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
public class ReconstruirIdxGenSeguridadEjb extends ReconstruirIdxGenAbs implements ReconstruirIdxGenSeguridadPro {

    private PrincipalCBLPro principalPro;

    public ReconstruirIdxGenSeguridadEjb() {
    }

    public ReconstruirIdxGenSeguridadEjb(PrincipalCBLPro prog) {
        this.principalPro = prog;
    }

    @Override
    protected PrincipalCBLPro getPrincipalPro() {
        return principalPro;
    }

}
