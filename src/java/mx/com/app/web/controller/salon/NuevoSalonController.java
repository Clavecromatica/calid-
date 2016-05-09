/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.app.web.controller.salon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.com.app.data.constantes.TipoSalon;
import mx.com.app.data.dao.edificio.EdificioDAO;
import mx.com.app.data.dao.salon.SalonDAO;
import mx.com.app.data.model.edificio.Edificio;
import mx.com.app.data.model.salon.Salon;

/**
 *
 * @author baquera
 */
@ManagedBean
@ViewScoped
public class NuevoSalonController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Salon salon;

    public Salon getSalon() {
        if (salon == null) {
            salon = new Salon();
        }
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public void guardarSalon() {
        if (!SalonController.validarSalon(salon)) {
            return;
        }
        SalonDAO sDAO = new SalonDAO();
        try {
            sDAO.guardarSalon(salon);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Info", "Salón guardado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Info", "Error al guardar el salon, intenta más tarde"));

        }
    }
    
    public HashMap<String, Integer> getEdificios() throws Exception {
        List<Edificio> edificios = new ArrayList<>();
        HashMap<String, Integer> mapa = new HashMap<>();
        EdificioDAO eDAO = new EdificioDAO();
        edificios = eDAO.todosEdificios();
        for (Edificio edificio : edificios) {
            mapa.put(edificio.getNombre(), edificio.getEdificioId());
        }
        return mapa;
    }

    public HashMap<String, Integer> getTiposSalon() {
        HashMap<String, Integer> mapa = new HashMap<>();
        mapa.put("Sala", 1);
        mapa.put("Convencional", 2);
        return mapa;
    }
}
