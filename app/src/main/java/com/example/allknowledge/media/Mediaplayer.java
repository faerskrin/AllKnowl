package com.example.allknowledge.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allknowledge.App;
import com.example.allknowledge.R;
import com.example.allknowledge.model.UrlModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Mediaplayer extends Activity implements  MusicAdapter.SetOnClickItem{

    private int i = 0;
    private MediaPlayer mediaplayer;
    private List<UrlModel> urlModels;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.textView_maxTime)
    TextView maxtime;
    @BindView(R.id.textView_currentPosion)
    TextView current;
    @BindView(R.id.button_start)
    ImageButton start;
    @BindView(R.id.button_pause)
    ImageButton pause;
    MusicAdapter adapter;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @OnClick (R.id.button_pause) void doPause() {
        mediaplayer.pause();
        pause.setVisibility(View.GONE);
        start.setVisibility(View.VISIBLE);
    }
    @OnClick (R.id.button_nextsong) void doNext() {
        i++;
        if (i>=urlModels.size()) { i=0; }
        this.mediaplayer.pause();
        seekBar.setProgress(0);
        maxtime.setText(urlModels.get(i).getName());
        mediaplayer = MediaPlayer.create(this, Uri.parse(urlModels.get(i).getUrl()));

        adapter.notifyItemChanged(adapter.getSelectpos());
        adapter.setSelectpos(i);
        adapter.notifyItemChanged(i);
        Handler thread = new Handler();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                seekBar.setMax(mediaplayer.getDuration()/1000);
                seekBar.setProgress(mediaplayer.getCurrentPosition()/1000);
                current.setText(ConvertDuration((long) mediaplayer.getCurrentPosition()));
                thread.postDelayed(this,1000);
                if (seekBar.getProgress() == mediaplayer.getDuration()/1000)
                {
                    doNext();
                }
            }
        });
        this.mediaplayer.start();

        pause.setVisibility(View.VISIBLE);
        start.setVisibility(View.INVISIBLE);
    }
    @OnClick (R.id.button_backsong) void  doBack(){
        i--;
        if (i==-1) { i=0; }
        this.mediaplayer.pause();
        seekBar.setProgress(0);
        maxtime.setText(urlModels.get(i).getName());
        mediaplayer = MediaPlayer.create(this, Uri.parse(urlModels.get(i).getUrl()));

        adapter.notifyItemChanged(adapter.getSelectpos());
        adapter.setSelectpos(i);
        adapter.notifyItemChanged(i);
        Handler thread = new Handler();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                seekBar.setMax(mediaplayer.getDuration()/1000);
                seekBar.setProgress(mediaplayer.getCurrentPosition()/1000);
                current.setText(ConvertDuration((long) mediaplayer.getCurrentPosition()));
                thread.postDelayed(this,1000);
                if (seekBar.getProgress() == mediaplayer.getDuration()/1000)
                {
                    doNext();
                }
            }
        });
        this.mediaplayer.start();
        pause.setVisibility(View.VISIBLE);
        start.setVisibility(View.INVISIBLE);
    }
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        ButterKnife.bind(this);

        urlModels = App.dm.getUrlModels();
        mediaplayer = MediaPlayer.create(getBaseContext(), Uri.parse(urlModels.get(i).getUrl()));
        handleSeekbar();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MusicAdapter(urlModels);
        adapter.setClickItem(this);
        recyclerView.setAdapter(adapter);


    }
    private void handleSeekbar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (mediaplayer!=null && b)
                {
                    mediaplayer.seekTo(i*1000);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    @OnClick (R.id.button_start) void doStart() {
        if (mediaplayer.isPlaying())
        {
            mediaplayer.stop();
            mediaplayer.reset();
        }
        else {
            maxtime.setText(urlModels.get(i).getName());
            mediaplayer.start();
            Handler thread = new Handler();
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    seekBar.setMax(mediaplayer.getDuration()/1000);
                    seekBar.setProgress(mediaplayer.getCurrentPosition()/1000);
                    current.setText(ConvertDuration((long) mediaplayer.getCurrentPosition()));
                    thread.postDelayed(this,1000);
                    if (seekBar.getProgress() == mediaplayer.getDuration()/1000)
                    {
                        doNext();
                    }
                }
            });
        }
        this.pause.setVisibility(View.VISIBLE);
        this.start.setVisibility(View.INVISIBLE);
    }
    private String ConvertDuration(long currentPosition) {
        long minutes = (currentPosition/1000)/60;
        long seconds = (currentPosition/1000)%60;
        String conveted = String.format("%d:%02d",minutes,seconds);
        return conveted;
    }
    @Override public void Start(UrlModel urlModel, int pos) {
            mediaplayer.pause();
            seekBar.setProgress(0);
            i=pos;
            maxtime.setText(urlModel.getName());
            mediaplayer = MediaPlayer.create(this, Uri.parse(urlModel.getUrl()));
            mediaplayer.start();
            adapter.notifyDataSetChanged();
            adapter.setSelectpos(i);
            adapter.notifyItemChanged(i);
        Handler thread = new Handler();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                seekBar.setMax(mediaplayer.getDuration()/1000);
                seekBar.setProgress(mediaplayer.getCurrentPosition()/1000);
                current.setText(ConvertDuration((long) mediaplayer.getCurrentPosition()));
                thread.postDelayed(this,1000);
                if (seekBar.getProgress() == mediaplayer.getDuration()/1000)
                {
                    doNext();
                }
            }
        });

        pause.setVisibility(View.VISIBLE);
        start.setVisibility(View.INVISIBLE);
    }


}
