package com.example.batataquente.service;

import com.example.batataquente.model.Palavra;
import com.example.batataquente.util.HttpHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

public class PalavraService {

    public static Palavra getPalavraAleatoria() throws IOException, JSONException {
        Random generator = new Random();
        int numeroUsuario = 0;

        while(numeroUsuario == 0){
            numeroUsuario = generator.nextInt(3);
        }

        HttpHelper http = new HttpHelper();
        String json = http.doGet("palavra.php?id=" + String.valueOf(numeroUsuario));
        Palavra palavra = parseJSON(json);

        return palavra;
    }

    public static Palavra parseJSON(String responseBody) throws JSONException {
        Palavra palavra = new Palavra();
        JSONArray rootArray = new JSONArray(responseBody);
        JSONObject root = null;

        for(int i = 0; i < rootArray.length(); i++){
            root = rootArray.getJSONObject(i);
        }

        palavra.PalavraId = root.getInt("PalavraId");
        palavra.Palavra = root.getString("Palavra");
        palavra.PalavraPontos = root.getInt("PalavraPontos");
        palavra.PalavraAcertos = root.getInt("PalavraAcertos");
        palavra.PalavraOpcaoCorreta = root.getString("PalavraOpcaoCorreta");
        palavra.PalavraOpcaoErrada1 = root.getString("PalavraOpcaoErrada1");
        palavra.PalavraOpcaoErrada2 = root.getString("PalavraOpcaoErrada2");

        return palavra;
    }
}
