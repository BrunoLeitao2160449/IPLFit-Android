package com.support.android.iplfit.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.support.android.iplfit.Class.CustomViewPager;
import com.support.android.iplfit.Diafragment;
import com.support.android.iplfit.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private CustomViewPager myViewPager;
    final Context context = this;

    private Adapter adapter = new Adapter(getSupportFragmentManager());

    Date date;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        myViewPager = findViewById(R.id.viewpager);
        if (myViewPager != null) {
            setupViewPager(myViewPager);
        }

        myViewPager.setOnSwipeOutListener(new CustomViewPager.OnSwipeOutListener() {
            @Override
            public void onSwipeOutAtStart() {

                //Toast.makeText(MainActivity.this, "inicio", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeOutAtEnd() {
                setupViewPager(myViewPager);
                //Toast.makeText(MainActivity.this, "fim", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.tv_drawerUsername);
        navUsername.setText(nome);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(myViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager myViewPager) {

        SimpleDateFormat sdf = new SimpleDateFormat("EEE - d MMM");

        if(adapter.getCount() == 0){
            date = calendar.getTime();
            String day = sdf.format(date);
            adapter.addFragment(new Diafragment(), day);
        }else
        {
            calendar.add(Calendar.DATE, 1);
            date = calendar.getTime();
            String day = sdf.format(date);
            adapter.addFragment(new Diafragment(), day);
        }

        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(adapter.getCount());

    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.nav_plan) {
                    Toast.makeText(MainActivity.this, "Plano", Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.nav_myfood) {
                    Intent intent = new Intent(getApplicationContext(), MeusAlimentosActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.nav_profile) {
                    Intent intent = new Intent(getApplicationContext(), PerfilActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.nav_home) {
                    Intent intent = new Intent(getApplicationContext(), DicasActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.nav_settings) {
                    Intent intent = new Intent(getApplicationContext(), DefinicoesActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.nav_about) {
                    Intent intent = new Intent(getApplicationContext(), SobreActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.nav_quit) {

                    LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
                    View mView = layoutInflaterAndroid.inflate(R.layout.sair_dialogbox, null);
                    AlertDialog.Builder DialogBoxSair = new AlertDialog.Builder(context);
                    DialogBoxSair.setView(mView);

                    DialogBoxSair
                            .setCancelable(false)
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogBox, int id) {
                                    finish();
                                }
                            })

                            .setNegativeButton("Não",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogBox, int id) {
                                            dialogBox.cancel();
                                            navigationView.getMenu().getItem(6).setChecked(false);
                                        }
                                    });

                    AlertDialog alertDialogBox = DialogBoxSair.create();
                    alertDialogBox.show();
                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return true;
            }
        });
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
