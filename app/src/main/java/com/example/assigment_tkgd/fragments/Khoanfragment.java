package com.example.assigment_tkgd.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment_tkgd.R;
import com.example.assigment_tkgd.adapter.KhoanAdapter;
import com.example.assigment_tkgd.dao.KhoanDAO;
import com.example.assigment_tkgd.dao.LoaiDAO;
import com.example.assigment_tkgd.models.Khoan;
import com.example.assigment_tkgd.models.LoaiThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Khoanfragment extends Fragment {

    private int trangThai;
    private RecyclerView recyclerView;
    private FloatingActionButton floatAdd;
    private KhoanDAO khoanDAO;
    private String idLoai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoan, container, false);

        recyclerView = view.findViewById(R.id.recycleKhoan);
        floatAdd = view.findViewById(R.id.floatAdd);

        Bundle bundle = getArguments();
        trangThai = bundle.getInt("trangThai");

        khoanDAO = new KhoanDAO(getContext());

        getDS();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;

    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_khoan, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        EditText edtTenKhoan = view.findViewById(R.id.edtTenKhoan);
        EditText edtTien = view.findViewById(R.id.edtTien);
        EditText edtNgay = view.findViewById(R.id.edtNgay);
        Spinner spnLoai = view.findViewById(R.id.spnLoai);
        Button btnThemSua = view.findViewById(R.id.btnThemSua);
        Button btnHuy = view.findViewById(R.id.btnHuy);
        TextView txtTieuDe = view.findViewById(R.id.txtTieuDe);

        txtTieuDe.setText("Thêm Khoản Mới");
        btnThemSua.setText("Thêm");

        edtNgay.setFocusable(false);
        edtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDilog(edtNgay);
            }
        });

        spnLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String , Object> hashMap = (HashMap<String, Object>) spnLoai.getSelectedItem();
                idLoai = String.valueOf(hashMap.get("MALOAI"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btnThemSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenKhoan = edtTenKhoan.getText().toString();
                int tien = Integer.parseInt(edtTien.getText().toString());
                String ngay = edtNgay.getText().toString();
                int id = Integer.parseInt(idLoai); // id loai kieeur int
                Khoan khoan = new Khoan(tenKhoan, tien, ngay, id);
                boolean check = khoanDAO.themKhoan(khoan);
                if (check){
                    Toast.makeText(getContext(),"Thêm Thành Công",Toast.LENGTH_LONG).show();
                    getDS();
                }else {
                    Toast.makeText(getContext(),"Thất bại",Toast.LENGTH_LONG).show();
                }
                alertDialog.dismiss();
            }
        });


        LayDSSpiner(spnLoai);
    }

    private void showDatePickerDilog(EditText edtNgay) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String thang = "";

                        if (i1 < 9) {
                            thang = "0" + (i1 + 1);
                        } else {
                            thang = String.valueOf((i1 + 1));
                        }
                        String ngay = "";
                        if (i2 < 10) {
                            ngay = "0" + i2;
                        } else {
                            ngay = String.valueOf(i2);
                        }
                        edtNgay.setText(ngay + "/" + thang + "/" + i);

                    }
                }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();

    }

    private void LayDSSpiner(Spinner spnLoai) {
        LoaiDAO loaiDAO = new LoaiDAO(getContext());
        ArrayList<LoaiThu> list = loaiDAO.LayDSLoai(trangThai);
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (LoaiThu loai : list) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("MALOAI", loai.getIdLoai());
            hashMap.put("TEN", loai.getTenLoai());
            listHM.add(hashMap);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"TEN"},
                new int[]{android.R.id.text1}
        );
        spnLoai.setAdapter(simpleAdapter);
    }


    private void getDS() {
        ArrayList<Khoan> list = khoanDAO.layDSKhoan(trangThai);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        KhoanAdapter adapter = new KhoanAdapter(getContext(), list, khoanDAO,trangThai);
        recyclerView.setAdapter(adapter);
    }
}
