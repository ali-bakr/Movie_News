package com.aliaboubakr.movienews.ViewHolders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aliaboubakr.movienews.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

public class PersonSearchViewHolder extends RecyclerView.ViewHolder {
    public KenBurnsView posterImageView;
    public TextView posterTitle;
    public TextView posterWorks;
    public PersonSearchViewHolder(@NonNull View itemView) {

        super(itemView);
        posterImageView=itemView.findViewById(R.id.tv_srch_actor_poster);
        posterTitle=itemView.findViewById(R.id.tv_srch_actor_title);
        posterWorks=itemView.findViewById(R.id.tv_srch_actor_overview);
       // RandomTransitionGenerator generator=new RandomTransitionGenerator(500,new DecelerateInterpolator());
     //   posterImageView.setTransitionGenerator(generator);

    }
    public void setPosterImageView(Context context , String posterUrl) {
        Picasso.get().load(posterUrl).into(posterImageView);

    }
}
