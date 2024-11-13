import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.util.Set;

public class ConsumirTasDeCambio {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/1729a3c10506c154d5eec6cf/latest/USD";

    public Moneda obtenerDatoCasaDeCambio(String idMonedaBase, String idMonedaDestino, String cantidadMoneda) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/1729a3c10506c154d5eec6cf/pair/"
                + idMonedaBase + "/" + idMonedaDestino + "/" + cantidadMoneda);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (Exception e) {
            throw new RuntimeException("No se encontró el valor adecuado.", e);
        }
    }
}
