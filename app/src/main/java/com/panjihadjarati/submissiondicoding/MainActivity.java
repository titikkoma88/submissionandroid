package com.panjihadjarati.submissiondicoding;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvClubs;
    private ArrayList<Club> list = new ArrayList<>();
    private String title = "Mode List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setActionBarTitle(title);

        rvClubs = findViewById(R.id.rv_clubs);
        rvClubs.setHasFixedSize(true);

        list.addAll(ClubsData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList() {

        rvClubs.setLayoutManager(new LinearLayoutManager(this));
        ListClubAdapter listClubAdapter = new ListClubAdapter(list);
        rvClubs.setAdapter(listClubAdapter);

        listClubAdapter.setOnItemClickCallback(new ListClubAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Club data) {
                showSelectedClub(data);
            }
        });
    }

    private void showRecyclerGrid(){
        rvClubs.setLayoutManager(new GridLayoutManager(this, 2));
        GridClubAdapter gridClubAdapter = new GridClubAdapter(list);
        rvClubs.setAdapter(gridClubAdapter);

        gridClubAdapter.setOnItemClickCallback(new GridClubAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Club data) {
                showSelectedClub(data);
            }
        });
    }

    private void showRecyclerCardView(){
        rvClubs.setLayoutManager(new LinearLayoutManager(this));
        CardViewClubAdapter cardViewHeroAdapter = new CardViewClubAdapter(list);
        rvClubs.setAdapter(cardViewHeroAdapter);
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void  showSelectedClub(Club club) {
        Toast.makeText(this, "Kamu memilih " + club.getName(), Toast.LENGTH_SHORT).show();

        String clubName = club.getName();
        String clubDetail = club.getDetail();
        int clubPhoto = club.getPhoto();

        Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
        detailIntent.putExtra(DetailActivity.EXTRA_NAME, clubName);
        detailIntent.putExtra(DetailActivity.EXTRA_DETAIL, clubDetail);
        detailIntent.putExtra(DetailActivity.EXTRA_IMAGE, clubPhoto);
        startActivity(detailIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_list:
                showRecyclerList();
                break;

            case R.id.action_grid:
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                showRecyclerCardView();
                break;

            case R.id.action_about:
                Intent i = new Intent(getApplicationContext(),
                        AboutActivity.class);
                startActivity(i);
                break;
            case R.id.action_exit:
                AlertDialog.Builder ab =
                        new AlertDialog.Builder(MainActivity.this);

                ab.setTitle("Confirmation");
                ab.setIcon(R.drawable.ic_check_black_24dp);
                ab.setMessage("Are you sure to exit?");
                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                });
                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ab.show();
                break;
        }

        setActionBarTitle(title);
    }


}
