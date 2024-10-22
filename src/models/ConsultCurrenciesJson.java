package models;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultCurrenciesJson {

    public CurrenciesJson generateCurrenciesJson(String baseCode){
        String address = "https://v6.exchangerate-api.com/v6/a9d1b784aa1ced48f0508a8f/latest/" + baseCode;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(address)).build();
        try{
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), CurrenciesJson.class);
        } catch (IOException | InterruptedException | IllegalStateException e){
            System.out.println("Erro!");
            System.out.println(e.getMessage());
            return null;
        }
    }
}
