/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy;

import es.cvjpy.aplicacion.ConfiguracionPro;
import es.cvjpy.aplicacion.ConfiguracionSeguridadEjb;
import es.cvjpy.aplicacion.ConfiguracionSeguridadPro;
import es.cvjpy.aplicacion.PreferenciasPro;
import es.cvjpy.aplicacion.PreferenciasSeguridadEjb;
import es.cvjpy.aplicacion.PreferenciasSeguridadPro;
import es.cvjpy.aplicacion.SalirPro;
import es.cvjpy.aplicacion.SalirSeguridadEjb;
import es.cvjpy.aplicacion.SalirSeguridadPro;
import es.cvjpy.cobol.utilidades.ReconstruirIdxSeguridadEjb;
import es.cvjpy.cobol.utilidades.ReconstruirIdxSeguridadPro;
import es.cvjpy.cobol.utilidades.ReconstruirIdxGenSeguridadEjb;
import es.cvjpy.cobol.utilidades.ReconstruirIdxGenSeguridadPro;
import es.cvjpy.cobol.utilidades.ReconstruirIdxGenPro;
import es.cvjpy.cobol.utilidades.ReconstruirIdxPro;
import es.cvjpy.seguridad.MenuNodoMngrPro;
import es.cvjpy.seguridad.MenuNodoMngrSeguridadEjb;
import es.cvjpy.seguridad.MenuNodoMngrSeguridadPro;
import es.cvjpy.seguridad.RolMngrPro;
import es.cvjpy.seguridad.RolMngrSeguridadEjb;
import es.cvjpy.seguridad.RolMngrSeguridadPro;
import es.cvjpy.seguridad.UsuarioLoginPro;
import es.cvjpy.seguridad.UsuarioLoginSeguridadEjb;
import es.cvjpy.seguridad.UsuarioLoginSeguridadPro;
import es.cvjpy.seguridad.UsuarioMngrPro;
import es.cvjpy.seguridad.UsuarioMngrSeguridadEjb;
import es.cvjpy.seguridad.UsuarioMngrSeguridadPro;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class SeguridadEjb extends PrincipalCBLAbs implements SeguridadPro, PrincipalCBLPro {

    private SalirSeguridadPro salirSeguridadEjb;
    private PreferenciasSeguridadPro preferenciasSeguridadEjb;
    private ConfiguracionSeguridadPro configuracionSeguridadEjb;
    private TextoPlanoSeguridadPro textoPlanoSeguridadEjb;
    private ReconstruirIdxSeguridadPro reconstruirIdxSeguridadEjb;
    private ReconstruirIdxGenSeguridadPro reconstruirIdxGenSeguridadEjb;
    private MenuNodoMngrSeguridadPro menuNodoMngrSeguridadEjb;
    private RolMngrSeguridadPro rolMngrSeguridadEjb;
    private UsuarioMngrSeguridadPro usuarioMngrSeguridadEjb;
    private UsuarioLoginSeguridadPro usuarioLoginSeguridadEjb;

    @Override
    protected String dondeEstaElExe() {
        String toExternalForm = null;
        URL resource = getClass().getResource("/exe/srv/SRU.int");
        if (resource != null) {
            toExternalForm = resource.getPath();
            Logger.getLogger(ConfiguracionPro.class.getName()).log(Level.INFO, "URL de SRU.int pero desde el EJB:{0}", toExternalForm);
            int pos = toExternalForm.indexOf("/exe");
            toExternalForm = toExternalForm.substring(0, pos) + "/exe";
        }
        return toExternalForm;
    }

    @Override
    public Object getProgramaServidor(Class cachoPro) throws Exception {
        Object ret = null;

        if (cachoPro.equals(ConfiguracionPro.class)) {
            ret = getConfiguracionPro();
            return ret;
        }
        if (cachoPro.equals(PreferenciasPro.class)) {
            ret = getPreferenciasPro();
            return ret;
        }
        if (cachoPro.equals(SalirPro.class)) {
            ret = getSalirPro();
            return ret;
        }
        if (cachoPro.equals(TextoPlanoPro.class)) {
            ret = getTextoPlanoPro();
            return ret;
        }

        if (cachoPro.equals(ReconstruirIdxPro.class)) {
            ret = getReconstruirIdxPro();
            return ret;
        }
        if (cachoPro.equals(ReconstruirIdxGenPro.class)) {
            ret = getReconstruirIdxGenPro();
            return ret;
        }
        if (cachoPro.equals(MenuNodoMngrPro.class)) {
            ret = getMenuNodoMngrPro();
            return ret;
        }
        if (cachoPro.equals(RolMngrPro.class)) {
            ret = getRolMngrPro();
            return ret;
        }
        if (cachoPro.equals(UsuarioMngrPro.class)) {
            ret = getUsuarioMngrPro();
            return ret;
        }
        if (cachoPro.equals(UsuarioLoginPro.class)) {
            ret = getUsuarioLoginPro();
            return ret;
        }

        if (ret == null) {
            throw new Exception("No está en el servidor el EJB para:" + cachoPro);
        }
        return ret;
    }

    @Override
    public ConfiguracionPro getConfiguracionPro() {
        if (configuracionSeguridadEjb == null) {
            configuracionSeguridadEjb = new ConfiguracionSeguridadEjb(this);
        }
        return configuracionSeguridadEjb;
    }

    @Override
    public PreferenciasPro getPreferenciasPro() {
        if (preferenciasSeguridadEjb == null) {
            preferenciasSeguridadEjb = new PreferenciasSeguridadEjb(this);
        }
        return preferenciasSeguridadEjb;
    }

    @Override
    public SalirPro getSalirPro() {
        if (salirSeguridadEjb == null) {
            salirSeguridadEjb = new SalirSeguridadEjb(this);
        }
        return salirSeguridadEjb;
    }

    @Override
    public TextoPlanoPro getTextoPlanoPro() {
        if (textoPlanoSeguridadEjb == null) {
            textoPlanoSeguridadEjb = new TextoPlanoSeguridadEjb(this);
        }
        return textoPlanoSeguridadEjb;
    }

    @Override
    public ReconstruirIdxPro getReconstruirIdxPro() {
        if (reconstruirIdxSeguridadEjb == null) {
            reconstruirIdxSeguridadEjb = new ReconstruirIdxSeguridadEjb(this);
        }
        return reconstruirIdxSeguridadEjb;
    }

    @Override
    public ReconstruirIdxGenPro getReconstruirIdxGenPro() {
        if (reconstruirIdxGenSeguridadEjb == null) {
            reconstruirIdxGenSeguridadEjb = new ReconstruirIdxGenSeguridadEjb(this);
        }
        return reconstruirIdxGenSeguridadEjb;
    }

    @Override
    public MenuNodoMngrPro getMenuNodoMngrPro() {
        if (menuNodoMngrSeguridadEjb == null) {
            menuNodoMngrSeguridadEjb = new MenuNodoMngrSeguridadEjb(this);
        }
        return menuNodoMngrSeguridadEjb;
    }

    @Override
    public RolMngrPro getRolMngrPro() {
        if (rolMngrSeguridadEjb == null) {
            rolMngrSeguridadEjb = new RolMngrSeguridadEjb(this);
        }
        return rolMngrSeguridadEjb;
    }

    @Override
    public UsuarioMngrPro getUsuarioMngrPro() {
        if (usuarioMngrSeguridadEjb == null) {
            usuarioMngrSeguridadEjb = new UsuarioMngrSeguridadEjb(this);
        }
        return usuarioMngrSeguridadEjb;
    }

    @Override
    public UsuarioLoginPro getUsuarioLoginPro() {
        if (usuarioLoginSeguridadEjb == null) {
            usuarioLoginSeguridadEjb = new UsuarioLoginSeguridadEjb(this);
        }
        return usuarioLoginSeguridadEjb;
    }

}
