package model;

public class Actividad {

    private int idActividad;
    private String nombre;
    private int duracionMinutos;
    private String nivel;
    private double precioMensual;
    private Socio[] sociosInscritos;

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public double getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(double precioMensual) {
        this.precioMensual = precioMensual;
    }

    public Socio[] getSociosInscritos() {
        return sociosInscritos;
    }

    public void setSociosInscritos(Socio[] sociosInscritos) {
        this.sociosInscritos = sociosInscritos;
    }

    public String ToString(){
        return "\nNombre: " + nombre
                +"\nId: " + idActividad
                + "\nDuración: " + duracionMinutos
                +"\nNivel: " + nivel
                +"\nPrecio mensual: " + precioMensual + "€";
    }
}
