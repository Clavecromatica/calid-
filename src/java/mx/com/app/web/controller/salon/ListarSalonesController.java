/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.app.web.controller.salon;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import mx.com.app.data.dao.salon.SalonDAO;
import mx.com.app.data.model.salon.Salon;

/**
 *
 * @author baquera
 */
@ManagedBean
@ViewScoped
public class ListarSalonesController implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Salon> salones;
    private Salon salonSeleccionado;

    public Salon getSalonSeleccionado() {
        return salonSeleccionado;
    }

    public void setSalonSeleccionado(Salon salonSeleccionado) {
        this.salonSeleccionado = salonSeleccionado;
    }

    public List<Salon> getSalones() throws Exception {
        if (salones == null) {
            SalonDAO sDAO = new SalonDAO();
            salones = sDAO.listarSalones();
        }
        return salones;
    }

    public void setSalones(List<Salon> salones) {
        this.salones = salones;
    }

    public String editarSalones() {
        if (getSalonSeleccionado() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Atencion", "Selecciona un salon"));
            return "";
        }
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("salonAEditar", getSalonSeleccionado());
        return "/private/salones/editar-salon.xhtml";
    }

    public void eliminarSalon() {
        if (getSalonSeleccionado() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Atencion", "Selecciona un salon"));

        } else {
            SalonDAO sDAO = new SalonDAO();
            try {
                sDAO.eliminarSalon(this.salonSeleccionado.getSalonID());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Salón eliminado correctamente"));
            } catch (Exception ex) {
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Error al eliminar el salón, intenta más tarde"));
            }
        }
    }
}
