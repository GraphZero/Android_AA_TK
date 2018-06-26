package com.aatk.pmanager.db;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MyFragment extends Fragment {
    private Button button;

    private Callback callback;

    public interface Callback{
        void onButtonClicked();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        callback = (Callback)activity;
    }

    @Override
    public void onDetach(){
        super.onDetach();
        callback = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onButtonClicked();
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
