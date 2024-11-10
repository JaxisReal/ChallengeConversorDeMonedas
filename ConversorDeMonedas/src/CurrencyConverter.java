public interface CurrencyConverter {
    // Metodo para convertir moneda que toma la moneda de origen,
    // la moneda de destino y la cantidad a convertir
    double convert(String fromCurrency, String toCurrency, double amount);
}
