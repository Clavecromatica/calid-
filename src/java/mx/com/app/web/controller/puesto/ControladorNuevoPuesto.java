package mx.com.app.web.controller.puesto;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.com.app.data.dao.puesto.PuestoDAO;
import mx.com.app.data.model.Puesto.Puesto;

@ManagedBean
@ViewScoped
public class ControladorNuevoPuesto implements Serializable{
    
    private Puesto puesto;

    public Puesto getPuesto() {
        if(puesto == null){
            puesto = new Puesto();
        }
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
    
    public void guardarPuesto(){
        if(!validar()){
            return;
        }
        PuestoDAO pDAO = new PuestoDAO();
        try{
            pDAO.guardarPuesto(puesto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Puesto guardado correctamente"));
        } catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Error al guardar el puesto, intenta más tarde"));
        }
    }
    public boolean validar(){
        if(puesto.getNombre().trim().equals("")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Ingresar un nombre válido para puesto"));
            return false;
        }
        return true;
    }
}
