package com.example.demo.infydemosample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Heroadapter to append heroes listdata to UI
 */
public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> {
    private List<HeroModel> mHeroList;
    private Context mContext;


    public HeroAdapter(List<HeroModel> heroList, Context context) {
        this.mHeroList = heroList;
        this.mContext = context;
    }

    public void setHeroList(List<HeroModel> mHeroList) {
        this.mHeroList = mHeroList;
        notifyDataSetChanged();
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listhero_row_layout, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HeroViewHolder holder, final int position) {
        HeroModel hero = mHeroList.get(position);
        holder.textViewName.setText(hero.getName());
        holder.textViewRealName.setText(hero.getRealname());
        holder.textViewTeam.setText(hero.getTeam());


        Glide.with(mContext).load(hero.getImageurl()).into(holder.heroImg);

    }

    @Override
    public int getItemCount() {
        return mHeroList.size();
    }




    class HeroViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewRealName;
        TextView textViewTeam;
        ImageView heroImg;

        HeroViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.name_value);
            textViewRealName = (TextView) itemView.findViewById(R.id.realname_value);
            textViewTeam = (TextView) itemView.findViewById(R.id.team_value);
            heroImg = (ImageView) itemView.findViewById(R.id.hero_img);


        }
    }


}
