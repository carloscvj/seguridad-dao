/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy;

import es.cvjpy.seguridad.MenuNodoMngrPro;
import es.cvjpy.seguridad.RolMngrPro;
import es.cvjpy.seguridad.UsuarioLoginPro;
import es.cvjpy.seguridad.UsuarioMngrPro;

/**
 *
 * @author carlos
 */
public interface SeguridadPro extends PrincipalPro {

    MenuNodoMngrPro getMenuNodoMngrPro();

    RolMngrPro getRolMngrPro();

    UsuarioMngrPro getUsuarioMngrPro();

    UsuarioLoginPro getUsuarioLoginPro();

}
