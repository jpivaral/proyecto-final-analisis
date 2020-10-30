package com.umg.proyectocovid.service;

import com.umg.proyectocovid.util.SessionUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jocpi
 */
@Named
@RequestScoped
@Data
public class LoginService implements Serializable {

    private String usuario;
    private String password;

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private UsuarioService usuarioService;

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private SessionUtils sessionUtils;

    public void login() {
        var context = FacesContext.getCurrentInstance();
        if (usuarioService.validateCredentials(usuario, password)) {
            var session = SessionUtils.getSession();
            session.setAttribute("username", usuario);
            try {
                context.getExternalContext().redirect("/proyecto-final-analisis/faces/menu.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Usuario y contrasena invalidos",
                            "Por favor ingrese un usuario y contrasena validos"));
        }
    }

    public void logout() {
        var session = SessionUtils.getSession();
        session.invalidate();
        var context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/proyecto-final-analisis/faces/login/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
