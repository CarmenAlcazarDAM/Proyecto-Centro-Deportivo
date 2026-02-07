package controller;
import model.Socio;
import model.Actividad;
import model.CentroDeportivo;

/*
registrarSocio()
buscarSocioPorId()
inscribirSocioEnActividad(id Socio, idActividad)
darDeBajaSocioDeActividad(idSocio, idActividad)
calcularCuotaMensualSocio(id Socio, mes)
marcarCuotaPagada(idSocio, mes)
obtenerActividadesDeSocio(idSocio)
 */

public class ControlarSocio {

    private CentroDeportivo miCentro;

    public ControlarSocio(CentroDeportivo miCentro) {
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

}