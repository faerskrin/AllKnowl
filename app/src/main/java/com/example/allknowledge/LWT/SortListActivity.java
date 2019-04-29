package com.example.allknowledge.LWT;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allknowledge.App;
import com.example.allknowledge.R;
import com.example.allknowledge.model.ListWithText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SortListActivity extends AppCompatActivity implements LWTAdapter.SetOnclicItem {

    private SearchView searchView;
    private LWTAdapter adapter;
    private List<ListWithText> lwt ;
    @BindView(R.id.listrv)
    RecyclerView recyclerView;
    @BindView(R.id.searchToolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlist);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        lwt = App.dm.getLwt();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LWTAdapter(lwt);
        adapter.setOnclicItem(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuactivity,menu);
        MenuItem sr = menu.findItem(R.id.search);
        searchView = (SearchView) sr.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                s = s.toLowerCase();
                List<ListWithText> llz = new ArrayList<>();

                for (ListWithText listWithText : App.dm.getLwt())
                {
                    String simbol = listWithText.getName();
                    if (simbol.contains(s))
                    {
                        llz.add(listWithText);
                    }
                }
                adapter.SetSearch(llz);
                return false;
            }
        });

        return true;
    }

    @Override
    public void onClicked(ListWithText lwt, int pos) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frgmCount,new FragmentLWT(lwt)).commit();

    }
}
