package model;

import java.util.Objects;

public class Actividad {
    private String nombre;
    private int idActividad;
    private int duracionMinutos;
    private String nivel;
    private double precioMensual;
    private Socio[] sociosActividad;
    private static int contID=0;
    private int numMiembros;


    public Actividad(String nombre, int duracionMinutos, String nivel, double precioMensual,int numMiembros) {
        this.nombre = nombre;
        this.duracionMinutos = duracionMinutos;
        this.nivel = nivel;
        this.precioMensual = precioMensual;
        this.numMiembros = numMiembros;
        this.idActividad=contID;
        contID++;
        sociosActividad = new Socio[numMiembros];
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

    public int getNumMiembros() {
        return numMiembros;
    }

    public void setNumMiembros(int numMiembros) {
        this.numMiembros = numMiembros;
    }





    @Override
    public String toString() {
        return "\n" + "Actividad: " + nombre + "\n" +
                "ID: " + idActividad + "\n" +
                "Duración: " + duracionMinutos + " min " + "\n" +
                "Nivel: " + nivel + "\n" +
                "Precio mensual: " + precioMensual +"€ \n" +
                "Numero miembros: " + numMiembros + "\n" +
                "\n";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actividad actividad = (Actividad) o;
        return Objects.equals(nombre, actividad.nombre) && Objects.equals(nivel, actividad.nivel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, idActividad, nivel);
    }

    //funcionalidades

    /**
     * Método que comprueba si existe un socio en el array de socios de  la actividad
     * @param socio objeto socio que busca en el array
     * @return posicion del socio si existe, -1 si no existe
     */
    public int existeSocio(Socio socio) {

        if(socio!=null && sociosActividad!=null && sociosActividad.length>0) {
            for(int i=0; i<sociosActividad.length; i++) {
                if(sociosActividad[i]!=null && sociosActividad[i].equals(socio)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Método que añade un socio (sin repetir, dni distinto) al array de socios de la actividad
     * @param socio socio que se añade a la actividad
     * @return true si ha podido añadirlo y false si no ha podido ser añadido
     */
    public boolean addSocio(Socio socio) {
        boolean result = false;
        if(socio!=null && sociosActividad!=null && sociosActividad.length>0 && this.existeSocio(socio)==-1) {
            for(int i=0; i<sociosActividad.length && !result; i++) {
                if(sociosActividad[i]==null){
                    sociosActividad[i] = socio;
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * elimina un socio de la actividad
     * @param socio socio eliminado
     * @return true si lo ha eliminado, false si no existe y no ha podido eliminarlo
     */
    public boolean removeSocio(Socio socio) {
        boolean result = false;
        if(this.existeSocio(socio)!=-1) {
            this.sociosActividad[this.existeSocio(socio)] = null;
            result = true;
        }
        return result;
    }

    /**
     * metodo que devulve la lista de socios inscritos
     * @return el array con los socios inscritos en la actividad
     */
    public Socio[] getSociosActividad() {
        return sociosActividad;
    }

    /**
     * método que cuenta los socios que tiene el array de socios de la actividad
     * @return numero de socios
     */
    public int numSociosActividad() {
        int cont=0;
        for(int i=0; i<sociosActividad.length; i++) {
            if(sociosActividad[i]!=null) {
                cont++;
            }
        }
        return cont;
    }
}