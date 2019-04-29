package com.example.allknowledge.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.allknowledge.R;
import com.example.testzadanie.model.Recipe;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityFood extends Activity {

    @BindView(R.id.img_activity)
    ImageView imageView;
    @BindView(R.id.title_next)
    TextView title;
    @BindView(R.id.diff_next)
    TextView diff;
    @BindView(R.id.instructions_next)
    WebView instr;
    @BindView(R.id.sometext_next)
    TextView sometext;


    @OnClick(R.id.Image_butt)
    void back()
    {
        Intent intent = new Intent(ActivityFood.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_activity);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String recipe = intent.getStringExtra("recipe");
        Recipe sr =new Gson().fromJson(recipe,Recipe.class);

        Glide.with(imageView)
                .load(sr.getImages().get(0))
                .apply(RequestOptions
                .errorOf(R.drawable.ic_search_black_24dp)
                .placeholder(R.drawable.ic_launcher_foreground))
                .into(imageView);

        title.setText(sr.getName());
        diff.setText(""+sr.getDifficulty());
        instr.loadData(sr.getInstructions(),"text/html; charset=UTF-8",null);
        sometext.setText(sr.getDescription());

    }
}
