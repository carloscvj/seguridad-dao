/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy;


/**
 *
 * @author carlos
 */
public class TextoPlanoSeguridadEjb extends TextoPlanoCBLAbs implements TextoPlanoSeguridadPro {

    private PrincipalCBLPro PrincipalPro;

    public TextoPlanoSeguridadEjb() {
    }

    public TextoPlanoSeguridadEjb(PrincipalCBLPro PrincipalPro) {
        this.PrincipalPro = PrincipalPro;
    }

    @Override
    protected PrincipalCBLPro getPrincipalPro() {
        return PrincipalPro;
    }

}
