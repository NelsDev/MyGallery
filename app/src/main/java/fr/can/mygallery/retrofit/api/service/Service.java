package fr.can.mygallery.retrofit.api.service;

import fr.can.mygallery.retrofit.api.model.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("users/")
    Call<SearchResult> usersLists(@Query("username") String username);

    @GET("{userID}/albums")
    Call<SearchResult> userGallery(@Path("userID") int userID);

    @GET("{userID}/photos")
    Call<SearchResult> userPhotos(@Path("albumID") int albumID);

}
