package com.example.allknowledge;
import com.example.testzadanie.model.RecipesModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiHelper {


    String BASEURL = "https://test.kode-t.ru/";


    @GET("recipes")
    Observable<RecipesModel> getRecipes();



    class Creator
    {
        public static ApiHelper newApi()
        {
            OkHttpClient okhtp = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15,TimeUnit.SECONDS)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .client(okhtp)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(ApiHelper.class);
        }
    }

}
