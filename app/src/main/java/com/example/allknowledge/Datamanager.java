package com.example.allknowledge;

import android.content.Context;

import com.example.allknowledge.model.ListWithText;
import com.example.allknowledge.model.UrlModel;
import com.example.testzadanie.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class Datamanager {

    private List<Recipe> recipes;
    private Context context;
    private List<UrlModel> urlModels = new ArrayList<>();
    private List<ListWithText> lwt = new ArrayList<>();

    public Datamanager(Context applicationContext) {
        this.context = applicationContext;
        setUrl();
        setList();
    }

    public void setLwt(List<ListWithText> lwt) {
        this.lwt = lwt;
    }

    public List<ListWithText> getLwt() {
        return lwt;
    }

    public void  setRecipesModels(List<Recipe> reps)
    {
        recipes = new ArrayList<>();

        for (int i = 0 ; i<reps.size();i++)
        {
            recipes.add(reps.get(i));
        }
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public List<UrlModel> getUrlModels() {

        return urlModels;
    }


    private void  setList ()
    {
        lwt.add(new ListWithText(R.drawable.tel,"Разработка мобильных приложений",false, 55.77144241333008, 49.23350524902344));
        lwt.add(new ListWithText(R.drawable.robot,"Мобильная робототехника",false,55.78217228729694, 49.18973922729492));
        lwt.add(new ListWithText(R.drawable.pk,"It решения для бизнеса",true,55.78342718541095,49.132919311523445));
     }

    private void setUrl() {
        urlModels.add(new UrlModel("Девочка","https://sgi3.ufanet.vkuseraudio.net/p2/6a970dead129bc.mp3?extra=rkWPfWPcKtKdHjyXAncapFFt91EVypj35mKJ2dN1-a80MZ9kQ4nRadfrAtxSAIkeGXWXV50H78Wt__iChyC6LxgW0mwvday6oZH8sLlU4Ij6MqoKrVpr-qEQ7-yXEfJe8K5Y4iU0br9hSFzrJyhKpLs"));
        urlModels.add(new UrlModel("Мой Калашников","https://sgi3.ufanet.vkuseraudio.net/p1/810460b08601a6.mp3?extra=rVbkk7lQGvMLOQt4VCWIV30sge9o-Q4tn1nOMqFBQD-ezzbNf-8-zAth3Yx49wt3fiPPJDXK_m9ZABgz35qCduj5OzjwUerggzYwcs8KD4-cHMLBuHl3y-so70TkEpmktBPIzrbPldqkrZ8RNkYx97c"));
        urlModels.add(new UrlModel("ТУРБО ПУШКА", "https://sgi2.ufanet.vkuseraudio.net/p3/305820b8886cd1.mp3?extra=tv5dxzn23R08nwCWGV0E8X_4dW7DGBjCLnkgXNJ6kwfQESz89kpwFcQMc5eu5Eza4TNfTEqZoYhdBHBzNY_dTP3yjCFtf9s1_GCDp9x-ejH25zpAQCFQg10P5hynA5440rMePVDbicrRM48SnVomvmM"));
    }

}
