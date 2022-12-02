package com.example.assigment_tkgd.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assigment_tkgd.R;

public class ThuChiFragment extends Fragment {
    
    private int trangthai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_thuchi,container,false);
        
        Bundle bundle = getArguments();
        trangthai = bundle.getInt("trangthai");
        Toast.makeText(getContext(),""+ trangthai,Toast.LENGTH_LONG).show();


        return view;
    }
}
