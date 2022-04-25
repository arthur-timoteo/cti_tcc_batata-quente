package com.example.batataquente.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.batataquente.R;
import com.example.batataquente.model.Palavra;
import com.example.batataquente.service.PalavraService;

import org.json.JSONException;

import java.io.IOException;

public class JogarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        try {
            Palavra palavra = PalavraService.getPalavraAleatoria();
            System.out.println(palavra.Palavra);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jogar, container, false);
    }
}