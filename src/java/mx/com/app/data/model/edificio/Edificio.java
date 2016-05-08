package mx.com.app.data.model.edificio;

import java.io.Serializable;

public class Edificio implements Serializable{
    private int edificioId;
    private String nombre;
    private float x;
    private float y;

    public int getEdificioId() {
        return edificioId;
    }

    public void setEdificioId(int edificioId) {
        this.edificioId = edificioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
