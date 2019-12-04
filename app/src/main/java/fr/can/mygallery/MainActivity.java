package fr.can.mygallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.can.mygallery.adapter.UserAdapter;
import fr.can.mygallery.retrofit.api.model.SearchResult;
import fr.can.mygallery.retrofit.api.model.User;
import fr.can.mygallery.retrofit.api.service.UsersApi;
import fr.can.mygallery.view.GalleryListActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.search)
    protected SearchView searchView;

    @BindView(R.id.list)
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        userSearch();
    }

    public void userSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String username) {

                if (username == null) {
                    Toast.makeText(
                            MainActivity.this,
                            "Aucun utilisateur trouvé",
                            Toast.LENGTH_LONG).show();
                } else {
                    userlist(username);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    public void userlist(String username) {
        UsersApi.getInstance().usersLists(username)
                .enqueue(new Callback<SearchResult>() {
                    @Override
                    public void onResponse(Call<SearchResult> call, final Response<SearchResult> response) {

                        if (response.isSuccessful()) {
                            List<User> usersList = response.body().getUsers();

                            final UserAdapter userAdapter =
                                    new UserAdapter(MainActivity.this, usersList);

                            listView.setAdapter(userAdapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int id, long l) {
                                    Intent intent = new Intent(getApplicationContext(), GalleryListActivity.class);

                                    if (intent.resolveActivity(getPackageManager()) != null) {
                                        startActivity(intent);
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(
                                    MainActivity.this,
                                    "Aucun utilisateur trouvé",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchResult> call, Throwable t) {

                    }
                });
    }
}
