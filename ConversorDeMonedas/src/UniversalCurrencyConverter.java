import java.util.Map;

public class UniversalCurrencyConverter {
    // Referencia al servicio de API para obtener tasas de cambio
    private final APIService apiService;

    // Constructor que inicializa el servicio de API
    public UniversalCurrencyConverter(APIService apiService) {
        this.apiService = apiService;
    }

    // Método para convertir moneda
    public double convert(String fromCurrency, String toCurrency, double amount) {
        // Obtener las tasas de cambio para la moneda de origen
        Map<String, Double> rates = apiService.getExchangeRates(fromCurrency);
        if (rates == null) {
            System.out.println("Error al obtener las tasas de cambio para " + fromCurrency);
            return 0.0;
        }
        // Verificar si la moneda de destino está en las tasas obtenidas
        if (!rates.containsKey(toCurrency)) {
            System.out.println("Moneda de destino " + toCurrency + " no encontrada.");
            return 0.0;
        }
        // Realizar la conversión multiplicando la cantidad por la tasa de cambio
        return amount * rates.get(toCurrency);
    }
}
