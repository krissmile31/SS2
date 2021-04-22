package com.example.myui.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.myui.R;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }
    public int[] images = {R.drawable.welcome, R.drawable.urgency, R.drawable.world, R.drawable.start};
    public String[] headings = {"Welcome", "  Let's", "Explore", ""};
    public String[] des = {"to my application", "manage your time effective", "more of what you love", ""};

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.welcome, container, false);
        ImageView image = view.findViewById(R.id.image);
        TextView heading = view.findViewById(R.id.heading);
        TextView desc = view.findViewById(R.id.desc);
        Glide.with(context).load(images[position]).into(image);
        heading.setText(headings[position]);
        desc.setText(des[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }


}
