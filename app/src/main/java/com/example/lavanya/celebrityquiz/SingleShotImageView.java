package com.example.lavanya.celebrityquiz;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by lavanya on 2/7/17.
 */

public class SingleShotImageView extends ImageView {
    public SingleShotImageView(Context context) {
        super(context);
    }

    public SingleShotImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SingleShotImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);
        setBackgroundDrawable(null);
        setImageBitmap(null);
        setImageResource(0);
        System.gc();
    }

}
