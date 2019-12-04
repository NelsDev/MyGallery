package fr.can.mygallery.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.can.mygallery.R;

public class GalleryActivity extends AppCompatActivity {

    @BindView(R.id.galleryGridView)
    protected GridView galleryGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

    }
}