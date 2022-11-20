package com.example.assigment_tkgd.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assigment_tkgd.R;
import com.example.assigment_tkgd.adapter.LoaiThuAdapter;

import com.example.assigment_tkgd.dao.KhoanThuChiDao;

import com.example.assigment_tkgd.models.LoaiThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaiThufragment extends Fragment {

    ListView listViewLoaiThu;
    FloatingActionButton floatAdd;
    LoaiThuAdapter adapter;
    ArrayList<LoaiThu> list;
    KhoanThuChiDao khoanThuChiDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loaithu_fragment,container,false);

        listViewLoaiThu = view.findViewById(R.id.listViewLoaiThu);
        floatAdd = view.findViewById(R.id.floatAdd);

        khoanThuChiDao = new KhoanThuChiDao(getContext());
        list = khoanThuChiDao.getDSLoai("thu");

        adapter = new LoaiThuAdapter(list, getContext());
        listViewLoaiThu.setAdapter(adapter);
        return view;
    }
}
