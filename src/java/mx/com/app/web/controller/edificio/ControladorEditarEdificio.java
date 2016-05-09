package mx.com.app.web.controller.edificio;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import mx.com.app.data.dao.edificio.EdificioDAO;
import mx.com.app.data.model.edificio.Edificio;

@ManagedBean
@ViewScoped
public class ControladorEditarEdificio implements Serializable{
    
    private Edificio edificio;
    
    public Edificio getEdificio() {
        if(edificio == null){
            Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            if(flash.containsKey("edificioAEditar")){
                edificio = (Edificio)flash.get("edificioAEditar");
            }
        }
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }
    
    public void guardarCambios(){
        if(edificio.getNombre().trim().equals("")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Ingresa un nombre válido para edificio"));
            return;
        }
        EdificioDAO eDAO = new EdificioDAO();
        try {
            eDAO.editarEdificio(edificio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Edificio editado correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Error al guardar datos del edificio, intenta más tarde"));
        }
    }
}
