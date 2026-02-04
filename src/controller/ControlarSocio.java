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

    private CentroDeportivo centro;

    public ControlarSocio(CentroDeportivo centro) {
        this.centro = centro;
    }

    private boolean existeSocio(int id){
        boolean existe = false;
        Socio[] socios = centro.getSocios();  //estoy pidiendo el array de socios del CentroDeportivo.


        for (int i = 0; i < socios.length; i++) {
            if (socios[i] != null && socios[i].equals(id)) {
                existe = true;
            }
        }
            return existe;
    }
}


