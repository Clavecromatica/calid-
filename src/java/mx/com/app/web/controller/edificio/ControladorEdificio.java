package mx.com.app.web.controller.edificio;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import mx.com.app.data.dao.edificio.EdificioDAO;
import mx.com.app.data.model.edificio.Edificio;

@ManagedBean
@ViewScoped
public class ControladorEdificio implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private List<Edificio> edificios;
    private Edificio edificioSeleccionado;

    public List<Edificio> getEdificios() throws Exception{
        if(edificios == null){
            EdificioDAO eDAO = new EdificioDAO();
            edificios = eDAO.todosEdificios();
        }
        return edificios;
    }

    public void setEdificios(List<Edificio> edificios) {
        this.edificios = edificios;
    }
    
    public String editarEdificio(){
        if(getEdificioSeleccionado() == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Selecciona un edificio para continuar"));
            return "";
        }
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("edificioAEditar", getEdificioSeleccionado());
        return "/private/edificios/editar-edificio.xhtml";
    }

    public Edificio getEdificioSeleccionado() {
        return edificioSeleccionado;
    }

    public void setEdificioSeleccionado(Edificio edificioSeleccionado) {
        this.edificioSeleccionado = edificioSeleccionado;
    }
}
