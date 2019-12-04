package fr.can.mygallery.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.can.mygallery.R;
import fr.can.mygallery.adapter.GalleryAdapter;
import fr.can.mygallery.retrofit.api.model.*;
import fr.can.mygallery.retrofit.api.model.SearchResult;
import fr.can.mygallery.retrofit.api.service.UsersApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryListActivity extends AppCompatActivity {

    @BindView(R.id.galleryList)
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallerylist);
        ButterKnife.bind(this);

        //test
        galleryList(1);
    }

    public void galleryList(Integer userID) {
        UsersApi.getInstance().userGallery(userID)
                .enqueue(new Callback<SearchResult>() {
                    @Override
                    public void onResponse(Call<SearchResult> call, final Response<SearchResult> response) {

                        if (response.isSuccessful()) {
                            /*List<Gallery> galleryList = response.body().getTitle();

                            final GalleryAdapter GalleryAdapter =
                                    new GalleryAdapter(GalleryListActivity.this, galleryList);

                            listView.setAdapter(GalleryAdapter);*/

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int id, long l) {
                                    Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);

                                    if (intent.resolveActivity(getPackageManager()) != null) {
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchResult> call, Throwable t) {

                    }
                });
    }
}
