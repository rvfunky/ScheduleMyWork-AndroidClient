package com.example.raghavendrkolisetty.schedulemywork;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.Intent;


import static com.example.raghavendrkolisetty.schedulemywork.R.id.datePicker;
import static com.example.raghavendrkolisetty.schedulemywork.R.id.schedulesLayout;
import static com.example.raghavendrkolisetty.schedulemywork.R.id.upcomingLinearLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SchedulesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SchedulesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchedulesFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SchedulesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SchedulesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SchedulesFragment newInstance(String param1, String param2) {
        SchedulesFragment fragment = new SchedulesFragment();
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
        final LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_schedules,container,false);
        LinearLayout  upcomingLinearLayout= (LinearLayout) linearLayout.findViewById(R.id.upcomingLinearLayout);
        ImageView homeImage = (ImageView) linearLayout.findViewById(R.id.homeImage);
        LinearLayout datepicker=(LinearLayout) linearLayout.findViewById(R.id.datePicker);
        homeImage.setOnClickListener(this);
        upcomingLinearLayout.setOnClickListener(this);
        datepicker.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        Fragment fragment=null;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.upcomingLinearLayout:
                System.out.println("hitting");
                try {
                    fragment = (Fragment) UpcomingFragment.newInstance("a","b");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.flContent, fragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
                break;
            case R.id.homeImage:
                System.out.println("in homeimage handler");
                try {
                    fragment = (Fragment) HomeFragment.newInstance("a","b");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.flContent, fragment);
                //transaction.addToBackStack(null);
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                // Commit the transaction
                transaction.commit();
                break;
            case R.id.datePicker:
//                System.out.println("in date picker");
//                Intent intent = new Intent(getActivity(), DatetimeActivity.class);
//                startActivity(intent);
               try {
                  fragment = (Fragment) DatepickerFragment2.newInstance("a","b");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.flContent, fragment);
                //transaction.addToBackStack(null);
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                // Commit the transaction
                transaction.commit();
                break;





        }
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
}
