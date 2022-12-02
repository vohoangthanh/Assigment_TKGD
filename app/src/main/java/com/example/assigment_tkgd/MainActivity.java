package com.example.assigment_tkgd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import android.view.MenuItem;
import android.widget.LinearLayout;



import com.example.assigment_tkgd.fragments.Thongkefragment;

import com.example.assigment_tkgd.fragments.ThuChiFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        linearLayout = findViewById(R.id.linearLayout);
        navigationView = findViewById(R.id.naviGationView);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                Bundle bundle = new Bundle();
                switch (item.getItemId()){
                    case R.id.mThu:
                       fragment = new ThuChiFragment();
                        bundle.putInt("trangthai",0);
                        fragment.setArguments(bundle);
                        break;
                    case R.id.mChi:
                        fragment = new ThuChiFragment();
                        bundle.putInt("trangthai",0);
                        fragment.setArguments(bundle);
                        break;
                    case R.id.mthongKe:
                        fragment = new Thongkefragment();
                    case R.id.mgioiThieu:
                        break;
                    case R.id.mthoat:
                        break;
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.linearLayout, fragment)
                        .commit();

                drawerLayout.closeDrawer(GravityCompat.START);
                setTitle(item.getTitle());
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}