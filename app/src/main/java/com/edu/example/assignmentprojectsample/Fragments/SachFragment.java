package com.edu.example.assignmentprojectsample.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.example.assignmentprojectsample.R;


public class SachFragment extends Fragment {


    public SachFragment() {
        // Required empty public constructor
    }


    public static SachFragment newInstance() {
        SachFragment fragment = new SachFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.sach_fragment, container, false);
    }
}