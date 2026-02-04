package view;

import controller.CentroDeportivoController;
import model.Actividad;
import model.Socio;
import util.InputUtils;

public class VistaConsola {

    private CentroDeportivoController controlador;

    public VistaConsola(CentroDeportivoController controlador) {
        this.controlador = controlador;
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = InputUtils.leeEntero("Elige una opción: ");
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
        System.out.println("===== CENTRO DEPORTIVO =====");
        System.out.println("1. Gestión de socios");
        System.out.println("2. Gestión de actividades");
        System.out.println("3. Inscripciones");
        System.out.println("4. Cuotas");
        System.out.println("5. Informes");
        System.out.println("0. Salir");
    }

    // --------- SOCIOS ---------

    private void menuSocios() {
        int opcion;
        do {
            System.out.println("----- Gestión de socios -----");
            System.out.println("1. Alta socio");
            System.out.println("2. Listar socios");
            System.out.println("3. Buscar socio por ID");
            System.out.println("4. Buscar socio por DNI");
            System.out.println("5. Eliminar socio");
            System.out.println("0. Volver");
            opcion = InputUtils.leeEntero("Opción: ");

            switch (opcion) {
                case 1 -> altaSocio();
                case 2 -> listarSocios();
                case 3 -> buscarSocioPorId();
                case 4 -> buscarSocioPorDni();
                case 5 -> eliminarSocio();
                case 0 -> { }
                default -> System.out.println("Opción no válida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void altaSocio() {
        String nombre = InputUtils.leerCadenaNoVacia("Nombre: ");
        String dni = InputUtils.leerCadenaNoVacia("DNI: ");
        int edad = InputUtils.leeEntero("Edad: ");

        boolean ok = controlador.registrarSocio(nombre, dni, edad);
        if (ok) {
            System.out.println("✅ Socio registrado correctamente.");
        } else {
            System.out.println("❌ No se ha podido registrar (quizá DNI duplicado o sin espacio).");
        }
    }

    private void listarSocios() {
        Socio[] socios = controlador.listarSocios();
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
        int id = InputUtils.leeEntero("ID socio: ");
        Socio socio = controlador.buscarSocioPorId(id);
        if (socio == null) {
            System.out.println("No existe socio con ese ID.");
        } else {
            System.out.println("Socio encontrado: " + socio);
        }
    }

    private void buscarSocioPorDni() {
        String dni = InputUtils.leerCadenaNoVacia("DNI del socio: ");
        Socio socio = controlador.buscarSocioPorDni(dni);
        if (socio == null) {
            System.out.println("No existe socio con ese DNI.");
        } else {
            System.out.println("Socio encontrado: " + socio);
        }
    }

    private void eliminarSocio() {
        int id = InputUtils.leeEntero("ID del socio a eliminar: ");
        boolean ok = controlador.eliminarSocio(id);
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
            System.out.println("----- Gestión de actividades -----");
            System.out.println("1. Alta actividad");
            System.out.println("2. Listar actividades");
            System.out.println("3. Buscar actividad por ID");
            System.out.println("4. Buscar actividad por nombre");
            System.out.println("5. Eliminar actividad");
            System.out.println("0. Volver");
            opcion = InputUtils.leeEntero("Opción: ");

            switch (opcion) {
                case 1 -> altaActividad();
                case 2 -> listarActividades();
                case 3 -> buscarActividadPorId();
                case 4 -> buscarActividadPorNombre();
                case 5 -> eliminarActividad();
                case 0 -> { }
                default -> System.out.println("Opción no válida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void altaActividad() {
        String nombre = InputUtils.leerCadenaNoVacia("Nombre actividad: ");
        int duracion = InputUtils.leeEntero("Duración en minutos: ");
        String nivel = InputUtils.leerCadenaNoVacia("Nivel (iniciación, intermedio, avanzado): ");
        double precio = InputUtils.leeDouble("Precio mensual: ");
        int numMiembros = InputUtils.leeEntero("Número máximo de socios: ");

        boolean ok = controlador.registrarActividad(nombre, duracion, nivel, precio, numMiembros);
        if (ok) {
            System.out.println("✅ Actividad registrada correctamente.");
        } else {
            System.out.println("❌ No se ha podido registrar la actividad (quizá ya exista).");
        }
    }

    private void listarActividades() {
        Actividad[] actividades = controlador.listarActividades();
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
        int id = InputUtils.leeEntero("ID actividad: ");
        Actividad a = controlador.buscarActividadPorId(id);
        if (a == null) {
            System.out.println("No existe actividad con ese ID.");
        } else {
            System.out.println("Actividad encontrada: " + a);
        }
    }

    private void buscarActividadPorNombre() {
        String nombre = InputUtils.leerCadenaNoVacia("Nombre actividad: ");
        Actividad a = controlador.buscarActividadPorNombre(nombre);
        if (a == null) {
            System.out.println("No existe actividad con ese nombre.");
        } else {
            System.out.println("Actividad encontrada: " + a);
        }
    }

    private void eliminarActividad() {
        int id = InputUtils.leeEntero("ID de la actividad a eliminar: ");
        boolean ok = controlador.eliminarActividad(id);
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
            System.out.println("----- Inscripciones -----");
            System.out.println("1. Inscribir socio en actividad");
            System.out.println("2. Dar de baja socio de actividad");
            System.out.println("3. Ver actividades de un socio");
            System.out.println("4. Ver socios de una actividad");
            System.out.println("0. Volver");
            opcion = InputUtils.leeEntero("Opción: ");

            switch (opcion) {
                case 1 -> inscribirSocioEnActividad();
                case 2 -> bajaSocioDeActividad();
                case 3 -> verActividadesDeSocio();
                case 4 -> verSociosDeActividad();
                case 0 -> { }
                default -> System.out.println("Opción no válida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void inscribirSocioEnActividad() {
        int idSocio = InputUtils.leeEntero("ID socio: ");
        int idActividad = InputUtils.leeEntero("ID actividad: ");
        boolean ok = controlador.inscribirSocioEnActividad(idSocio, idActividad);
        if (ok) {
            System.out.println("✅ Inscripción realizada correctamente.");
        } else {
            System.out.println("❌ No se ha podido inscribir (datos incorrectos o ya inscrito).");
        }
    }

    private void bajaSocioDeActividad() {
        int idSocio = InputUtils.leeEntero("ID socio: ");
        int idActividad = InputUtils.leeEntero("ID actividad: ");
        boolean ok = controlador.darDeBajaSocioDeActividad(idSocio, idActividad);
        if (ok) {
            System.out.println("✅ Baja realizada correctamente.");
        } else {
            System.out.println("❌ No se ha podido dar de baja.");
        }
    }

    private void verActividadesDeSocio() {
        int idSocio = InputUtils.leeEntero("ID socio: ");
        Actividad[] actividades = controlador.obtenerActividadesDeSocio(idSocio);
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
        int idActividad = InputUtils.leeEntero("ID actividad: ");
        Socio[] socios = controlador.obtenerSociosDeActividad(idActividad);
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
            opcion = InputUtils.leeEntero("Opción: ");

            switch (opcion) {
                case 1 -> verCuotaMes();
                case 2 -> marcarMes(true);
                case 3 -> marcarMes(false);
                case 4 -> verTotalPagado();
                case 5 -> verTotalPendiente();
                case 6 -> verEstadoMes();
                case 7 -> listarMesesPendientes();
                case 8 -> listarMesesPagados();
                case 0 -> { }
                default -> System.out.println("Opción no válida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void verCuotaMes() {
        int idSocio = InputUtils.leeEntero("ID socio: ");
        int mes = InputUtils.leeEntero("Mes (1-12): ", 1, 12);
        double cuota = controlador.calcularCuotaMensualSocio(idSocio, mes);
        System.out.println("Cuota del socio " + idSocio + " en el mes " + mes + " = " + cuota + " €");
    }

    private void marcarMes(boolean pagado) {
        int idSocio = InputUtils.leeEntero("ID socio: ");
        int mes = InputUtils.leeEntero("Mes (1-12): ", 1, 12);
        boolean ok = pagado ? controlador.marcarCuotaPagada(idSocio, mes)
                : controlador.marcarCuotaPendiente(idSocio, mes);
        if (ok) {
            System.out.println("✅ Estado actualizado correctamente.");
        } else {
            System.out.println("❌ No se ha podido actualizar el estado.");
        }
    }

    private void verTotalPagado() {
        int idSocio = InputUtils.leeEntero("ID socio: ");
        double total = controlador.obtenerTotalPagadoAnual(idSocio);
        System.out.println("Total pagado en el año por el socio " + idSocio + " = " + total + " €");
    }

    private void verTotalPendiente() {
        int idSocio = InputUtils.leeEntero("ID socio: ");
        double total = controlador.obtenerTotalPendienteAnual(idSocio);
        System.out.println("Total pendiente en el año por el socio " + idSocio + " = " + total + " €");
    }

    private void verEstadoMes() {
        int idSocio = InputUtils.leeEntero("ID socio: ");
        int mes = InputUtils.leeEntero("Mes (1-12): ", 1, 12);
        String estado = controlador.obtenerEstadoPagoMes(idSocio, mes);
        System.out.println("Estado del mes " + mes + ": " + estado);
    }

    private void listarMesesPendientes() {
        int idSocio = InputUtils.leeEntero("ID socio: ");
        int[] meses = controlador.obtenerMesesPendientes(idSocio);
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
        int idSocio = InputUtils.leeEntero("ID socio: ");
        int[] meses = controlador.obtenerMesesPagados(idSocio);
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
        Socio s = controlador.socioConMasActividades();
        if (s != null) {
            System.out.println("Socio con más actividades: " + s);
        } else {
            System.out.println("No hay socios para informar.");
        }

        Actividad a = controlador.actividadMasPopular();
        if (a != null) {
            System.out.println("Actividad más popular: " + a);
        } else {
            System.out.println("No hay actividades para informar.");
        }
    }
}