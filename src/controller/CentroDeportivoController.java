package controller;

import model.Actividad;
import model.CentroDeportivo;
import model.Socio;

public class CentroDeportivoController {

    private CentroDeportivo miCentro;

    public CentroDeportivoController(CentroDeportivo miCentro) {
        this.miCentro = miCentro;
    }

    // ========= SOCIOS =========

    public boolean registrarSocio(String nombre, String dni, int edad) {
        // Comprobación por DNI para evitar duplicados
        boolean result = false;  //partimos de que no se puede añadir
        if ((miCentro.getSocio(dni) == null)) {  //comprobamos si existe un socio con el DNI, si no existe
            Socio socio = new Socio(nombre, dni, edad);
            return miCentro.addSocio(socio);  //devuelve true si lo ha podido añadir a miCentro
        }
        return result;

    }

    public Socio buscarSocioPorId(int id) {
        return miCentro.getSocio(id);
    }

    public Socio buscarSocioPorDni(String dni) {
        return miCentro.getSocio(dni);
    }

    /**
     * Devuelve todos los socios (puede contener nulls).
     */
    public Socio[] listarSocios() {
        return miCentro.getSocios();
    }

    /**
     * Elimina un socio por "id" (que en tu diseño coincide con el índice del array).
     * Además lo desinscribe de todas las actividades.
     */
    public boolean eliminarSocio(int idSocio) {
        boolean result = false;
        Socio[] socios = miCentro.getSocios(); //sacamos el array de socios de mi centro
        if (socios != null && idSocio >= 0 && idSocio <= socios.length && socios[idSocio] != null) {
            Socio socio = socios[idSocio];
            // Quitar al socio de todas las actividades en las que esté
            Actividad[] actividades = miCentro.getActividades();
            if (actividades != null) {
                for (Actividad a : actividades) {
                    if (a != null) {
                        a.removeSocio(socio);
                    }
                }
            }
            //eliminamos al socio de la lista de socios de miCentro
            socios[idSocio] = null;
            result = true;
        }
        return result;
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

    public boolean inscribirSocioEnActividad(int idSocio, int idActividad) {
        Socio socio = miCentro.getSocio(idSocio);
        Actividad actividad = miCentro.getActividad(idActividad);

        if (socio == null || actividad == null) {
            return false;
        }

        boolean okSocio = socio.addActividad(actividad);
        boolean okActividad = actividad.addSocio(socio);

        if (okSocio && okActividad) {
            socio.recalcularCoutasDesdeMesActual();
            return true;
        } else {
            // Deshacer si una de las dos inserciones ha fallado
            if (okSocio) {
                socio.removeActividad(actividad);
            }
            if (okActividad) {
                actividad.removeSocio(socio);
            }
            return false;
        }
    }

    public boolean darDeBajaSocioDeActividad(int idSocio, int idActividad) {
        Socio socio = miCentro.getSocio(idSocio);
        Actividad actividad = miCentro.getActividad(idActividad);

        if (socio == null || actividad == null) {
            return false;
        }

        boolean okSocio = socio.removeActividad(actividad);
        boolean okActividad = actividad.removeSocio(socio);

        if (okSocio && okActividad) {
            socio.recalcularCoutasDesdeMesActual();
            return true;
        } else {
            return false;
        }
    }

    public Actividad[] obtenerActividadesDeSocio(int idSocio) {
        Socio socio = miCentro.getSocio(idSocio);
        if (socio == null) {
            return null;
        }
        return socio.getActividadesInscritas();
    }

    public Socio[] obtenerSociosDeActividad(int idActividad) {
        Actividad actividad = miCentro.getActividad(idActividad);
        if (actividad == null) {
            return null;
        }
        return actividad.getSociosActividad();
    }

    // ========= CUOTAS =========

    public double calcularCuotaMensualSocio(int idSocio, int mes) {
        Socio socio = miCentro.getSocio(idSocio);
        if (socio == null) {
            return 0.0;
        }
        return socio.getCuotaMes(mes);
    }

    public boolean marcarCuotaPagada(int idSocio, int mes) {
        Socio socio = miCentro.getSocio(idSocio);
        if (socio == null) {
            return false;
        }
        return socio.cambiarEstadoPagoMes(mes, true);
    }

    public boolean marcarCuotaPendiente(int idSocio, int mes) {
        Socio socio = miCentro.getSocio(idSocio);
        if (socio == null) {
            return false;
        }
        return socio.cambiarEstadoPagoMes(mes, false);
    }

    public double obtenerTotalPagadoAnual(int idSocio) {
        Socio socio = miCentro.getSocio(idSocio);
        if (socio == null) {
            return 0.0;
        }
        return socio.getTotalPagadoAnual();
    }

    public double obtenerTotalPendienteAnual(int idSocio) {
        Socio socio = miCentro.getSocio(idSocio);
        if (socio == null) {
            return 0.0;
        }
        return socio.getTotalPendienteAnual();
    }

    public String obtenerEstadoPagoMes(int idSocio, int mes) {
        Socio socio = miCentro.getSocio(idSocio);
        if (socio == null) {
            return "Socio no encontrado";
        }
        return socio.getEstadoPagoMes(mes);
    }

    public int[] obtenerMesesPendientes(int idSocio) {
        Socio socio = miCentro.getSocio(idSocio);
        if (socio == null) {
            return null;
        }
        return socio.getMesesPendientesArray();
    }

    public int[] obtenerMesesPagados(int idSocio) {
        Socio socio = miCentro.getSocio(idSocio);
        if (socio == null) {
            return null;
        }
        return socio.getMesesPagadosArray();
    }

    // ========= INFORMES SENCILLOS =========

    public Socio socioConMasActividades() {
        Socio[] socios = miCentro.getSocios();
        if (socios == null) return null;

        Socio maxSocio = null;
        int maxAct = -1;

        for (Socio s : socios) {
            if (s != null) {
                int cont = 0;
                Actividad[] acts = s.getActividadesInscritas();
                if (acts != null) {
                    for (Actividad a : acts) {
                        if (a != null) cont++;
                    }
                }
                if (cont > maxAct) {
                    maxAct = cont;
                    maxSocio = s;
                }
            }
        }
        return maxSocio;
    }

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