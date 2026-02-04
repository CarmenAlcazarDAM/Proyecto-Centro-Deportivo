package model;

public class CentroDeportivo {
    final int MAXIMO_SOCIOS = 100;
    final int MAXIMO_ACTIVIDADES = 20;


    private Socio[] socios;
    private Actividad[] actividades;



    public CentroDeportivo() {
        this.socios = new Socio[MAXIMO_SOCIOS];
        this.actividades = new Actividad[MAXIMO_ACTIVIDADES];

    }

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
}
