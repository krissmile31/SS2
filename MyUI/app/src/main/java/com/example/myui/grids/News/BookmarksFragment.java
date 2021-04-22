package com.example.myui.grids.News;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myui.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookmarksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookmarksFragment extends Fragment {

    public BookmarksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookmarks, container, false);
        view.findViewById(R.id.baomoi).setOnClickListener(this::onClick);
        view.findViewById(R.id.vnexpress).setOnClickListener(this::onClick);
//        view.findViewById(R.id.medium).setOnClickListener(this);
//        view.findViewById(R.id.vnexpress).setOnClickListener(this);
//        view.findViewById(R.id.shopee).setOnClickListener(this);
        return view;
    }

    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        Fragment fragment = new WebViewFragment();
        Bundle args = new Bundle();

        switch (v.getId()) {

            case R.id.baomoi:
                Toast.makeText(getActivity(), "Navigate to Báo Mới...", Toast.LENGTH_SHORT).show();
                args.putString("URL", "https://baomoi.com/");
                fragment.setArguments(args);
                manager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack("Bao Moi")
                        .commit();
                break;

            case R.id.vnexpress:
                Toast.makeText(getActivity(), "Navigate to VnExpress...", Toast.LENGTH_SHORT).show();
                args.putString("URL", "https://vnexpress.net/");
                fragment.setArguments(args);
                manager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack("VnExpress")
                        .commit();
                break;

//            case R.id.kenh14:
//                Toast.makeText(getActivity(), "Navigate to Kenh14...", Toast.LENGTH_SHORT).show();
//                args.putString("URL", "https://kenh14.vn/");
//                fragment.setArguments(args);
//                manager.beginTransaction()
//                        .replace(R.id.fragmentContainer, fragment)
//                        .addToBackStack("Kenh14")
//                        .commit();
//                break;

//            case R.id.shopee:
//                Toast.makeText(getActivity(), "Navigate to Shopee...", Toast.LENGTH_SHORT).show();
//                args.putString("URL", "https://shopee.vn/");
//                fragment.setArguments(args);
//                manager.beginTransaction()
//                        .replace(R.id.fragmentContainer, fragment)
//                        .addToBackStack("Shopee")
//                        .commit();
//                break;

        }
    }
}