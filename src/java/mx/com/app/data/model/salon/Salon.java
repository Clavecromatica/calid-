/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.app.data.model.salon;

import mx.com.app.data.constantes.TipoSalon;
import mx.com.app.data.model.edificio.Edificio;

/**
 *
 * @author baquera
 */
public class Salon {
    private int salonID;
    private String nombre;
    private int capacidad;
    private Edificio edificio;
    private int tipoSalon;

    public int getSalonID() {
        return salonID;
    }

    public void setSalonID(int salonID) {
        this.salonID = salonID;
    }
   

    /**
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return the edificio
     */
    public Edificio getEdificio() {
        if(edificio == null) {
            edificio = new Edificio();
        }
        return edificio;
    }

    /**
     * @param edificio the edificio to set
     */
    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    /**
     * @return the tipoSalon
     */
    public int getTipoSalon() {
        return tipoSalon;
    }

    /**
     * @param tipoSalon the tipoSalon to set
     */
    public void setTipoSalon(int tipoSalon) {
        this.tipoSalon = tipoSalon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
