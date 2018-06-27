package com.aatk.pmanager.gallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.aatk.pmanager.R;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.car_01, R.drawable.car_01,
            R.drawable.car_02, R.drawable.car_04,
            R.drawable.car_04, R.drawable.car_03,
            R.drawable.car_01, R.drawable.car_02,
            R.drawable.car_04, R.drawable.car_02,
            R.drawable.car_03, R.drawable.car_01,
            R.drawable.car_02, R.drawable.car_04,
            R.drawable.car_04, R.drawable.car_03,
            R.drawable.car_01, R.drawable.car_03,
            R.drawable.car_03, R.drawable.car_01,
            R.drawable.car_02, R.drawable.car_02
    };
}
