package com.example.batataquente.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.batataquente.R;
import com.example.batataquente.adapter.PalavraAdapter;
import com.example.batataquente.model.Palavra;
import com.example.batataquente.service.PalavraService;

import org.json.JSONException;

import java.io.IOException;

public class JogarFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView txtViewPalavra;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jogar, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        txtViewPalavra = view.findViewById(R.id.txtViewPalavra);

        try {
            Palavra palavra = PalavraService.getPalavraAleatoria();
            recyclerView.setAdapter(new PalavraAdapter(getContext(), palavra, onClickPalavra()));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            txtViewPalavra.setText(palavra.Palavra);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    protected PalavraAdapter.PalavraOnClickListener onClickPalavra() {
        return new PalavraAdapter.PalavraOnClickListener() {
            @Override
            public void onClickPalavra(PalavraAdapter.PalavraViewHolder holder, int idx) {
                Toast.makeText(getContext(), "Você Acertou, Pontos: ", Toast.LENGTH_SHORT).show();
            }
        };
    }
}