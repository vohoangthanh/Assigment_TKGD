package com.example.assigment_tkgd.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment_tkgd.R;
import com.example.assigment_tkgd.dao.KhoanDAO;
import com.example.assigment_tkgd.dao.LoaiDAO;
import com.example.assigment_tkgd.models.Khoan;
import com.example.assigment_tkgd.models.LoaiThu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class KhoanAdapter extends RecyclerView.Adapter<KhoanAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Khoan> list;
    private KhoanDAO khoanDAO;
    private String idLoai;
    private int trangThai;

    public KhoanAdapter(Context context, ArrayList<Khoan> list, KhoanDAO khoanDAO, int trangThai) {
        this.context = context;
        this.list = list;
        this.khoanDAO = khoanDAO;
        this.trangThai = trangThai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khoan, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtIdKhoan.setText(String.valueOf(list.get(position).getIdKhoan()));
        holder.txtTenKhoan.setText(list.get(position).getTenKhoan());
        holder.txtNgay.setText(list.get(position).getNgay());
        holder.txtTien.setText(String.valueOf(list.get(position).getTien()));
        holder.txtIDLoai.setText(String.valueOf(list.get(position).getIdLoai()));
        holder.txtTenLoai.setText(list.get(position).getTenLoai());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(list.get(holder.getAdapterPosition()));
            }
        });
        holder.ivDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = khoanDAO.xoaKhoan(list.get(holder.getAdapterPosition()).getIdKhoan());
                if (check) {
                    Toast.makeText(context, "Xóa Thành Công ", Toast.LENGTH_LONG).show();
                    getDS();
                }else {
                    Toast.makeText(context, "Thất Bại ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtIdKhoan, txtTenKhoan, txtNgay, txtTien, txtIDLoai, txtTenLoai;
        ImageView ivIcon, ivEdit, ivDele;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIdKhoan = itemView.findViewById(R.id.txtIdKhoan);
            txtTenKhoan = itemView.findViewById(R.id.txtTenKhoan);
            txtTien = itemView.findViewById(R.id.txtTien);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtIDLoai = itemView.findViewById(R.id.txtIDLoai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDele = itemView.findViewById(R.id.ivDele);


        }
    }

    private void showDialog(Khoan khoan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
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

        txtTieuDe.setText("Chỉnh Sữa Khoản Mới");
        btnThemSua.setText("Chỉnh Sữa");

        edtTenKhoan.setText(khoan.getTenKhoan());
        edtTien.setText(String.valueOf(khoan.getTien()));
        edtNgay.setText(khoan.getNgay());

        LayDSSpiner(spnLoai, khoan.getIdLoai());

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
                HashMap<String, Object> hashMap = (HashMap<String, Object>) spnLoai.getSelectedItem();
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
                Khoan Capnhatkhoan = new Khoan(khoan.getIdKhoan(), tenKhoan, tien, ngay, id);
                boolean check = khoanDAO.capNhatKhoan(Capnhatkhoan);
                if (check) {
                    Toast.makeText(context, "Chỉnh Sữa Thành Công", Toast.LENGTH_LONG).show();
                    getDS();
                } else {
                    Toast.makeText(context, "Thất bại", Toast.LENGTH_LONG).show();
                }
                alertDialog.dismiss();
            }
        });


    }

    private void showDatePickerDilog(EditText edtNgay) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
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

    private void LayDSSpiner(Spinner spnLoai, int idLoai) {
        int vitri = -1;
        int position = 0;
        LoaiDAO loaiDAO = new LoaiDAO(context);
        ArrayList<LoaiThu> list = loaiDAO.LayDSLoai(trangThai);
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (LoaiThu loai : list) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("MALOAI", loai.getIdLoai());
            hashMap.put("TEN", loai.getTenLoai());
            listHM.add(hashMap);
            if (loai.getIdLoai() == idLoai) {
                vitri = position;
            }
            position++;
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"TEN"},
                new int[]{android.R.id.text1}
        );
        spnLoai.setAdapter(simpleAdapter);
        spnLoai.setSelection(vitri);


    }

    private void getDS() {
        list.clear();
        list = khoanDAO.layDSKhoan(trangThai);
        notifyDataSetChanged();
    }
}
