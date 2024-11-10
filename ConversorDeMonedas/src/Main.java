import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear un objeto Scanner para la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Crear un objeto UniversalCurrencyConverter usando un servicio de API
        UniversalCurrencyConverter converter = new UniversalCurrencyConverter(new APIService());

        // Variable para controlar el bucle principal
        boolean running = true;

        // Bucle principal del programa
        while (running) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Convertir moneda");
            System.out.println("2. Salir");
            System.out.print("Selecciona una opción: ");

            // Leer la opción seleccionada por el usuario
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Pedir la moneda de origen
                    System.out.println("\nSelecciona la moneda de origen:");
                    displayCurrencyOptions();
                    int fromCurrencyIndex = scanner.nextInt();
                    String fromCurrency = getCurrencyCode(fromCurrencyIndex);

                    // Pedir la moneda de destino
                    System.out.println("\nSelecciona la moneda de destino:");
                    displayCurrencyOptions();
                    int toCurrencyIndex = scanner.nextInt();
                    String toCurrency = getCurrencyCode(toCurrencyIndex);

                    // Comprobar si las monedas son válidas
                    if (fromCurrency == null || toCurrency == null) {
                        System.out.println("Moneda no válida, intenta de nuevo.");
                        break;
                    }

                    // Pedir la cantidad a convertir
                    System.out.print("\nIngresa la cantidad que deseas convertir: ");
                    double amount = scanner.nextDouble();

                    // Realizar la conversión
                    double result = converter.convert(fromCurrency, toCurrency, amount);
                    if (result == 0.0) {
                        System.out.println("Hubo un problema al realizar la conversión.");
                    } else {
                        System.out.println("Resultado de la conversión: " + result);
                    }
                    break;
                case 2:
                    // Salir del programa
                    running = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    // Manejar opción no válida
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
        // Cerrar el objeto Scanner
        scanner.close();
    }

    // Mostrar las opciones de monedas disponibles
    private static void displayCurrencyOptions() {
        System.out.println("1 - ARS - Peso argentino");
        System.out.println("2 - BOB - Boliviano boliviano");
        System.out.println("3 - BRL - Real brasileño");
        System.out.println("4 - CLP - Peso chileno");
        System.out.println("5 - COP - Peso colombiano");
        System.out.println("6 - USD - Dólar estadounidense");
        System.out.print("Selecciona el número de la moneda: ");
    }

    // Obtener el código de moneda según la opción seleccionada
    private static String getCurrencyCode(int index) {
        switch (index) {
            case 1: return "ARS";
            case 2: return "BOB";
            case 3: return "BRL";
            case 4: return "CLP";
            case 5: return "COP";
            case 6: return "USD";
            default:
                System.out.println("Opción no válida.");
                return null; // Si no es una opción válida, devuelve null
        }
    }
}