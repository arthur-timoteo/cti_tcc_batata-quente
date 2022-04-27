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

public class PalavraAdapter extends RecyclerView.Adapter<PalavraAdapter.PalavraViewHolder> {

    private final Palavra palavra;
    private final Context context;
    private final PalavraOnClickListener onClickListener;
    private final String[] opcoes = new String[3];

    public interface PalavraOnClickListener {
        public void onClickPalavra(PalavraViewHolder holder, int idx);
    }

    public PalavraAdapter(Context context, Palavra palavra, PalavraOnClickListener onClickListener){
        this.context = context;
        this.palavra = palavra;
        this.onClickListener = onClickListener;
        this.opcoes[0] = palavra.PalavraOpcaoCorreta;
        this.opcoes[1] = palavra.PalavraOpcaoErrada1;
        this.opcoes[2] = palavra.PalavraOpcaoErrada2;
    }

    @NonNull
    @Override
    public PalavraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.palavra_card, parent, false);
        return new PalavraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PalavraViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtViewOpcao.setText(opcoes[position]);

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
        return opcoes.length;
    }

    public class PalavraViewHolder extends RecyclerView.ViewHolder {

        TextView txtViewOpcao;

        public PalavraViewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewOpcao = (TextView) itemView.findViewById(R.id.txtViewOpcao);
        }
    }
}
