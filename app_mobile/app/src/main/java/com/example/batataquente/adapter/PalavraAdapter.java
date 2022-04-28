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

    private final Context context;
    private final PalavraOnClickListener onClickListener;
    private List<String> opcoes;

    public interface PalavraOnClickListener {
        public void onClickPalavra(PalavraViewHolder holder, int idx);
    }

    public PalavraAdapter(Context context, List<String> opcoes, PalavraOnClickListener onClickListener){
        this.context = context;
        this.onClickListener = onClickListener;
        this.opcoes = opcoes;
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
