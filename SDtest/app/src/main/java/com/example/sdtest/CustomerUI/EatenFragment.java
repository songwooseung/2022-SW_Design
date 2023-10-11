package com.example.sdtest.CustomerUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.sdtest.R;
import com.example.sdtest.User.Customer.Customer;

public class EatenFragment extends Fragment {
    private GridView gridView;
    private EatenAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_eaten, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView = view.findViewById(R.id.eaten_gridview);
        adapter = new EatenAdapter();
        adapter.setItems(Customer.getInstance().getEatenMenu());
        gridView.setAdapter(adapter);
    }
}
