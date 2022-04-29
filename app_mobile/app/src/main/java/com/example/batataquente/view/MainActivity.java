package com.example.batataquente.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.batataquente.R;
import com.example.batataquente.adapter.TLAdapter;
import com.example.batataquente.view.fragment.ClassificacaoFragment;
import com.example.batataquente.view.fragment.ContaFragment;
import com.example.batataquente.view.fragment.JogarFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public int UsuarioPontos = 0;
    public int UsuarioAcertos = 0;
    public int UsuarioErros = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        TLAdapter tlAdapter = new TLAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tlAdapter.addFragment(new ClassificacaoFragment(), "RANQUE");
        tlAdapter.addFragment(new JogarFragment(), "JOGAR");
        tlAdapter.addFragment(new ContaFragment(), "CONTA");
        viewPager.setAdapter(tlAdapter);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addOnTabSelectedListener(this);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if(tab.getPosition() == 2){
            LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(MainActivity.this);
            Intent i = new Intent("TAG_REFRESH_AUTENTICACAO");
            lbm.sendBroadcast(i);
        }

        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}