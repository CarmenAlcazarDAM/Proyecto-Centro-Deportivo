package model;

public class CentroDeportivo {
    private Socio[] misSocios;
    private Actividad[] misActividades;
    private int numMaxActividades;
    private int numMaxSocios;

    public CentroDeportivo(int numMaxActividades, int numMaxSocios) {
        this.numMaxActividades = numMaxActividades;
        this.numMaxSocios = numMaxSocios;
        misSocios = new Socio[numMaxSocios];
        misActividades = new Actividad[numMaxActividades];
    }

    public Socio[] getSocios() { return misSocios; }
    public Actividad[] getActividades() { return misActividades; }

    //funcionalidades  de actividad

    /**
     * Metodo que te indica la posición de una actividad en el array
     * @param actividad Actividad a comprobar
     * @return -1 si no existe la actividad, y la posición de la misma si SÍ existe
     */
    public int existeActividad(Actividad actividad) {
        if (misActividades != null && misActividades.length > 0) {
            for (int i = 0; i < misActividades.length; i++) {
                if (misActividades[i] != null && misActividades[i].equals(actividad)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * añade una actividad al array de ACTIVIDADES DEL CENTRO DEPORTIVO
     * @param actividad Actividad a añadir
     * @return TRUE: si se ha añadido con existe, FALSE: si no se ha podido añadir
     */
    public boolean addActividad(Actividad actividad) {
        boolean result = false;
        if (actividad != null && misActividades != null && misActividades.length > 0 && this.existeActividad(actividad) == -1) {
            for (int i = 0; i < misActividades.length && !result; i++) {
                if (misActividades[i] == null) {
                    misActividades[i] = actividad;
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Metodo que busca una actividad por su id
     * @param id ID de la actividad
     * @return null si no existe la actividad con ese id, el objeto actividad si existe
     */
    public Actividad getActividad(int id) {
        Actividad actividad = null;
        if ( id >= 0 && id < this.misActividades.length && this.existeActividad(misActividades[id]) != -1) {
            actividad = misActividades[id];
        }
        return actividad;
    }

    /**
     * Metodo que busca una actividad por su nombre y la devuelve
     * @param nombre Nombre de la actividad
     * @return null si no existe la actividad con ese nombre, el objeto actividad si existe
     */
    public Actividad getActividad(String nombre) {
        Actividad actividad = null;
        for(int i = 0; i < misActividades.length; i++) {
            if (misActividades[i]!=null && misActividades[i].getNombre().equals(nombre)) {
                actividad = misActividades[i];
            }
        }
        return actividad;
    }

    //funcionalidades de socio
    /**
     * Método que comprueba si existe un socio en el array de socios de  la actividad
     * @param socio objeto socio que busca en el array
     * @return posicion del socio si existe, -1 si no existe
     */
    public int existeSocio(Socio socio) {

        if(socio!=null && misSocios!=null && misSocios.length>0) {
            for(int i=0; i<misSocios.length; i++) {
                if(misSocios[i]!=null && misSocios[i].equals(socio)) {
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
        if(socio!=null && misSocios!=null && misSocios.length>0 && this.existeSocio(socio)==-1) {
            for(int i=0; i<misSocios.length && !result; i++) {
                if(misSocios[i]==null){
                    misSocios[i] = socio;
                    result = true;
                }
            }
        }
        return result;
    }
    /**
     * Metodo que busca un socio por su id
     * @param id ID del socio
     * @return null si no existe el socio con ese id, el objeto socio si existe
     */
    public Socio getSocio(int id) {
        Socio socio = null;
        if (id >= 0 && id < this.misSocios.length && this.existeSocio(misSocios[id]) != -1) {
            socio = misSocios[id];
        }
        return socio;
    }

    /**
     * Metodo que busca un socio por su id
     * @param dni DNI del socio
     * @return null si no existe el socio con ese id, el objeto socio si existe
     */
    public Socio getSocio(String dni) {
        Socio socio= null;
        for(int i = 0; i < misSocios.length; i++) {
            if (misSocios[i]!=null && misSocios[i].getDni().equals(dni)) {
                socio = misSocios[i];
            }
        }
        return socio;
    }



}