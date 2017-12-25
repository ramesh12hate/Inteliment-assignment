package com.example.rramesh.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rramesh.assignment.R;

/**
 * Created by Ramesh on 12/23/2017.
 */

public class ThreeFragment extends Fragment  implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), getString(R.string.fragment) + " " + getString(R.string.three), Toast.LENGTH_LONG).show();
    }
}
