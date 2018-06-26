package com.aatk.pmanager.db;

import android.content.Context;
import android.util.AttributeSet;

public class CarAdder extends android.support.v7.widget.AppCompatButton{
    private CarAdderListener carAdderListener;

    public CarAdder(Context context) {
        super(context);
    }

    public CarAdder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarAdder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface CarAdderListener{
        public void buttonClicked();
    }

    public void setCarAdderListener(CarAdderListener carAdderListener){
        this.carAdderListener = carAdderListener;
    }
}
