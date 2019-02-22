package com.example.intent.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.intent.R;
import com.example.intent.item.GridItem;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class AdapterFragment extends PagerAdapter {


    private Context context;
    private LayoutInflater layoutInflater;

    ArrayList<GridItem> data;
    public AdapterFragment(Context context,ArrayList<GridItem> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (View) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final GridItem gridItem = data.get(position);

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewpager_item_layout, container, false);

        PhotoView photoView = (PhotoView) view.findViewById(R.id.selectImg);

        //photoView.setImageResource(image_resource[position]);
        photoView.setImageResource(gridItem.getImg());

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
