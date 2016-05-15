package mx.com.app.web.controller.puesto;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import mx.com.app.data.dao.edificio.EdificioDAO;
import mx.com.app.data.dao.puesto.PuestoDAO;
import mx.com.app.data.model.Puesto.Puesto;
import mx.com.app.data.model.edificio.Edificio;

@ManagedBean
@ViewScoped
public class ControladorEditarPuesto {
    
    private Puesto puesto;

    public Puesto getPuesto() {
        if(puesto == null){
            Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            if(flash.containsKey("puestoAEditar")){
                puesto = (Puesto)flash.get("puestoAEditar");
            }
        }
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
    
    public void guardarCambios(){
        if(puesto.getNombre().trim().equals("")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Ingresa un nombre válido para edificio"));
            return;
        }
        PuestoDAO pDAO = new PuestoDAO();
        try {
            pDAO.editarPuesto(puesto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Puesto editado correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Error al guardar datos del puesto, intenta más tarde"));
        }
    }
}
