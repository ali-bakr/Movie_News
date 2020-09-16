package com.aliaboubakr.movienews.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.aliaboubakr.movienews.client.Api;
import com.aliaboubakr.movienews.client.RetrofitClient;
import com.aliaboubakr.movienews.models.PersonProfileModel;
import com.aliaboubakr.movienews.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PersonProfile extends Fragment {

    private int person_id;

    final private String apiKey="a4c9732ea7cf1c37f3fdeef9da44c7af";
     private TextView name,job,biography,birthdate,gender,deathdate,placeOfBirth,alsoKnownFor;
     private ImageView profilel;
     private KenBurnsView cover;
     private String facelink,instalink,twitterlink,flag,link="https://image.tmdb.org/t/p/original";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.person_profile,container,false);

        name=v.findViewById(R.id.tv_per_Prof_name);
        job=v.findViewById(R.id.tv_per_Prof_job);
        biography=v.findViewById(R.id.tv_per_Prof_biography);

        birthdate=v.findViewById(R.id.tv_per_Prof_birthdate);


        deathdate=v.findViewById(R.id.tv_per_Prof_deathdate);
        gender=v.findViewById(R.id.tv_per_Prof_gender);

        placeOfBirth=v.findViewById(R.id.tv_per_Prof_bearthplace);

        profilel=v.findViewById(R.id.iv_per_pro_poster);
        cover=v.findViewById(R.id.iv_per_Prof_cover);
alsoKnownFor=v.findViewById(R.id.tv_per_Prof_alsoknownfor);

        v.findViewById(R.id.iv_per_Prof_face).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        v.findViewById(R.id.iv_per_Prof_insta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        v.findViewById(R.id.iv_per_Prof_twitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        Bundle bundle = this.getArguments();
        if (bundle != null) {
            person_id = bundle.getInt("person_id", 0);


        }
        getPersonProfile();

        return v;
    }



    private void getPersonProfile(){

       Retrofit retrofit= RetrofitClient.getInstance();
       Api api =retrofit.create(Api.class);

        Call<PersonProfileModel> personProfileModelCall =api.getPersonProfileById(person_id,apiKey);

       personProfileModelCall.enqueue(new Callback<PersonProfileModel>() {
           @Override
           public void onResponse(Call<PersonProfileModel> call, Response<PersonProfileModel> response) {

               name.setText(response.body().getName());
               job.setText(response.body().getKnownForDepartment());
               if (response.body().getGender()==2){ gender.setText("male");}
               else if(response.body().getGender()==1){ gender.setText("female");}

               biography.setText(response.body().getBiography());
             //  site.setText(response.body().getHomepage().toString());
               birthdate.setText(response.body().getBirthday());
//               deathdate.setText(response.body().getDeathday().toString());
               placeOfBirth.setText(response.body().getPlaceOfBirth());

               for (int i = 0; i < response.body().getAlsoKnownAs().size(); i++) {
                   flag+="\n*"+response.body().getAlsoKnownAs().get(i);
               }

               alsoKnownFor.setText(flag);

               Picasso.get().load("https://image.tmdb.org/t/p/original"+response.body().getProfilePath()).into(profilel);
               Picasso.get().load("https://image.tmdb.org/t/p/original"+response.body().getProfilePath()).into(cover);
           }
           @Override
           public void onFailure(Call<PersonProfileModel> call, Throwable t) {
               Log.e("--------","no true");
           }
       });

    }




}
