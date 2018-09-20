package com.example.demo.infydemosample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.widget.Toast;

/**
 * A placeholder fragment containing a recyclerview to show heroes list.
 */
public class HeroFragment extends Fragment {

    private RecyclerView mHeroRV;
    private List<HeroModel> mHeroList;
    private HeroAdapter mHeroAdapter;
    private HeroActivity mActivity;

    public HeroFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mActivity = (HeroActivity) getActivity();
        mActivity.getSupportActionBar().setTitle(R.string.marvel_hero);
        //initialize all the views
        initializeViews(view);
        return view;

    }

    /**
     * Initializes all the views
     *
     * @param view
     */
    private void initializeViews(View view) {
        mHeroRV = (RecyclerView) view.findViewById(R.id.hero_rv);
        mHeroRV.setHasFixedSize(true);
        mHeroRV.setLayoutManager(new LinearLayoutManager(mActivity));
        mHeroRV.addItemDecoration(new DividerItemDecoration(mActivity, LinearLayoutManager.VERTICAL));
        mHeroList = new ArrayList<>();
        mHeroAdapter = new HeroAdapter(mHeroList, mActivity);
        mHeroRV.setAdapter(mHeroAdapter);
        //get List of heroes
        getHeroes();

    }

    /**
     * Retrive the heroes list from the server
     */
    private void getHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();


        Api api = retrofit.create(Api.class);

        Call<List<HeroModel>> call = api.getHeroes();

        call.enqueue(new Callback<List<HeroModel>>() {
            @Override
            public void onResponse(Call<List<HeroModel>> call, Response<List<HeroModel>> response) {
                mHeroList = response.body();


                //displaying the string array into listview
                if (mHeroList != null) {
                    mHeroAdapter.setHeroList(mHeroList);
                }

            }

            @Override
            public void onFailure(Call<List<HeroModel>> call, Throwable t) {
                Toast.makeText(mActivity.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
