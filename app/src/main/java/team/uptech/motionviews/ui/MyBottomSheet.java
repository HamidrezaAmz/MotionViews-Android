package team.uptech.motionviews.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import team.uptech.motionviews.R;
import team.uptech.motionviews.enums.enumHeight;
import team.uptech.motionviews.utils.Tools;

/**
 * Created by asus on 2/7/2018.
 */

public class MyBottomSheet extends LinearLayout {

    public MyBottomSheet(@NonNull Context context) {
        super(context);
        initView();
    }

    public MyBottomSheet(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyBottomSheet(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = inflate(getContext(), R.layout.layout_my_bottom_sheet, null);
        addView(view);
        setHeight(enumHeight.HIDE);
    }

    public void setHeight(enumHeight height) {
        switch (height) {
            case HIDE:
                setHeightAnimation(Tools.getScreenHeight());
                break;
            case LOW:
                setHeightAnimation((Tools.getScreenHeight() * 2) / 3);
                break;
            case MEDIUM:
                setHeightAnimation(Tools.getScreenHeight() / 2);
                break;
            case HIGH:
                setHeightAnimation(Tools.getScreenHeight() / 4);
                break;
        }
    }

    private void setHeightAnimation(int height) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(this, "y", height);
        oa.setDuration(200);
        oa.start();
    }
}
