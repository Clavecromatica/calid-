package mx.com.app.web.controller.puesto;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import mx.com.app.data.dao.puesto.PuestoDAO;
import mx.com.app.data.model.Puesto.Puesto;

@ManagedBean
@ViewScoped
public class ControladorPuesto implements Serializable{

    private List<Puesto> puestos;
    private Puesto puestoSeleccionado;

    public List<Puesto> getPuestos() throws Exception{
        if(puestos == null){
            PuestoDAO pDAO = new PuestoDAO();
            puestos = pDAO.todosPuestos();
        }
        return puestos;
    }

    public void setPuestos(List<Puesto> puestos) {
        this.puestos = puestos;
    }

    public Puesto getPuestoSeleccionado() {
        return puestoSeleccionado;
    }

    public void setPuestoSeleccionado(Puesto puestoSeleccionado) {
        this.puestoSeleccionado = puestoSeleccionado;
    }
    
    public void eliminarPuesto(){
        if(puestoSeleccionado == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Selecciona un puesto para continuar"));
            return;
        }
        PuestoDAO pDAO = new PuestoDAO();
        try {
            pDAO.eliminarPuesto(puestoSeleccionado);
            puestos = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Puesto guardado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al guardar el puesto, intenta más tarde"));
        }
    }
    
    public String editarPuesto(){
        if(getPuestoSeleccionado() == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Selecciona un puesto para continuar"));
            return "";
        }
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("puestoAEditar", getPuestoSeleccionado());
        return "/private/puestos/editar-puesto.xhtml";
    }
    
}
