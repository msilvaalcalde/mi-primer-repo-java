package com.mycompany.sistemapersonalmunicipal;

import java.util.Scanner;

public class SistemaPersonalMunicipal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ControlTrabajador control = new ControlTrabajador();

        int opcion = 0;

        while (opcion != 7) {
            try {
                System.out.println("===== SISTEMA DE GESTION DE PERSONAL MUNICIPAL =====");
                System.out.println("1. Registrar empleado municipal");
                System.out.println("2. Registrar obrero municipal");
                System.out.println("3. Listar trabajadores");
                System.out.println("4. Buscar trabajador por documento");
                System.out.println("5. Buscar trabajador por nombre");
                System.out.println("6. Generar reportes");
                System.out.println("7. Salir");
                System.out.print("Seleccione una opcion: ");

                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        registrarEmpleado(sc, control);
                        break;

                    case 2:
                        registrarObrero(sc, control);
                        break;

                    case 3:
                        control.listarTrabajadores();
                        break;

                    case 4:
                        buscarPorDocumento(sc, control);
                        break;

                    case 5:
                        buscarPorNombre(sc, control);
                        break;

                    case 6:
                        menuReportes(sc, control);
                        break;

                    case 7:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opcion invalida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero.");
            }

            System.out.println();
        }

        sc.close();
    }

    public static void registrarEmpleado(Scanner sc, ControlTrabajador control) {

        EmpleadoMunicipal empleado = new EmpleadoMunicipal();

        completarDatosGenerales(sc, empleado);

        while (true) {
            try {
                System.out.print("Sueldo mensual: ");
                empleado.setSueldoMensual(Double.parseDouble(sc.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un valor numerico.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Bonificacion: ");
                empleado.setBonificacion(Double.parseDouble(sc.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un valor numerico.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        control.agregarTrabajador(empleado);

        System.out.println("Empleado municipal registrado correctamente.");
    }

    public static void registrarObrero(Scanner sc, ControlTrabajador control) {

        ObreroMunicipal obrero = new ObreroMunicipal();

        completarDatosGenerales(sc, obrero);

        while (true) {
            try {
                System.out.print("Jornal diario: ");
                obrero.setJornalDiario(Double.parseDouble(sc.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un valor numerico.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Dias trabajados: ");
                obrero.setDiasTrabajados(Integer.parseInt(sc.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero entero.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        control.agregarTrabajador(obrero);

        System.out.println("Obrero municipal registrado correctamente.");
    }

    public static void completarDatosGenerales(Scanner sc, Trabajador trabajador) {

        while (true) {
            try {
                System.out.print("Tipo documento (DNI/CE): ");
                trabajador.setTipoDocumento(sc.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Numero documento: ");
                trabajador.setNumeroDocumento(sc.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Nombres: ");
                trabajador.setNombres(sc.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Apellido paterno: ");
                trabajador.setApellidoPaterno(sc.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Apellido materno: ");
                trabajador.setApellidoMaterno(sc.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Celular: ");
                trabajador.setCelular(sc.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Correo: ");
                trabajador.setCorreo(sc.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Codigo trabajador: ");
                trabajador.setCodigoTrabajador(sc.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Fecha ingreso: ");
                trabajador.setFechaIngreso(sc.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Regimen laboral (CAS/276/728): ");
                trabajador.setRegimenLaboral(sc.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        Area area = new Area("A001", "Recursos Humanos");
        Cargo cargo = new Cargo("C001", "Asistente administrativo");
        Contrato contrato = new Contrato("CT001", trabajador.getFechaIngreso(), "2026-12-31");

        trabajador.setArea(area);
        trabajador.setCargo(cargo);
        trabajador.setContrato(contrato);
    }

    public static void buscarPorDocumento(Scanner sc, ControlTrabajador control) {

        System.out.print("Ingrese documento a buscar: ");
        String documento = sc.nextLine();

        Trabajador encontrado = control.buscarPorDocumento(documento);

        if (encontrado == null) {
            System.out.println("No se encontro trabajador.");
        } else {
            System.out.println(encontrado.mostrarDatos());
        }
    }

    public static void buscarPorNombre(Scanner sc, ControlTrabajador control) {

        System.out.print("Ingrese nombre a buscar: ");
        String nombre = sc.nextLine();

        Trabajador encontrado = control.buscarPorNombre(nombre);

        if (encontrado == null) {
            System.out.println("No se encontro trabajador.");
        } else {
            System.out.println(encontrado.mostrarDatos());
        }
    }

    public static void menuReportes(Scanner sc, ControlTrabajador control) {

        int opcionReporte = 0;

        while (opcionReporte != 3) {
            try {
                System.out.println("===== MENU DE REPORTES =====");
                System.out.println("1. Reporte general de trabajadores");
                System.out.println("2. Reporte por tipo de trabajador");
                System.out.println("3. Volver al menu principal");
                System.out.print("Seleccione una opcion: ");

                opcionReporte = Integer.parseInt(sc.nextLine());

                switch (opcionReporte) {
                    case 1:
                        control.generarReporteGeneral();
                        break;

                    case 2:
                        control.generarReportePorTipo();
                        break;

                    case 3:
                        System.out.println("Volviendo al menu principal...");
                        break;

                    default:
                        System.out.println("Opcion invalida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero.");
            }

            System.out.println();
        }
    }
}