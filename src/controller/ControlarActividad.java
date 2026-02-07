package controller;
/*
Referencia a CentroDeportivo

registrarActividad()
buscarActividadPorId()
listarSociosDeActividad(idActividad)
eliminarActividad(idActividad)
 */


import model.Actividad;
import model.CentroDeportivo;
import model.Socio;

public class ControlarActividad {
    private CentroDeportivo miCentro;

    public ControlarActividad(CentroDeportivo miCentro) {
        this.miCentro = miCentro;
    }
    // ========= ACTIVIDADES =========

    /**
     * Registra una actividad si no existe otra con el mismo nombre y nivel
     * @param nombre Nombre de la actividad
     * @param duracionMinutos Duración (en minutos)
     * @param nivel Nivel de la actividad
     * @param precioMensual Precio mensual
     * @param numMiembros Número de miembros permitidos
     * @return true si la ha registrado, false si no la ha registrado
     */
    public boolean registrarActividad(String nombre, int duracionMinutos, String nivel, double precioMensual, int numMiembros) {

        // Creamos una actividad "temporal" que usaremos para comparar
        Actividad nueva = new Actividad(nombre, duracionMinutos, nivel, precioMensual, numMiembros);

        // Revisamos todas las actividades ya registradas

        for (Actividad a : miCentro.getActividades()) {
            if (a != null && a.equals(nueva)) {   // aquí usamos equals() para saber si la nueva actividad es igual a alguna de las que ya había
                //recuerda, que dos actividades son iguales si tienen el mismo nombre y el mismo nivel
                return false;  // ya existe actividad igual (mismo nombre y mismo nivel)
            }
        }

        // Si no existe ninguna igual, la añadimos
        return miCentro.addActividad(nueva);
    }
    public Actividad buscarActividadPorId(int idActividad) {
        return miCentro.getActividad(idActividad);
    }

    public Actividad buscarActividadPorNombre(String nombre) {
        return miCentro.getActividad(nombre);
    }
    /**
     * Devuelve todas las actividades en un array

     */
    public Actividad[] listarActividades() {
        return miCentro.getActividades();
    }

    /**
     * Elimina una actividad (la borra del array y la quita de todos los socios).
     */
    public boolean eliminarActividad(int idActividad) {
        boolean result = false;
        Actividad[] actividades = miCentro.getActividades(); // sacamos el array de actividades

        // Comprobación de índice + null
        if (actividades != null && idActividad >= 0 && idActividad < actividades.length && actividades[idActividad] != null) {
            Actividad actividad = actividades[idActividad];

            // Quitamos esta actividad de todos los socios

            if (miCentro.getSocios() != null) {
                for (Socio s : miCentro.getSocios()) {
                    if (s != null) {
                        s.removeActividad(actividad);
                        s.recalcularCoutasDesdeMesActual(); // mismo efecto que en tu controlador
                    }
                }
            }

            // Eliminamos la actividad del array del centro
            actividades[idActividad] = null;

            result = true;
        }

        return result;
    }


    // ========= INSCRIPCIONES =========

    public Socio[] obtenerSociosDeActividad(int idActividad) {
        Actividad actividad = miCentro.getActividad(idActividad);
        if (actividad == null) {
            return null;
        }
        return actividad.getSociosActividad();
    }

    // ========= INFORMES SENCILLOS =========

    public Actividad actividadMasPopular() {
        Actividad[] actividades = miCentro.getActividades();
        if (actividades == null) return null;

        Actividad maxAct = null;
        int maxSocios = -1;

        for (Actividad a : actividades) {
            if (a != null) {
                int num = a.numSociosActividad();
                if (num > maxSocios) {
                    maxSocios = num;
                    maxAct = a;
                }
            }
        }
        return maxAct;
    }
}
