package com.project.radioetzion;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    ArrayList<StreamItems> streamItems = new ArrayList<>();
     RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        setPointer();

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new StreamAdapter(streamItems, new StreamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StreamItems item) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
//                alertBuilder.setTitle(item.getTxtStream())
//                        .set
            }
        });

        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);

        Log.e(TAG, "setPointer: " + "hiwwww" );
        return view;

    }

    private void setPointer() {
        Log.e(TAG, "setPointer: " + "hi" );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://be.repoai.com:5080/WebRTCAppEE/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONHandler jsonHandler = retrofit.create(JSONHandler.class);

        Call<List<JSONData>> call = jsonHandler.getJson();
        call.enqueue(new Callback<List<JSONData>>() {
            @Override
            public void onResponse(Call<List<JSONData>> call, Response<List<JSONData>> response) {
                Log.d(TAG, "onResponse: " + response.body().get(0).getVodName());
                Calendar c = Calendar.getInstance();

                Log.e(TAG, "onCreateView: " +
                        c.get(Calendar.DAY_OF_MONTH) +"/"+
                        c.get(Calendar.MONTH) +"/"+
                        c.get(Calendar.YEAR)
                );

                for (int i = 0; i < response.body().size(); i++){
                    c.setTimeInMillis( Long.parseLong(response.body().get(i).getCreationDate()));
                    String currentDate = c.get(Calendar.DAY_OF_MONTH) + "/"+ c.get(Calendar.MONTH) +"/"+ c.get(Calendar.YEAR);

                    streamItems.add(new StreamItems(R.drawable.ic_slow_motion_video_black_24dp,
                            response.body().get(i).getVodName().replace("_"," ").replace(".mp4", " "),
                            response.body().get(i).getDuration() ,
                            currentDate));
                    Log.e(TAG, "onResponse: " + currentDate );
                }
                mRecyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<List<JSONData>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
