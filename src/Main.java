import controller.ControlarActividad;
import controller.ControlarSocio;
import model.CentroDeportivo;

public class Main {

    public static  void main(String[] args){

        //Creamos el centro deportivo
        CentroDeportivo miCentro = new CentroDeportivo();

        //Creamos los controladores --> le pasamos <miCentro>
        ControlarSocio controlSocio = new ControlarSocio(miCentro);
        ControlarActividad controlActividad = new ControlarActividad(miCentro);
    }
}
