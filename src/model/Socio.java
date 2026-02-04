package model;


import java.time.LocalDate;
import java.util.Objects;

public class Socio {
    private static final int MAX_ACTIVIDADES = 5;

    private int idSocio;
    private String nombre;
    private String dni;
    private int edad;
    private Actividad[] actividadesInscritas;
    private double[] coutasMensuales;
    private boolean[] coutasPagadas;
    private static int contID = 0;


    public Socio(String nombre, String dni, int edad) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.idSocio = contID;
        contID++;
        actividadesInscritas = new Actividad[MAX_ACTIVIDADES];
        coutasMensuales = new double[12];
        coutasPagadas = new boolean[12];
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Actividad[] getActividadesInscritas() {
        return actividadesInscritas;
    }

    public void setActividadesInscritas(Actividad[] actividadesInscritas) {
        this.actividadesInscritas = actividadesInscritas;
    }

    public double[] getCoutasMensuales() {
        return coutasMensuales;
    }

    public void setCoutasMensuales(double[] coutasMensuales) {
        this.coutasMensuales = coutasMensuales;
    }

    public boolean[] getCoutasPagadas() {
        return coutasPagadas;
    }

    public void setCoutasPagadas(boolean[] coutasPagadas) {
        this.coutasPagadas = coutasPagadas;
    }

    @Override
    public String toString() {
        return "Socio{" +
                "idSocio=" + idSocio +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", edad=" + edad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socio socio = (Socio) o;
        return Objects.equals(dni, socio.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSocio, dni);
    }


    //funcionalidades
    public int existeActividad(Actividad actividad) {
        if (actividadesInscritas != null && actividadesInscritas.length > 0) {
            for (int i = 0; i < actividadesInscritas.length; i++) {
                if (actividadesInscritas[i] != null && actividadesInscritas[i].equals(actividad)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean addActividad(Actividad actividad) {
        boolean result = false;
        if (actividad != null && actividadesInscritas != null && actividadesInscritas.length > 0 && this.existeActividad(actividad) == -1) {
            for (int i = 0; i < actividadesInscritas.length && !result; i++) {
                if (actividadesInscritas[i] == null) {
                    actividadesInscritas[i] = actividad;
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean removeActividad(Actividad actividad) {
        boolean result = false;
        if (this.existeActividad(actividad) != -1) {
            this.actividadesInscritas[this.existeActividad(actividad)] = null;
            result = true;
        }
        return result;
    }

    // calcula la cuota mensual del socio según sus actividades actuales
    private double calcularCuotaMensualActual() {
        double total = 0.0;

        if (actividadesInscritas != null) {
            for (Actividad a : actividadesInscritas) {
                if (a != null) {
                    total += a.getPrecioMensual();
                }
            }
        }

        return total;
    }

    /**
     * Recalcula las coutasMensuales desde el mes actual hasta el final del año.
     * No modifica las cuotas de meses anteriores, aunque estén sin pagar.
     */
    public void recalcularCoutasDesdeMesActual() {
        if (this.coutasMensuales != null && this.coutasMensuales.length > 0) {
            // LocalDate.now().getMonthValue() devuelve 1..12
            // Nuestro array probablemente es 0..11 (enero = 0, diciembre = 11)
            int mesActual = LocalDate.now().getMonthValue() - 1;

            double nuevaCuotaMensual = this.calcularCuotaMensualActual();

            // Recalcular desde el mes actual hasta el final del array
            for (int i = mesActual; i < this.coutasMensuales.length; i++) {
                this.coutasMensuales[i] = nuevaCuotaMensual;
            }
        }
    }

    public boolean cambiarEstadoPagoMes(int mes, boolean estado) {
        if (coutasMensuales != null && mes >= 1 && mes <= 12) {
            this.coutasPagadas[mes - 1] = estado;
            return true;
        }
        return false;
    }

    /**
     * Devuelve la cuota de un mes concreto.
     *
     * @param mes número de mes (1 = enero, 12 = diciembre)
     * @return importe de la cuota de ese mes, o 0.0 si el mes es inválido
     */
    public double getCuotaMes(int mes) {
        if (coutasMensuales == null || mes < 1 || mes > coutasMensuales.length) {
            return 0.0;
        }
        return coutasMensuales[mes - 1];
    }

    /**
     * Devuelve el total anual ya pagado.
     */
    public double getTotalPagadoAnual() {
        double total = 0.0;

        if (coutasMensuales != null && coutasPagadas != null) {
            for (int i = 0; i < coutasMensuales.length; i++) {
                if (coutasPagadas[i]) {
                    total += coutasMensuales[i];
                }
            }
        }

        return total;
    }

    /**
     * Devuelve el total anual pendiente de pago.
     */
    public double getTotalPendienteAnual() {
        double total = 0.0;

        if (coutasMensuales != null && coutasPagadas != null) {
            for (int i = 0; i < coutasMensuales.length; i++) {
                if (!coutasPagadas[i]) {
                    total += coutasMensuales[i];
                }
            }
        }

        return total;
    }

    /**
     * Devuelve una cadena con el estado de pago de un mes concreto.
     *
     * @param mes número de mes (1 = enero, 12 = diciembre)
     * @return "Pagado", "Pendiente" o "Mes inválido"
     */
    public String getEstadoPagoMes(int mes) {
        if (coutasPagadas == null || mes < 1 || mes > coutasPagadas.length) {
            return "Mes inválido";
        }
        return coutasPagadas[mes - 1] ? "Pagado" : "Pendiente";
    }

    /**
     * Devuelve un array con los números de los meses pendientes de pago.
     * Ejemplo: [1, 3, 4] si enero, marzo y abril están pendientes.
     * Si no hay meses pendientes devuelve null
     */
    public int[] getMesesPendientesArray() {
        if (coutasPagadas == null) {
            return null;
        }

        // Primero contamos cuántos meses pendientes hay
        int count = 0;
        for (int i = 0; i < coutasPagadas.length; i++) {
            if (!coutasPagadas[i]) {
                count++;
            }
        }

        // Creamos el array del tamaño exacto
        int[] pendientes = new int[count];

        // Rellenamos el array
        int index = 0;
        for (int i = 0; i < coutasPagadas.length; i++) {
            if (!coutasPagadas[i]) {
                pendientes[index] = i + 1; // +1 para convertir 0..11 → 1..12
                index++;
            }
        }

        return pendientes;
    }

    /**
     * Devuelve un array con los números de los meses pagados.
     * Ejemplo: [2, 5] si febrero y mayo están pagados.
     */
    public int[] getMesesPagadosArray() {
        if (coutasPagadas == null) {
            return new int[0];
        }

        // Contar meses pagados
        int count = 0;
        for (int i = 0; i < coutasPagadas.length; i++) {
            if (coutasPagadas[i]) {
                count++;
            }
        }

        // Crear array del tamaño exacto
        int[] pagados = new int[count];

        // Rellenar
        int index = 0;
        for (int i = 0; i < coutasPagadas.length; i++) {
            if (coutasPagadas[i]) {
                pagados[index] = i + 1; // +1 para devolver mes 1..12
                index++;
            }
        }

        return pagados;
    }

}