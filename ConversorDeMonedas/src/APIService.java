import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class APIService {
    // URL base para la API de tasas de cambio
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/a154243dd9f2fcdcf2730798/latest/";

    // Método para obtener las tasas de cambio
    public Map<String, Double> getExchangeRates(String baseCurrency) {
        try {
            // Crear un cliente HTTP
            HttpClient httpClient = HttpClient.newHttpClient();

            // Construir la solicitud HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL + baseCurrency))
                    .GET()
                    .build();

            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar si la respuesta tiene éxito
            if (response.statusCode() != 200) {
                System.out.println("Error al obtener las tasas de cambio: " + response.statusCode());
                return null;
            }

            // Parsear el cuerpo de la respuesta JSON
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

            // Verificar si las tasas están disponibles
            if (rates == null) {
                System.out.println("Error: Las tasas de cambio no están disponibles.");
                return null;
            }

            // Almacenar las tasas de cambio en un mapa
            Map<String, Double> exchangeRates = new HashMap<>();
            for (String key : rates.keySet()) {
                exchangeRates.put(key, rates.get(key).getAsDouble());
            }

            return exchangeRates;
        } catch (Exception e) {
            System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
            return null;
        }
    }
}
