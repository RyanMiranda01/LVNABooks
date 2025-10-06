package LVNABooks.Controller.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscaAPI {

    public String buscaAPI(String livro) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.googleapis.com/books/v1/volumes?q=" + livro.replace(" ", "+")))
                .build();


        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        if(response.body().length() == 0){
            return "Livro não encontrado!";
        }else{

            return response.body();
        }

    }

}
