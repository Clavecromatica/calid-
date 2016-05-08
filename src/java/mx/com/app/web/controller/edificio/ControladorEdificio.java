package mx.com.app.web.controller.edificio;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.com.app.data.dao.edificio.EdificioDAO;
import mx.com.app.data.model.edificio.Edificio;

@ManagedBean
@ViewScoped
public class ControladorEdificio implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private List<Edificio> edificios;

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
    
    
}
