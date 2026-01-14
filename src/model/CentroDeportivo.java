package model;

public class CentroDeportivo {

    private Socio[] socios;
    private Actividad[] actividades;
    private int numSocios;
    private int numActividades;


    public Socio[] getSocios() {
        return socios;
    }

    public void setSocios(Socio[] socios) {
        this.socios = socios;
    }

    public Actividad[] getActividades() {
        return actividades;
    }

    public void setActividades(Actividad[] actividades) {
        this.actividades = actividades;
    }

    public int getNumSocios() {
        return numSocios;
    }

    public void setNumSocios(int numSocios) {
        this.numSocios = numSocios;
    }

    public int getNumActividades() {
        return numActividades;
    }

    public void setNumActividades(int numActividades) {
        this.numActividades = numActividades;
    }
}
