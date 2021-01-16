package com.aoinc.moviedb_networkcalls_practice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.aoinc.moviedb_networkcalls_practice.R;
import com.aoinc.moviedb_networkcalls_practice.model.TmdbResponse;
import com.aoinc.moviedb_networkcalls_practice.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(MainActivityViewModel.class);

        mainActivityViewModel.discoverTmdbMoviesWithGenre(12, 9648)
                .observe(this, new Observer<TmdbResponse>() {
            @Override
            public void onChanged(TmdbResponse tmdbResponse) {
                Log.d("search_results", tmdbResponse.getMovies().get(0).getTitle());
            }
        });
    }
}