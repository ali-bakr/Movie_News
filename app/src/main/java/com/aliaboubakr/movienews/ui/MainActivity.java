package com.aliaboubakr.movienews.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.aliaboubakr.movienews.adapters.MovieSearchAdapter;
import com.aliaboubakr.movienews.adapters.PersonSearchAdapter;
import com.aliaboubakr.movienews.client.Api;
import com.aliaboubakr.movienews.client.RetrofitClient;
import com.aliaboubakr.movienews.models.MovieSearchModel;
import com.aliaboubakr.movienews.models.PersonSearchModel;
import com.aliaboubakr.movienews.R;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ImageView a;
    private EditText search ;
    final private String apiKey="a4c9732ea7cf1c37f3fdeef9da44c7af";
    private RecyclerView mResultsRecyclerView;
    private RecyclerView pResultsRecyclerView;
    private Retrofit retrofit= RetrofitClient.getInstance();
    private Api api =retrofit.create(Api.class);
    private MovieSearchAdapter movieSearchAdapter;
    private PersonSearchAdapter personSearchAdapter;

    // List personSearchResults =new List<>();
    private LinearLayoutManager vLinearLayoutManager;
    private LinearLayoutManager hLinearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        search=findViewById(R.id.search_et);
        mResultsRecyclerView=findViewById(R.id.rv_movies);
        pResultsRecyclerView=findViewById(R.id.rv_actors);

        vLinearLayoutManager = new LinearLayoutManager(this);
        vLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hLinearLayoutManager = new LinearLayoutManager(this);
        hLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mResultsRecyclerView.setHasFixedSize(true);
        pResultsRecyclerView.setHasFixedSize(true);

        pResultsRecyclerView.setLayoutManager(hLinearLayoutManager);
        mResultsRecyclerView.setLayoutManager(vLinearLayoutManager);



        findViewById(R.id.but_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMovieSearch();
                getPersonSearch();

                //  mResultsRecyclerView.setAdapter(personSearchAdapter);

            }
        });


    }




    private void getMovieSearch(){
        Call<MovieSearchModel> movieSearchModelCall=api.getMovieByQuery("a4c9732ea7cf1c37f3fdeef9da44c7af",search.getText().toString());

        movieSearchModelCall.enqueue(new Callback<MovieSearchModel>() {
            @Override
            public void onResponse(Call<MovieSearchModel> call, Response<MovieSearchModel> response) {
                if (response.body()!=null){
                    List movieSearchResultList= response.body().getMovieSearchResultList() ;
                    movieSearchAdapter=new MovieSearchAdapter(MainActivity.this,movieSearchResultList);
                    mResultsRecyclerView.setLayoutManager(vLinearLayoutManager);
                    mResultsRecyclerView.setAdapter(movieSearchAdapter);
                    LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation(MainActivity.this,R.anim.layout_slide_right);
                    mResultsRecyclerView.setLayoutAnimation(controller);
                    mResultsRecyclerView.scheduleLayoutAnimation();
                    mResultsRecyclerView.setAdapter(movieSearchAdapter);
                }
            }

            @Override
            public void onFailure(Call<MovieSearchModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getPersonSearch(){

        Call<PersonSearchModel>personSearchModelCall=api.getPersonByQuery(apiKey,search.getText().toString());
        personSearchModelCall.enqueue(new  Callback<PersonSearchModel>() {
            @Override
            public void onResponse(Call<PersonSearchModel> call, Response<PersonSearchModel> response) {
                if (response.body() != null) {
                    List personSearchResults=response.body().getPersonSearchResultList();
                    personSearchAdapter = new PersonSearchAdapter (MainActivity.this, personSearchResults);
                    pResultsRecyclerView.setLayoutManager(hLinearLayoutManager);
                    pResultsRecyclerView.setAdapter(personSearchAdapter);
                    //animation whill item loading
                    LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_slide_right);
                    pResultsRecyclerView.setLayoutAnimation(controller);
                    pResultsRecyclerView.scheduleLayoutAnimation();
                    pResultsRecyclerView.setAdapter(personSearchAdapter);

                }
            }
            @Override
            public void onFailure(Call<PersonSearchModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "check your internet connection !!", Toast.LENGTH_SHORT).show();

            }
        });


    }



}
