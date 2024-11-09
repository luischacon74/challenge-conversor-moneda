import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Menu {

    private static Idioma idiomaSeleccionado = Idioma.INGLES;
    private static ObtenerNombresMonedas currencyService = new ObtenerNombresMonedas();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione el idioma / Select language:");
            System.out.println("1. Español");
            System.out.println("2. English");
            System.out.print("Seleccione una opción: ");
            try {
                int idiomaOption = scanner.nextInt();
                switch (idiomaOption) {
                    case 1:
                        idiomaSeleccionado = Idioma.ESPANOL;
                        break;
                    case 2:
                        idiomaSeleccionado = Idioma.INGLES;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar la entrada no válida
                continue;
            }

            while (!exit) {
                if (idiomaSeleccionado == Idioma.ESPANOL) {
                    System.out.println("Menú:");
                    System.out.println("1. Mostrar Códigos de Moneda Disponibles");
                    System.out.println("2. Convertir Moneda");
                    System.out.println("3. Salir");
                    System.out.print("Seleccione una opción: ");
                } else {
                    System.out.println("Menu:");
                    System.out.println("1. Show Available Currency Codes");
                    System.out.println("2. Convert Currency");
                    System.out.println("3. Exit");
                    System.out.print("Select an option: ");
                }

                try {
                    String option = scanner.next().toLowerCase();
                    switch (option) {
                        case "1":
                            mostrarCodigosMoneda();
                            break;
                        case "2":
                            convertirMoneda(scanner);
                            break;
                        case "3":
                        case "exit":
                            exit = true;
                            if (idiomaSeleccionado == Idioma.ESPANOL) {
                                System.out.println("Saliendo del menú...");
                            } else {
                                System.out.println("Exiting the menu...");
                            }
                            break;
                        default:
                            if (idiomaSeleccionado == Idioma.ESPANOL) {
                                System.out.println("Opción no válida. Intente de nuevo.");
                            } else {
                                System.out.println("Invalid option. Please try again.");
                            }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada no válida. Por favor, ingrese un número.");
                    scanner.next(); // Limpiar la entrada no válida
                }
            }
        }

        scanner.close();
    }

    public static void mostrarCodigosMoneda() {
        Map<String, String> nombresMonedas = currencyService.obtenerNombresMonedas();
        if (idiomaSeleccionado == Idioma.ESPANOL) {
            System.out.println("Códigos de Moneda Disponibles:");
        } else {
            System.out.println("Available Currency Codes:");
        }
        for (Map.Entry<String, String> entry : nombresMonedas.entrySet()) {
            System.out.printf("%s - %s%n", entry.getKey(), entry.getValue());
        }
    }

    public static void convertirMoneda(Scanner scanner) {
        ConsumirTasDeCambio consumirTasDeCambio = new ConsumirTasDeCambio();

        String monedaBase;
        do {
            if (idiomaSeleccionado == Idioma.ESPANOL) {
                System.out.print("Ingrese la moneda base (por ejemplo, USD): ");
            } else {
                System.out.print("Enter the base currency (e.g., USD): ");
            }
            monedaBase = scanner.next().toUpperCase();
            if (!currencyService.esCodigoMonedaValido(monedaBase)) {
                if (idiomaSeleccionado == Idioma.ESPANOL) {
                    System.out.println("Código de moneda no válido. Intente de nuevo.");
                } else {
                    System.out.println("Invalid currency code. Please try again.");
                }
            }
        } while (!currencyService.esCodigoMonedaValido(monedaBase));

        String monedaDestino;
        do {
            if (idiomaSeleccionado == Idioma.ESPANOL) {
                System.out.print("Ingrese la moneda de destino (por ejemplo, EUR): ");
            } else {
                System.out.print("Enter the target currency (e.g., EUR): ");
            }
            monedaDestino = scanner.next().toUpperCase();
            if (!currencyService.esCodigoMonedaValido(monedaDestino)) {
                if (idiomaSeleccionado == Idioma.ESPANOL) {
                    System.out.println("Código de moneda no válido. Intente de nuevo.");
                } else {
                    System.out.println("Invalid currency code. Please try again.");
                }
            }
        } while (!currencyService.esCodigoMonedaValido(monedaDestino));

        String cantidad;
        while (true) {
            if (idiomaSeleccionado == Idioma.ESPANOL) {
                System.out.print("Ingrese la cantidad a convertir: ");
            } else {
                System.out.print("Enter the amount to convert: ");
            }
            cantidad = scanner.next();
            try {
                Double.parseDouble(cantidad);
                break;
            } catch (NumberFormatException e) {
                if (idiomaSeleccionado == Idioma.ESPANOL) {
                    System.out.println("Cantidad no válida. Por favor, ingrese un número.");
                } else {
                    System.out.println("Invalid amount. Please enter a number.");
                }
            }
        }

        try {
            Moneda resultado = consumirTasDeCambio.obtenerDatoCasaDeCambio(monedaBase, monedaDestino, cantidad);

            if ("success".equals(resultado.result())) {
                if (idiomaSeleccionado == Idioma.ESPANOL) {
                    System.out.printf("%s %s equivalen a %.2f %s%n", cantidad, monedaBase, resultado.conversion_result(), monedaDestino);
                } else {
                    System.out.printf("%s %s equals %.2f %s%n", cantidad, monedaBase, resultado.conversion_result(), monedaDestino);
                }
            } else {
                if (idiomaSeleccionado == Idioma.ESPANOL) {
                    System.out.println("Error al obtener la tasa de cambio.");
                } else {
                    System.out.println("Error obtaining the exchange rate.");
                }
            }
        } catch (Exception e) {
            if (idiomaSeleccionado == Idioma.ESPANOL) {
                System.out.println("Error al realizar la conversión. Por favor, intente de nuevo.");
            } else {
                System.out.println("Error performing the conversion. Please try again.");
            }
        }
    }
}