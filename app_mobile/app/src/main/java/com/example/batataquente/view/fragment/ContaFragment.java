package com.example.batataquente.view.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.batataquente.R;
import com.example.batataquente.view.MainActivity;

public class ContaFragment extends Fragment {

    private MainActivity main;
    private TextView txtPontos;
    private TextView txtAcertos;
    private TextView txtErros;
    private MyReceiver receiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = (MainActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conta, container, false);

        txtPontos = (TextView) view.findViewById(R.id.txtViewPontos);
        txtAcertos = (TextView) view.findViewById(R.id.txtViewAcertos);
        txtErros = (TextView) view.findViewById(R.id.txtViewErros);
        AtualizarCampos();

        receiver = new MyReceiver();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver,
                new IntentFilter("TAG_REFRESH_AUTENTICACAO"));

        return view;
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ContaFragment.this.AtualizarCampos();
        }
    }

    public void AtualizarCampos(){
        txtPontos.setText("" + main.UsuarioPontos + "");
        txtAcertos.setText("" + main.UsuarioAcertos + "");
        txtErros.setText("" + main.UsuarioErros + "");
    }
}