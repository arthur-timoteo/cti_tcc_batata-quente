package com.example.batataquente.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.batataquente.R;
import com.example.batataquente.model.Palavra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PalavraAdapter extends RecyclerView.Adapter<PalavraAdapter.PalavraViewHolder> {

    private final Palavra palavra;
    private final Context context;
    private final PalavraOnClickListener onClickListener;
    private final List<String> opcoes = new ArrayList<String>();

    public interface PalavraOnClickListener {
        public void onClickPalavra(PalavraViewHolder holder, int idx);
    }

    public PalavraAdapter(Context context, Palavra palavra, PalavraOnClickListener onClickListener){
        this.context = context;
        this.palavra = palavra;
        this.onClickListener = onClickListener;
        this.opcoes.add(""); this.opcoes.add(""); this.opcoes.add("");

        Random g = new Random();
        int r1 = g.nextInt(3);
        int r2 = g.nextInt(3);
        int r3 = g.nextInt(3);

        while (r2 == r1 || r2 == r3) {
            r2= g.nextInt(3);
        }
        while (r3 == r1 || r3 == r2) {
            r3= g.nextInt(3);
        }

        this.opcoes.set(r1, palavra.PalavraOpcaoErrada1);
        this.opcoes.set(r2, palavra.PalavraOpcaoCorreta);
        this.opcoes.set(r3, palavra.PalavraOpcaoErrada2);
    }

    @NonNull
    @Override
    public PalavraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.palavra_card, parent, false);
        return new PalavraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PalavraViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtViewOpcao.setText(opcoes.get(position));

        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickPalavra(holder, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return opcoes.size();
    }

    public class PalavraViewHolder extends RecyclerView.ViewHolder {

        TextView txtViewOpcao;

        public PalavraViewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewOpcao = (TextView) itemView.findViewById(R.id.txtViewOpcao);
        }
    }
}
