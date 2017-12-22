package com.support.android.iplfit.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.support.android.iplfit.Dicasfragment;
import com.support.android.iplfit.R;

import java.util.ArrayList;
import java.util.List;

public class DicasActivity extends AppCompatActivity {

    private ViewPager ViewPagerDicas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dicas);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ViewPagerDicas = findViewById(R.id.viewPagerDicas);
        if (ViewPagerDicas != null) {
            setupViewPager(ViewPagerDicas);
        }

        ViewPagerDicas.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {
                ((Dicasfragment)((Adapter)ViewPagerDicas.getAdapter()).getItem(position)).alterarAdapater(position);
            }
        });

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(ViewPagerDicas);

        setTitle("Dicas");
    }

    private void setupViewPager(ViewPager myViewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());

            adapter.addFragment(new Dicasfragment(), "Alimentação");
            adapter.addFragment(new Dicasfragment(), "Desporto");
            adapter.addFragment(new Dicasfragment(), "Saúde");

        myViewPager.setAdapter(adapter);

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
