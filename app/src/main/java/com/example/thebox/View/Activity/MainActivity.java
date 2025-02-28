package com.example.thebox.View.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.thebox.R;
import com.example.thebox.View.Fragment.TareaFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
private BottomNavigationView btnNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        init();
        initlisteners();
        if(savedInstanceState == null) {
         openFragment(TareaFragment.newInstance());
        }

    }
    private void init() {
        btnNav = findViewById(R.id.btnNav);
    }

    private void initlisteners() {
        btnNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();

               if (itemId == R.id.nav_home) {
                   openFragment(TareaFragment.newInstance());
                } /*else if (itemId == R.id.nav_stock) {
                   openFragment();

                } else if (itemId == R.id.nav_file) {
                    openFragment();
                }*/
                return true;
            }
        });
    }
    public void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameContainer, fragment)
                .addToBackStack(null)
                .commit();
    }
}