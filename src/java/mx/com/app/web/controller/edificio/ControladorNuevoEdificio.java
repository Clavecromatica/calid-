package mx.com.app.web.controller.edificio;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.com.app.data.dao.edificio.EdificioDAO;
import mx.com.app.data.model.edificio.Edificio;

@ManagedBean
@ViewScoped
public class ControladorNuevoEdificio implements Serializable{
    
    private Edificio edificio;
    
    public Edificio getEdificio() {
        if(edificio == null){
            edificio = new Edificio();
        }
        return edificio;
    }
    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }
    
    public void guardarEdificio(){
        if(!validar()){
            return;
        }
        EdificioDAO eDAO = new EdificioDAO();
        try{
            eDAO.guardarEdificio(edificio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Edificio guardado correctamente"));
        } catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Error al guardar el edificio, intenta más tarde"));
        }
    }
    public boolean validar(){
        if(edificio.getNombre().trim().equals("")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Ingresar un nombre válido para edificio"));
            return false;
        }
        return true;
    }
}
