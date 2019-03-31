package tech.pcreate.multiselectlayout.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import tech.pcreate.multiselectlayout.R;
import tech.pcreate.multiselectlayout.genreselection.Genre;
import tech.pcreate.multiselectlayout.genreselection.GenreAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;

    private Button donebutton;

    private List<Genre> genrelist;
    private List<String> genresSelected = new ArrayList<>();

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.genrelist);
        recyclerView.setHasFixedSize(true);
        donebutton = findViewById(R.id.donebutton);

        progressDialog = new ProgressDialog(this);

        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);

        genrelist = getGenresList();

        GenreAdapter genreAdapter = new GenreAdapter(MainActivity.this, genrelist);
        recyclerView.setAdapter(genreAdapter);

        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(broadcastReceiver, new IntentFilter("genresMessage"));


        donebutton.setOnClickListener(view -> {

            if (genresSelected.size() >= 4){
                progressDialog.setMessage(getString(R.string.updating_genres_message));
                progressDialog.show();
//                Log.e("TAG","Output : " + genresSelected);

                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                    intent.putStringArrayListExtra("selected", (ArrayList<String>) genresSelected);
                    startActivity(intent);
                    progressDialog.cancel();
                }, 2000);

            }else Toast.makeText(MainActivity.this, R.string.at_least_4_message, Toast.LENGTH_SHORT).show();

        });
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra("getGenres");
            genresSelected.clear();
            if (text.equals("true")){
                donebutton.setVisibility(View.VISIBLE);
                for (Genre model : genrelist) {
                    if (model.isSelected()) {
                        genresSelected.add(model.getName());
                    }
                }
            }else{
                donebutton.setVisibility(View.GONE);
            }
        }
    };

    private List<Genre> getGenresList() {

        List <Genre> list = new ArrayList<>();

        list.add(new Genre("Love"));
        list.add(new Genre("Life"));
        list.add(new Genre("Technology"));
        list.add(new Genre("Art"));
        list.add(new Genre("Adult"));
        list.add(new Genre("Politics"));
        list.add(new Genre("Movies/TV"));
        list.add(new Genre("Sports"));
        list.add(new Genre("Science"));
        list.add(new Genre("Fashion"));
        list.add(new Genre("Religion"));
        list.add(new Genre("Money"));
        list.add(new Genre("History"));
        list.add(new Genre("Travel"));
        list.add(new Genre("Spirituality"));
        list.add(new Genre("Career"));
        list.add(new Genre("Animals"));

        return list;
    }
}

