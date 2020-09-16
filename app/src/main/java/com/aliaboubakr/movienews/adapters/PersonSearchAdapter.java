package com.aliaboubakr.movienews.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.aliaboubakr.movienews.models.PersonSearchModel;
import com.aliaboubakr.movienews.ui.PersonProfile;
import com.aliaboubakr.movienews.R;
import com.aliaboubakr.movienews.ViewHolders.PersonSearchViewHolder;

import java.util.List;

public class PersonSearchAdapter  extends RecyclerView.Adapter<PersonSearchViewHolder> {
private String about;
    private Activity activity;
    private List<PersonSearchModel.PersonSearchResult> psresults;

    public PersonSearchAdapter(Activity activity, List<PersonSearchModel.PersonSearchResult> psresults) {
        this.activity = activity;
        this.psresults = psresults;
    }

    @NonNull
    @Override
    public PersonSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v= LayoutInflater.from(activity).inflate(R.layout.search_person_items,parent,false);
      //  View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.search_person_items,parent,false);
        return new PersonSearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonSearchViewHolder holder, int position) {

        final PersonSearchModel.PersonSearchResult personSearchResultList=psresults.get(position);
      //  Picasso.get().load("https://image.tmdb.org/t/p/original/3oWEuo0e8Nx8JvkqYCDec2iMY6K.jpg").into(holder.posterImageView);

       about=personSearchResultList.getKnownForDepartment()+"* ";
    for (int i = 0; i <personSearchResultList.getKnownFor().size() ; i++) {
            about+=personSearchResultList.getKnownFor().get(i).getTitle()+", ";
        }

        holder.setPosterImageView(activity,"https://image.tmdb.org/t/p/original"+personSearchResultList.getProfilePath());
        holder.posterTitle.setText(personSearchResultList.getName());
        holder.posterWorks.setText(about);
        Log.e("size",""+personSearchResultList.getKnownFor().size() );

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                PersonProfile personProfile=new PersonProfile();
                activity.getSupportFragmentManager().beginTransaction().add(R.id.frag_container, personProfile).addToBackStack(null).commit();

                Bundle bundle2 = new Bundle();
                bundle2.putInt("person_id", personSearchResultList.getId());
                personProfile.setArguments(bundle2);

            }
        });

        //    Picasso.get().load("http://ipda3-tech.com/blood-bank/uploads/posts/156198883772753.jpg").into(holder.posterImageView);
       //  holder.setPosterImageView(activity,personSearchResultList.getKnownFor().get(0).getPosterPath());
      // holder.posterWorks.setText(personSearchResultList.getKnownForDepartment() +"* "+personSearchResultList.getKnownFor().get(position).getOriginalTitle());
//holder.posterWorks.setText(personSearchResultList.getKnownFor().get(position).getOverview());


    }

    @Override
    public int getItemCount() {
        return psresults.size();
    }
}
