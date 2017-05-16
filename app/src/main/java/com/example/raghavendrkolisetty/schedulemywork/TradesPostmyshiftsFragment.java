package com.example.raghavendrkolisetty.schedulemywork;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TradesPostmyshiftsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TradesPostmyshiftsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TradesPostmyshiftsFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<RowItem> rowItems;
    List<RowItem> t;
    JSONObject response=new JSONObject();

    private OnFragmentInteractionListener mListener;

    public TradesPostmyshiftsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TradesPostmyshiftsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TradesPostmyshiftsFragment newInstance(String param1, String param2) {
        TradesPostmyshiftsFragment fragment = new TradesPostmyshiftsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("postmyshifts oncreateview method");
        final LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_trades_postmyshifts,container,false);
        ImageView homeImage = (ImageView) linearLayout.findViewById(R.id.homeImage);
        homeImage.setOnClickListener(this);
        final ListView lv=(ListView) linearLayout.findViewById(R.id.list);
        final List<RowItem> output = new ArrayList<RowItem>();
        lv.setOnItemClickListener(this);


        JSONObject jsonObject = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://54.71.67.192:5000/shifts/user", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response1) {
                System.out.println("in JSON response"+response1.toString());
                response=response1;

//                try {
//                    frameData();
//                }
//                catch (JSONException e)
//                {
//                    e.printStackTrace();
//                }

                System.out.println("in testing"+response1.toString());
                JSONObject issueObj = response1;
                Iterator keysToCopyIterator = issueObj.keys();
                while (keysToCopyIterator.hasNext()) {
                    String key = (String) keysToCopyIterator.next();
                    JSONArray keyArray=new JSONArray();
                    try {
                        keyArray = issueObj.getJSONArray(key);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                    Iterator itemIterator=null;
                    for(int i=0;i<keyArray.length();i++)
                    {
                        try {
                            itemIterator = keyArray.getJSONObject(i).keys();
                        }
                        catch(JSONException e)
                        {
                            e.printStackTrace();
                        }
                        String []itemValuesArray=new String[4];
                        int k=0;
                        while (itemIterator.hasNext()) {
                            String itemKey = (String) itemIterator.next();
                            try {
                                itemValuesArray[k++] = keyArray.getJSONObject(i).getString(itemKey);
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }

                        }
                        System.out.println("hello"+itemValuesArray[0]);
                        RowItem item=new RowItem(itemValuesArray[0],itemValuesArray[2],itemValuesArray[1]);
                        output.add(item);
                        System.out.println("reached here");
                        rowItems=output;
                        CustomAdapter adapter = new CustomAdapter(getActivity(),output);
                        lv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                }







                SharedPreferences prefs = getDefaultSharedPreferences(getActivity().getApplicationContext());
                System.out.println("checking prefs"+prefs.getString("access_token",null));
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if(networkResponse!=null){
                    if(networkResponse.statusCode==400){
                        System.out.println("user already exists");
                    }
                }
                System.out.println("in error listener response"+error);
            }
        })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                SharedPreferences prefs = getDefaultSharedPreferences(getActivity().getApplicationContext());
                System.out.println("accessing token from homeactivity"+prefs.getString("access_token",null));
                String access_token = prefs.getString("access_token",null);
                headers.put("Authorization", "JWT "+access_token);
                return headers;
            }
        };



        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(jsonObjectRequest);


        return linearLayout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void onClick(View v) {
            android.app.Fragment fragment = null;
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            switch (v.getId()){
                case R.id.homeImage:
                    System.out.println("in homeimage handler");
                    try {
                        fragment = (android.app.Fragment) HomeFragment.newInstance("a","b");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    transaction.replace(R.id.flContent, fragment);
                    //transaction.addToBackStack(null);
                    fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    // Commit the transaction
                    transaction.commit();
                    break;


        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        String day = rowItems.get(position).getDay();
        System.out.println("checking day is"+day);



        String startTime=rowItems.get(position).getStartTime();
        String endTime=rowItems.get(position).getEndTime();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("startTime", startTime);
            jsonObject.put("endTime",endTime);
            jsonObject.put("day",day);
        }catch (Exception e){
            System.out.println("in the json exception phase");
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://54.71.67.192:5000/trade/offer", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("in JSON response"+response.toString());
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if(networkResponse!=null){
                    if(networkResponse.statusCode==400){
                        System.out.println("user already exists");
                    }
                }
                System.out.println("in error listener response"+error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( getActivity() );
                System.out.println("accessing token from homeactivity"+prefs.getString("access_token",null));
                String access_token = prefs.getString("access_token",null);
                headers.put("Authorization", "JWT "+access_token);
                return headers;
            }
        };



        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(jsonObjectRequest);



    }
}
