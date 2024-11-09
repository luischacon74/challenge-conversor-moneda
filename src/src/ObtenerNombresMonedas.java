import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ObtenerNombresMonedas {

    private static final String API_URL = "https://openexchangerates.org/api/currencies.json";
    private static final String APP_ID = "YOUR_APP_ID"; // Reemplaza con tu App ID de Open Exchange Rates

    private Map<String, String> nombresMonedas;

    public ObtenerNombresMonedas() {
        this.nombresMonedas = obtenerNombresMonedas();
    }

    public Map<String, String> obtenerNombresMonedas() {
        URI direccion = URI.create(API_URL + "?app_id=" + APP_ID);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);
            Map<String, String> nombresMonedas = new HashMap<>();
            for (String key : jsonResponse.keySet()) {
                nombresMonedas.put(key, jsonResponse.get(key).getAsString());
            }
            return nombresMonedas;
        } catch (Exception e) {
            throw new RuntimeException("No se pudieron obtener los nombres de las monedas.", e);
        }
    }

    public boolean esCodigoMonedaValido(String codigo) {
        return nombresMonedas.containsKey(codigo);
    }

    public Set<String> obtenerCodigosMoneda() {
        return nombresMonedas.keySet();
    }
}