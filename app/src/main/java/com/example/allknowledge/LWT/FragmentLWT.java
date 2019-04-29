package com.example.allknowledge.LWT;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.allknowledge.App;
import com.example.allknowledge.R;
import com.example.allknowledge.model.ListWithText;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

public class FragmentLWT extends Fragment implements CompoundButton.OnCheckedChangeListener {

    private ListWithText listWithTexts;
    @BindView(R.id.fragment_text)
    TextView textView;
    @BindView(R.id.fragment_image)
    ImageView imageView;
    @BindView(R.id.fragment_switch)
    SwitchMaterial switchMaterial;
    private int pos;
    private List<ListWithText> lwt = new ArrayList<>();

    public FragmentLWT(ListWithText lwt, int position ) {
        pos=position;
        listWithTexts = lwt;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragmentlist,container,false);
        ButterKnife.bind(this,view);
        textView.setText(listWithTexts.getName());
        Glide.with(imageView)
                .load(listWithTexts.getImage())
                .into(imageView);
        lwt = App.dm.getLwt();

        if (listWithTexts.getSituation()) {
            switchMaterial.setChecked(true);
        } else  switchMaterial.setChecked(false);
        switchMaterial.setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b)
            {
                lwt.get(pos).setSituation(true);
                App.dm.setLwt(lwt);
            }
            else
            {
                lwt.get(pos).setSituation(false);
                App.dm.setLwt(lwt);
            }
    }


}
