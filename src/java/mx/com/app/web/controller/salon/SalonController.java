/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.app.web.controller.salon;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import mx.com.app.data.constantes.TipoSalon;
import mx.com.app.data.model.salon.Salon;


public class SalonController {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of SalonController
     */

    public static boolean validarSalon(Salon salon) {
        if (salon.getCapacidad() == 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención",
                            "Ingresar una capacidad válida"));
            return false;
        }
        if (salon.getTipoSalon() != TipoSalon.CONVENCIONAL
                && salon.getTipoSalon() != TipoSalon.SALA) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención",
                            "Ingresar un tipo de salón válido"));
        }
        // TODO Validar que el edificio exista
        return true;
    }

}
