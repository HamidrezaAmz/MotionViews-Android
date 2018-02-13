package team.uptech.motionviews.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import team.uptech.motionviews.R;
import team.uptech.motionviews.enums.enumHeight;
import team.uptech.motionviews.ui.fragments.fragmentAssetsViewer;
import team.uptech.motionviews.utils.Tools;

public class AssetViewerActivity extends AppCompatActivity {

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_viewer);
        frameLayout = findViewById(R.id.container);

        setFragment(new fragmentAssetsViewer(this));

        setHeight(enumHeight.HIGH);
    }

    protected void setFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    public void setHeight(enumHeight height) {
        switch (height) {
            case HIDE:
                frameLayout.getLayoutParams().height = (Tools.getScreenHeight());
                break;
            case LOW:
                frameLayout.getLayoutParams().height = (Tools.getScreenHeight() / 4);
                break;
            case MEDIUM:
                frameLayout.getLayoutParams().height = (Tools.getScreenHeight() / 2);
                break;
            case HIGH:
                frameLayout.getLayoutParams().height = ((Tools.getScreenHeight() * 2) / 3);
                break;
        }
    }

}
