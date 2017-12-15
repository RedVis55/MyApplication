package com.example.elv.f1stats;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainList extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        String url ="http://poetrydb.org/title";
//        ArrayList<String> myArray = new ArrayList<>() ;
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest jsonobject = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) { Log.d("response", response.toString());
                        JSONArray jarray;

                        try {
                            jarray = response.getJSONArray("titles");
                            for (int i = 0; i < jarray.length(); i++) {

                                newArray.add(jarray.getString(i));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        setAdapter();

                    }
                }

                , null);
        queue.add(jsonobject);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_list, container, false);
    }

    ArrayList<String> newArray = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void setAdapter() {
        ArrayAdapter<String> myAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, newArray);

        this.setListAdapter(myAdapter);
    }



//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
////        super.onListItemClick(l, v, position, id);
//    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
//    {
//        String data=(String)arg0.getItemAtPosition(arg2);
//
//    };












}