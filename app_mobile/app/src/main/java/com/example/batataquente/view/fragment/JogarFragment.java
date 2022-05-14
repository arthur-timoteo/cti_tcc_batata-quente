package com.example.batataquente.view.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
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
import com.example.batataquente.util.NetworkUtil;
import com.example.batataquente.view.MainActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JogarFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView txtViewPalavra;
    private MainActivity main;
    private int opcaoCorreta = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = (MainActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jogar, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        txtViewPalavra = view.findViewById(R.id.txtViewPalavra);

        taskPalavra();

        return view;
    }

    private void taskPalavra(){
        if(NetworkUtil.isNetworkConnected(getContext())){
            try {
                Palavra palavra = PalavraService.getPalavraAleatoria();

                recyclerView.setAdapter(new PalavraAdapter(getContext(), embaralharOpcoes(palavra), onClickPalavra()));
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                txtViewPalavra.setText(palavra.Palavra);
            } catch (IOException e) {
                e.printStackTrace();
                txtViewPalavra.setText("Erro de conexão com servidor");
            } catch (JSONException e) {
                e.printStackTrace();
                txtViewPalavra.setText("Erro na busca");
            }
        } else {
            txtViewPalavra.setText("Erro de conexão");
            Toast.makeText(getContext(), "Erro de conexão de  internet", Toast.LENGTH_SHORT).show();
        }
    }

    protected PalavraAdapter.PalavraOnClickListener onClickPalavra() {
        return new PalavraAdapter.PalavraOnClickListener() {
            @Override
            public void onClickPalavra(PalavraAdapter.PalavraViewHolder holder, int idx) {
                if(idx == opcaoCorreta){
                    main.UsuarioPontos += 1;
                    main.UsuarioAcertos += 1;
                    Toast.makeText(getContext(), "Você Acertou", Toast.LENGTH_SHORT).show();
                } else {
                    main.UsuarioPontos = main.UsuarioPontos != 0 ? main.UsuarioPontos - 1 : 0;
                    main.UsuarioErros += 1;
                    Toast.makeText(getContext(), "Você Errou", Toast.LENGTH_SHORT).show();
                }

                taskPalavra();
            }
        };
    }

    private List<String> embaralharOpcoes(Palavra palavra){
        List<String> opcoes = new ArrayList<String>();
        opcoes.add(""); opcoes.add(""); opcoes.add("");

        Random g = new Random();
        int r1 = g.nextInt(3);
        int r2 = g.nextInt(3);
        int r3 = g.nextInt(3);
        opcaoCorreta = r2;

        while (r2 == r1 || r2 == r3) {
            r2= g.nextInt(3);
            opcaoCorreta = r2;
        }
        while (r3 == r1 || r3 == r2) {
            r3= g.nextInt(3);
        }

        opcoes.set(r1, palavra.PalavraOpcaoErrada1);
        opcoes.set(r2, palavra.PalavraOpcaoCorreta);
        opcoes.set(r3, palavra.PalavraOpcaoErrada2);

        return opcoes;
    }
}