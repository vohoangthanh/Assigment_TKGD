package com.example.assigment_tkgd.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment_tkgd.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoaiFrament extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton floatAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai,container,false);

        recyclerView = view.findViewById(R.id.recycleLoai);
        floatAdd = view.findViewById(R.id.floatAdd);


        return view;
    }
}
