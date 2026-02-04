import controller.CentroDeportivoController;
import model.CentroDeportivo;
import view.VistaConsola;

public class Main {
    public static void main(String[] args) {

        // Capa MODELO
        // creamos un centro deportivo (centro1) de 20 actividades y 20 socios
        CentroDeportivo centro1 = new CentroDeportivo(20, 50);

        // Capa CONTROLADOR
        //creamos el controlador para
        CentroDeportivoController controladorCentro1 = new CentroDeportivoController(centro1);

        // Capa VISTA
        VistaConsola vista = new VistaConsola(controladorCentro1);

        // Lanzamos bucle principal
        vista.iniciar();
    }
}