package model;

public class Socio {

    final int CUOTAS = 12;

    private int idSocio;
    private String dni;
    private String nombre;
    private int edad;
    private Actividad[] actividadesInscritas;
    private double[] cuotasMensuales = new double[CUOTAS];
    private boolean[] cuotasPagadas = new boolean[CUOTAS];

    public Socio (int idSocio, String dni, String nombre, int edad){
        this.idSocio=idSocio;
        this.dni=dni;
        this.nombre=nombre;
        this.edad=edad;
    }

    public int getCUOTAS() {
        return CUOTAS;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public double[] getCuotasMensuales() {
        return cuotasMensuales;
    }

    public void setCuotasMensuales(double[] cuotasMensuales) {
        this.cuotasMensuales = cuotasMensuales;
    }

    public boolean[] getCuotasPagadas() {
        return cuotasPagadas;
    }

    public void setCuotasPagadas(boolean[] cuotasPagadas) {
        this.cuotasPagadas = cuotasPagadas;
    }
}
