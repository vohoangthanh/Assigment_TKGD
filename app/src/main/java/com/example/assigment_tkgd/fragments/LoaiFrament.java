package com.example.assigment_tkgd.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment_tkgd.R;
import com.example.assigment_tkgd.adapter.LoaiAdapter;
import com.example.assigment_tkgd.dao.LoaiDAO;
import com.example.assigment_tkgd.models.LoaiThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaiFrament extends Fragment {

    private RecyclerView recyclerLoai;
    private FloatingActionButton floatAdd;
    private LoaiDAO loaiDAO;
    private int trangThai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai,container,false);

        recyclerLoai = view.findViewById(R.id.recycleLoai);
        floatAdd = view.findViewById(R.id.floatAdd);

        Bundle bundle = getArguments();
        trangThai = bundle.getInt("trangThai");

        loaiDAO = new LoaiDAO(getContext());
        getDS();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDilogAdd();
            }
        });
        return view;
    }

    private void showDilogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_loai,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        EditText edtenLoai = view.findViewById(R.id.edtTenLoai);
        Button btnThem = view.findViewById(R.id.btnThem);
        Button btnHuy = view.findViewById(R.id.btnHuy);
        TextView txtTieuDe = view.findViewById(R.id.txtTieuDe);

        txtTieuDe.setText("THÊM LOẠI");

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = edtenLoai.getText().toString();
                LoaiThu loai = new LoaiThu(data,trangThai);
                boolean check = loaiDAO.inSert(loai);
                if (check){
                    Toast.makeText(getContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
                    getDS();
                }else {
                    Toast.makeText(getContext(),"Thêm thất bại",Toast.LENGTH_LONG).show();
                }
                alertDialog.dismiss();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }

    private void getDS(){
        ArrayList<LoaiThu> list = loaiDAO.LayDSLoai(trangThai);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerLoai.setLayoutManager(linearLayoutManager);
        LoaiAdapter adapter = new LoaiAdapter(getContext(),list,loaiDAO,trangThai);
        recyclerLoai.setAdapter(adapter);
    }
}
