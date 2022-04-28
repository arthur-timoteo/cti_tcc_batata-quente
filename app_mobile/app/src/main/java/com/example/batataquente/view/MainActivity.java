package com.example.batataquente.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.batataquente.R;
import com.example.batataquente.adapter.TLAdapter;
import com.example.batataquente.view.fragment.ClassificacaoFragment;
import com.example.batataquente.view.fragment.ContaFragment;
import com.example.batataquente.view.fragment.JogarFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

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
        tlAdapter.addFragment(new ClassificacaoFragment(), "CLASSIFICAÇÃO");
        tlAdapter.addFragment(new JogarFragment(), "JOGAR");
        tlAdapter.addFragment(new ContaFragment(), "CONTA");
        viewPager.setAdapter(tlAdapter);
    }
}