package view;

import controller.CentroDeportivoController;
import controller.ControlarActividad;
import controller.ControlarSocio;
import model.Actividad;
import model.Socio;
import utils.InputUtils;
import utils.InputUtils;

public class VistaConsola {

    private ControlarSocio controladorSocio;
    private ControlarActividad controladorActividad;

    public VistaConsola(ControlarSocio controladorSocio, ControlarActividad controladorActividad) {
        this.controladorSocio = controladorSocio;
        this.controladorActividad = controladorActividad;
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = utils.InputUtils.leeEntero("Elige una opción: ");
            System.out.println();
            switch (opcion) {
                case 1 -> menuSocios();
                case 2 -> menuActividades();
                case 3 -> menuInscripciones();
                case 4 -> menuCuotas();
                case 5 -> mostrarInformes();
                case 0 -> System.out.println("Saliendo de la aplicación...");
                default -> System.out.println("Opción no válida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\u001B[34m");
        System.out.println("=============================");
        System.out.println("      CENTRO DEPORTIVO       ");
        System.out.println("=============================");
        System.out.println("\u001B[0m");

        System.out.println("\u001B[33m");
        System.out.println("1. Gestión de socios");
        System.out.println("2. Gestión de actividades");
        System.out.println("3. Inscripciones");
        System.out.println("4. Cuotas");
        System.out.println("5. Informes");
        System.out.println("0. Salir");
        System.out.println("\u001B[0m");
    }

    // --------- SOCIOS ---------

    private void menuSocios() {
        int opcion;
        do {

            System.out.println("\u001B[33m");
            System.out.println("----- Gestión de socios -----");
            System.out.println("1. Alta socio");
            System.out.println("2. Listar socios");
            System.out.println("3. Buscar socio por ID");
            System.out.println("4. Buscar socio por DNI");
            System.out.println("5. Eliminar socio");
            System.out.println("0. Volver");
            System.out.println("\u001B[0m");
            opcion = utils.InputUtils.leeEntero("Opción: ");

            switch (opcion) {
                case 1 -> altaSocio();
                case 2 -> listarSocios();
                case 3 -> buscarSocioPorId();
                case 4 -> buscarSocioPorDni();
                case 5 -> eliminarSocio();
                case 0 -> {
                }
                default -> System.out.println("Opción no válida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void altaSocio() {
        String nombre = utils.InputUtils.leerCadenaNoVacia("Nombre: ");
        String dni = utils.InputUtils.leerCadenaNoVacia("DNI: ");
        int edad = utils.InputUtils.leeEntero("Edad: ");

        boolean ok = controladorSocio.registrarSocio(nombre, dni, edad);
        if (ok) {
            System.out.println("✅ Socio registrado correctamente.");
        } else {
            System.out.println("❌ No se ha podido registrar (quizá DNI duplicado o sin espacio).");
        }
    }

    private void listarSocios() {
        Socio[] socios = controladorSocio.listarSocios();
        if (socios == null) {
            System.out.println("No hay socios registrados.");
            return;
        }
        System.out.println("=== Lista de socios ===");
        for (int i = 0; i < socios.length; i++) {
            if (socios[i] != null) {
                System.out.println("ID " + i + " -> " + socios[i]);
            }
        }
    }

    private void buscarSocioPorId() {
        int id = utils.InputUtils.leeEntero("ID socio: ");
        Socio socio = controladorSocio.buscarSocioPorId(id);
        if (socio == null) {
            System.out.println("No existe socio con ese ID.");
        } else {
            System.out.println("Socio encontrado: " + socio);
        }
    }

    private void buscarSocioPorDni() {
        String dni = utils.InputUtils.leerCadenaNoVacia("DNI del socio: ");
        Socio socio = controladorSocio.buscarSocioPorDni(dni);
        if (socio == null) {
            System.out.println("No existe socio con ese DNI.");
        } else {
            System.out.println("Socio encontrado: " + socio);
        }
    }

    private void eliminarSocio() {
        int id = utils.InputUtils.leeEntero("ID del socio a eliminar: ");
        boolean ok = controladorSocio.eliminarSocio(id);
        if (ok) {
            System.out.println("✅ Socio eliminado (y desinscrito de todas las actividades).");
        } else {
            System.out.println("❌ No se ha podido eliminar el socio.");
        }
    }

    // --------- ACTIVIDADES ---------

    private void menuActividades() {
        int opcion;
        do {

            System.out.println("\u001B[33m");
            System.out.println("----- Gestión de actividades -----");
            System.out.println("1. Alta actividad");
            System.out.println("2. Listar actividades");
            System.out.println("3. Buscar actividad por ID");
            System.out.println("4. Buscar actividad por nombre");
            System.out.println("5. Eliminar actividad");
            System.out.println("0. Volver");
            System.out.println("\u001B[0m");
            opcion = utils.InputUtils.leeEntero("Opción: ");

            switch (opcion) {
                case 1 -> altaActividad();
                case 2 -> listarActividades();
                case 3 -> buscarActividadPorId();
                case 4 -> buscarActividadPorNombre();
                case 5 -> eliminarActividad();
                case 0 -> {
                }
                default -> System.out.println("Opción no válida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void altaActividad() {
        String nombre = utils.InputUtils.leerCadenaNoVacia("Nombre actividad: ");
        int duracion = utils.InputUtils.leeEntero("Duración en minutos: ");
        String nivel = utils.InputUtils.leerCadenaNoVacia("Nivel (iniciación, intermedio, avanzado): ");
        double precio = utils.InputUtils.leeDouble("Precio mensual: ");
        int numMiembros = utils.InputUtils.leeEntero("Número máximo de socios: ");

        boolean ok = controladorActividad.registrarActividad(nombre, duracion, nivel, precio, numMiembros);
        if (ok) {
            System.out.println("✅ Actividad registrada correctamente.");
        } else {
            System.out.println("❌ No se ha podido registrar la actividad (quizá ya exista).");
        }
    }

    private void listarActividades() {
        Actividad[] actividades = controladorActividad.listarActividades();
        if (actividades == null) {
            System.out.println("No hay actividades registradas.");
            return;
        }
        System.out.println("=== Lista de actividades ===");
        for (int i = 0; i < actividades.length; i++) {
            if (actividades[i] != null) {
                System.out.println("ID " + i + " -> " + actividades[i]);
            }
        }
    }

    private void buscarActividadPorId() {
        int id = utils.InputUtils.leeEntero("ID actividad: ");
        Actividad a = controladorActividad.buscarActividadPorId(id);
        if (a == null) {
            System.out.println("No existe actividad con ese ID.");
        } else {
            System.out.println("Actividad encontrada: " + a);
        }
    }

    private void buscarActividadPorNombre() {
        String nombre = utils.InputUtils.leerCadenaNoVacia("Nombre actividad: ");
        Actividad a = controladorActividad.buscarActividadPorNombre(nombre);
        if (a == null) {
            System.out.println("No existe actividad con ese nombre.");
        } else {
            System.out.println("Actividad encontrada: " + a);
        }
    }

    private void eliminarActividad() {
        int id = utils.InputUtils.leeEntero("ID de la actividad a eliminar: ");
        boolean ok = controladorActividad.eliminarActividad(id);
        if (ok) {
            System.out.println("✅ Actividad eliminada y socios actualizados.");
        } else {
            System.out.println("❌ No se ha podido eliminar la actividad.");
        }
    }

    // --------- INSCRIPCIONES ---------

    private void menuInscripciones() {
        int opcion;
        do {

            System.out.println("\u001B[33m");
            System.out.println("----- Inscripciones -----");
            System.out.println("1. Inscribir socio en actividad");
            System.out.println("2. Dar de baja socio de actividad");
            System.out.println("3. Ver actividades de un socio");
            System.out.println("4. Ver socios de una actividad");
            System.out.println("0. Volver");
            System.out.println("\u001B[0m");
            opcion = utils.InputUtils.leeEntero("Opción: ");

            switch (opcion) {
                case 1 -> inscribirSocioEnActividad();
                case 2 -> bajaSocioDeActividad();
                case 3 -> verActividadesDeSocio();
                case 4 -> verSociosDeActividad();
                case 0 -> {
                }
                default -> System.out.println("Opción no válida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void inscribirSocioEnActividad() {
        int idSocio = utils.InputUtils.leeEntero("ID socio: ");
        int idActividad = utils.InputUtils.leeEntero("ID actividad: ");
        boolean ok = controladorSocio.inscribirSocioEnActividad(idSocio, idActividad);
        if (ok) {
            System.out.println("✅ Inscripción realizada correctamente.");
        } else {
            System.out.println("❌ No se ha podido inscribir (datos incorrectos o ya inscrito).");
        }
    }

    private void bajaSocioDeActividad() {
        int idSocio = utils.InputUtils.leeEntero("ID socio: ");
        int idActividad = utils.InputUtils.leeEntero("ID actividad: ");
        boolean ok = controladorSocio.darDeBajaSocioDeActividad(idSocio, idActividad);
        if (ok) {
            System.out.println("✅ Baja realizada correctamente.");
        } else {
            System.out.println("❌ No se ha podido dar de baja.");
        }
    }

    private void verActividadesDeSocio() {
        int idSocio = utils.InputUtils.leeEntero("ID socio: ");
        Actividad[] actividades = controladorSocio.obtenerActividadesDeSocio(idSocio);
        if (actividades == null) {
            System.out.println("Socio no encontrado.");
            return;
        }
        System.out.println("Actividades del socio " + idSocio + ":");
        boolean alguna = false;
        for (Actividad a : actividades) {
            if (a != null) {
                System.out.println("- " + a);
                alguna = true;
            }
        }
        if (!alguna) {
            System.out.println("El socio no está inscrito en ninguna actividad.");
        }
    }

    private void verSociosDeActividad() {
        int idActividad = utils.InputUtils.leeEntero("ID actividad: ");
        Socio[] socios = controladorActividad.obtenerSociosDeActividad(idActividad);
        if (socios == null) {
            System.out.println("Actividad no encontrada.");
            return;
        }
        System.out.println("Socios inscritos en la actividad " + idActividad + ":");
        boolean alguno = false;
        for (Socio s : socios) {
            if (s != null) {
                System.out.println("- " + s);
                alguno = true;
            }
        }
        if (!alguno) {
            System.out.println("La actividad no tiene socios inscritos.");
        }
    }

    // --------- CUOTAS ---------

    private void menuCuotas() {
        int opcion;
        do {

            System.out.println("\u001B[33m");
            System.out.println("----- Cuotas -----");
            System.out.println("1. Ver cuota de un mes");
            System.out.println("2. Marcar mes como pagado");
            System.out.println("3. Marcar mes como pendiente");
            System.out.println("4. Ver total pagado anual");
            System.out.println("5. Ver total pendiente anual");
            System.out.println("6. Ver estado de un mes");
            System.out.println("7. Listar meses pendientes");
            System.out.println("8. Listar meses pagados");
            System.out.println("0. Volver");
            System.out.println("\u001B[0m");
            opcion = utils.InputUtils.leeEntero("Opción: ");

            switch (opcion) {
                case 1 -> verCuotaMes();
                case 2 -> marcarMes(true);
                case 3 -> marcarMes(false);
                case 4 -> verTotalPagado();
                case 5 -> verTotalPendiente();
                case 6 -> verEstadoMes();
                case 7 -> listarMesesPendientes();
                case 8 -> listarMesesPagados();
                case 0 -> {
                }
                default -> System.out.println("Opción no válida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void verCuotaMes() {
        int idSocio = utils.InputUtils.leeEntero("ID socio: ");
        int mes = utils.InputUtils.leeEntero("Mes (1-12): ", 1, 12);
        double cuota = controladorSocio.calcularCuotaMensualSocio(idSocio, mes);
        System.out.println("Cuota del socio " + idSocio + " en el mes " + mes + " = " + cuota + " €");
    }

    private void marcarMes(boolean pagado) {
        int idSocio = utils.InputUtils.leeEntero("ID socio: ");
        int mes = utils.InputUtils.leeEntero("Mes (1-12): ", 1, 12);
        boolean ok = pagado ? controladorSocio.marcarCuotaPagada(idSocio, mes)
                : controladorSocio.marcarCuotaPendiente(idSocio, mes);
        if (ok) {
            System.out.println("✅ Estado actualizado correctamente.");
        } else {
            System.out.println("❌ No se ha podido actualizar el estado.");
        }
    }

    private void verTotalPagado() {
        int idSocio = utils.InputUtils.leeEntero("ID socio: ");
        double total = controladorSocio.obtenerTotalPagadoAnual(idSocio);
        System.out.println("Total pagado en el año por el socio " + idSocio + " = " + total + " €");
    }

    private void verTotalPendiente() {
        int idSocio = utils.InputUtils.leeEntero("ID socio: ");
        double total = controladorSocio.obtenerTotalPendienteAnual(idSocio);
        System.out.println("Total pendiente en el año por el socio " + idSocio + " = " + total + " €");
    }

    private void verEstadoMes() {
        int idSocio = utils.InputUtils.leeEntero("ID socio: ");
        int mes = utils.InputUtils.leeEntero("Mes (1-12): ", 1, 12);
        String estado = controladorSocio.obtenerEstadoPagoMes(idSocio, mes);
        System.out.println("Estado del mes " + mes + ": " + estado);
    }

    private void listarMesesPendientes() {
        int idSocio = utils.InputUtils.leeEntero("ID socio: ");
        int[] meses = controladorSocio.obtenerMesesPendientes(idSocio);
        if (meses == null || meses.length == 0) {
            System.out.println("No hay meses pendientes o socio no encontrado.");
        } else {
            System.out.print("Meses pendientes: ");
            for (int m : meses) {
                System.out.print(m + " ");
            }
            System.out.println();
        }
    }

    private void listarMesesPagados() {
        int idSocio = utils.InputUtils.leeEntero("ID socio: ");
        int[] meses = controladorSocio.obtenerMesesPagados(idSocio);
        if (meses == null || meses.length == 0) {
            System.out.println("No hay meses pagados o socio no encontrado.");
        } else {
            System.out.print("Meses pagados: ");
            for (int m : meses) {
                System.out.print(m + " ");
            }
            System.out.println();
        }
    }

    // --------- INFORMES ---------

    private void mostrarInformes() {
        System.out.println("----- Informes -----");
        Socio s = controladorSocio.socioConMasActividades();
        if (s != null) {
            System.out.println("Socio con más actividades: " + s);
        } else {
            System.out.println("No hay socios para informar.");
        }

        Actividad a = controladorActividad.actividadMasPopular();
        if (a != null) {
            System.out.println("Actividad más popular: " + a);
        } else {
            System.out.println("No hay actividades para informar.");
        }
    }
}