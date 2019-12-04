package fr.can.mygallery.retrofit.api.service;

import android.support.annotation.NonNull;
import android.util.Log;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersApi {

    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private static Service service;

    static public @NonNull
    Service getInstance() {
        if (service == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(Service.class);
        }
        return service;
    }
}
