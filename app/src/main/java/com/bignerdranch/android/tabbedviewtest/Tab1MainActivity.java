package com.bignerdranch.android.tabbedviewtest;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Tab1MainActivity extends android.support.v4.app.Fragment{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Hotel> mHotelData = new ArrayList<Hotel>();

    private static final String TAG = "Tab1MainActivity";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_main_recycler, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        // Create new LayoutManager and attach to RecyclerView
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        httpRequest(); // Gets JSON data from a URL

        // Create new Adapter and attach to RecyclerView
        mAdapter = new Tab1Adapter(mHotelData);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    // Note: this code was copied from: https://github.com/codepath/android_guides/wiki/Using-Android-Async-Http-Client
    //       Don't put this code in an Adapter, because List<Hotel> should be populated first before being passed to the Adapter
    public void httpRequest(){
        String url = "http://raz-test.herokuapp.com/api/v1/images/get";
        AsyncHttpClient client = new AsyncHttpClient(); // Rz - This opens a new thread that runs in the background
        client.get(url, new JsonHttpResponseHandler() { // Rz - Handles parsing the JSON data from the URL
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray dataArray = response.getJSONArray("data");
                    mHotelData.clear(); // Rz. So that array does not keep on accumulating new Hotel Objects
                    for(int i = 0; i < dataArray.length(); i++) {
//                        JSONObject data = dataArray.getJSONObject(i);
//                        String title = data.getString("title");
//                        String url = data.getString("image_url");
                        String title = dataArray.getJSONObject(i).getString("title");
                        String url = dataArray.getJSONObject(i).getString("image_url");
                        mHotelData.add(new Hotel(url, title));
                    }
                    //
                    mAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, response.toString());
                // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                // Handle resulting parsed JSON response here
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }
        });
    }

}
