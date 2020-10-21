package com.panjihadjarati.submissiondicoding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE = "extra_image";
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_DETAIL = "extra_detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvNamaClub = findViewById(R.id.tv_nama_club);
        TextView tvDetailClub = findViewById(R.id.tv_detail_club);
        CircleImageView imgPhoto = findViewById(R.id.profile_image);

        String name = getIntent().getStringExtra(EXTRA_NAME);
        String detail = getIntent().getStringExtra(EXTRA_DETAIL);
        int photo = getIntent().getIntExtra(EXTRA_IMAGE,0);

        Glide.with(getApplicationContext())
                .load(photo)
                .apply(new RequestOptions().override(55, 55))
                .into(imgPhoto);

        tvNamaClub.setText(name);
        tvDetailClub.setText(detail);

    }
    public void goMain(View view) {
        Intent i = new Intent(getApplicationContext(),
                MainActivity.class);

        startActivity(i);
    }
}
