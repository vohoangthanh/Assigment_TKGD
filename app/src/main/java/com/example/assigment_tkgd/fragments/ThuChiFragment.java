package com.example.assigment_tkgd.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.assigment_tkgd.R;
import com.example.assigment_tkgd.adapter.ViewPager2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ThuChiFragment extends Fragment {

    private int trangthai;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_thuchi, container, false);

        Bundle bundle = getArguments();
        trangthai = bundle.getInt("trangthai");
        Toast.makeText(getContext(), "" + trangthai, Toast.LENGTH_LONG).show();

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2 = view.findViewById(R.id.viewPager2);

        ViewPager2Adapter adapter = new ViewPager2Adapter(getActivity(),trangthai);
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    if (trangthai == 0) {
                        tab.setText("Loại Thu");
                    } else {
                        tab.setText("Loại Chi");
                    }
                } else {
                    if (trangthai == 0) {
                        tab.setText("Khoản Thu");
                    } else {
                        tab.setText("Khoản Chi");
                    }
                }
            }
        }).attach();
        return view;
    }
}
