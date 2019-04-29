package com.example.allknowledge.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.allknowledge.App;
import com.example.allknowledge.LWT.SortListActivity;
import com.example.allknowledge.MapsActivity;
import com.example.allknowledge.R;
import com.example.allknowledge.media.Mediaplayer;
import com.example.testzadanie.model.Recipe;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements AdapterRecipe.OnitemClick, SwipeRefreshLayout.OnRefreshListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyc)
    RecyclerView recyc;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    AdapterRecipe adapter;
    List<ArrayList<Recipe>> lists = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusearch,menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        App.apiHelper.getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        o->{
                            App.dm.setRecipesModels(o.getRecipes());
                            setDataFrags();

                            recyc.setLayoutManager(new LinearLayoutManager(this));
                            adapter = new AdapterRecipe(lists);
                            adapter.setOnitemClick(this);
                            recyc.setAdapter(adapter);
                        },
                        throwable -> {
                            Toast.makeText(getBaseContext(),"No inthernet conn",Toast.LENGTH_SHORT).show();
                        }
                );

    }

    private void setDataFrags() {

        List<Recipe> istr = App.dm.getRecipes();
        Integer z = 0;
        for (int i =0 ; i<istr.size(); i++)
        {
            z++;
            if (z==1 || z==2)
            {
                ArrayList<Recipe> _m = new ArrayList<>();
                _m.add(istr.get(i));

                lists.add(_m);
            }else {
                z=0;
                lists.get(lists.size()-1).add(istr.get(i));
            }
        }





    }

    @Override
    public void start(Recipe recipe) {
        Intent intent = new Intent(MainActivity.this,ActivityFood.class);
        String gson = new Gson().toJson(recipe);
        intent.putExtra("recipe",gson);
        startActivity(intent);

        Toast.makeText(getBaseContext(),""+recipe.getName(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(false);
            }
        },4000);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(MainActivity.this, Mediaplayer.class);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, SortListActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_tools) {
            Toast.makeText(getBaseContext(),"4",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {
            Toast.makeText(getBaseContext(),"5",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(getBaseContext(),"6",Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
