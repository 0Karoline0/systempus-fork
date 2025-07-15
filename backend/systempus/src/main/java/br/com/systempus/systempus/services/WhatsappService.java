package br.com.systempus.systempus.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.Disciplina;
import br.com.systempus.systempus.util.Util;

@Service
public class WhatsappService {

    public Integer sendWhatsappMessage(String professorPhoneNumber, String nomeProfessor, Disciplina disciplina, String mensagemAdicional) throws IOException, InterruptedException {
        String mensagemCustomizada = Util.gerarMensagemWhatsApp(nomeProfessor, disciplina, mensagemAdicional);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(
            URI.create("http://localhost:3000/send"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(Util.messageAndNumberToWhatsapp(professorPhoneNumber, mensagemCustomizada)))
            .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.statusCode();
    }    

}