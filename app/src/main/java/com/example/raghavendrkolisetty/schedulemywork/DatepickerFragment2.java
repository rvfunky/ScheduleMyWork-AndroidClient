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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.DialogFragment;


import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DatepickerFragment2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DatepickerFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatepickerFragment2 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "Sample";
    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";
    private static final String TAG_DATETIME_FRAGMENT1 = "TAG_DATETIME_FRAGMENT";
    private static final String STATE_TEXTVIEW = "STATE_TEXTVIEW";
    private static final String STATE_TEXTVIEW1 = "STATE_TEXTVIEW";
    private TextView textView;
    private TextView textView1;
    private Button buttonView;
    private  Button buttonView1;
    private SwitchDateTimeDialogFragment dateTimeFragment;
    private SwitchDateTimeDialogFragment dateTimeFragment1;





    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DatepickerFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatepickerFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static DatepickerFragment2 newInstance(String param1, String param2) {
        DatepickerFragment2 fragment = new DatepickerFragment2();
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

        // Construct SwitchDateTimePicker
        dateTimeFragment = SwitchDateTimeDialogFragment.newInstance(
                getString(R.string.label_datetime_dialog),
                getString(R.string.positive_button_datetime_picker),
                getString(R.string.negative_button_datetime_picker)
        );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_datepicker_fragment2, container, false);
        LinearLayout startLayout = (LinearLayout)linearLayout.findViewById(R.id.startLayout);
        LinearLayout endLayout = (LinearLayout)linearLayout.findViewById(R.id.EndLayout);
        startLayout.setOnClickListener(this);
        endLayout.setOnClickListener(this);
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

        switch (v.getId()) {
            case R.id.startLayout:
                System.out.println("hitting start laout");

                DialogFragment newFragment = new DateTimeDialogFragment();
                newFragment.show(getFragmentManager(), "timePicker");
//                try {
//                    fragment = (SwitchDateTimeDialogFragment) dateTimeFragment.show(fragmentManager,TAG_DATETIME_FRAGMENT);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                // Replace whatever is in the fragment_container view with this fragment,
//                // and add the transaction to the back stack
//
//                transaction.addToBackStack(null);
//
//                // Commit the transaction
//                transaction.commit();

                break;
            case R.id.EndLayout:
                System.out.println("end layout");
                DialogFragment newFragment1 = new DateTimeDialogFragment();
                newFragment1.show(getFragmentManager(), "timePicker");

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
