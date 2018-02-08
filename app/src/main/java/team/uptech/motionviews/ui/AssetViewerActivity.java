package team.uptech.motionviews.ui;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flipboard.bottomsheet.BottomSheetLayout;

import team.uptech.motionviews.R;
import team.uptech.motionviews.ui.fragments.StickerBottomSheetDialogFragment;
import team.uptech.motionviews.ui.fragments.fragmentAssetsViewer;

public class AssetViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_viewer);
        setFragment(new fragmentAssetsViewer(this));

        findViewById(R.id.open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new fragmentAssetsViewer(AssetViewerActivity.this));
            }
        });

        BottomSheetLayout bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);

    }

    protected void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    private void showStickerBottomSheet() {
        BottomSheetDialogFragment bottomSheetDialogFragment = new StickerBottomSheetDialogFragment();
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
    }
}
