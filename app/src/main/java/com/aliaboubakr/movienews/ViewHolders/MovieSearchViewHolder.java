package com.aliaboubakr.movienews.ViewHolders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aliaboubakr.movienews.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

public class MovieSearchViewHolder extends RecyclerView.ViewHolder {

    private KenBurnsView posterImageView;
    public TextView posterTitle;
    public TextView posterDate;
    public TextView posterAbout;
    public TextView posterRate;
    public TextView posterAdeult;
    public TextView posterVotes;

    public MovieSearchViewHolder(@NonNull View itemView) {
        super(itemView);
        posterImageView=itemView.findViewById(R.id.iv_srch_mov_poster);
        posterTitle=itemView.findViewById(R.id.tv_srch_mov_title);
        posterDate=itemView.findViewById(R.id.tv_srch_mov_date);
        posterAbout=itemView.findViewById(R.id.tv_srch_mov_about);
        posterRate=itemView.findViewById(R.id.tv_srch_mov_rate);
        posterAdeult=itemView.findViewById(R.id.tv_srch_mov_adult);
        posterVotes=itemView.findViewById(R.id.tv_srch_mov_votes);

      //  RandomTransitionGenerator generator=new RandomTransitionGenerator(500,new DecelerateInterpolator());
       // posterImageView.setTransitionGenerator(generator);

    }

    public void setPosterImageView(Context context ,String posterUrl) {
        Picasso.get().load(posterUrl).into(posterImageView);

}




}
