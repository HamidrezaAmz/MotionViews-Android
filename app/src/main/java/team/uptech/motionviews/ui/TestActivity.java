package team.uptech.motionviews.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import team.uptech.motionviews.R;
import team.uptech.motionviews.enums.enumHeight;
import team.uptech.motionviews.interfaces.StickerViewCallBack;
import team.uptech.motionviews.ui.fragments.fragmentAssetsViewer;
import team.uptech.motionviews.utils.Tools;
import team.uptech.motionviews.viewmodel.Layer;
import team.uptech.motionviews.widget.MotionView;
import team.uptech.motionviews.widget.entity.ImageEntity;

public class TestActivity extends AppCompatActivity implements StickerViewCallBack {

    private MotionView motionView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        motionView = (MotionView) findViewById(R.id.main_motion_view);
        frameLayout = findViewById(R.id.container);

        setFragment(new fragmentAssetsViewer(TestActivity.this, this));
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
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        switch (height) {
            case HIDE:
                layoutParams.height = 0;
                break;
            case LOW:
                layoutParams.height = Tools.getScreenHeight() / 4;
                break;
            case MEDIUM:
                layoutParams.height = Tools.getScreenHeight() / 2;
                break;
            case HIGH:
                layoutParams.height = (Tools.getScreenHeight() * 2) / 3;
                break;
        }
        frameLayout.setLayoutParams(layoutParams);
    }

    private void addSticker(final int stickerResId) {

        motionView.post(new Runnable() {
            @Override
            public void run() {
                Layer layer = new Layer();
                Bitmap pica = BitmapFactory.decodeResource(getResources(), stickerResId);

                ImageEntity entity = new ImageEntity(layer, pica, motionView.getWidth(), motionView.getHeight());

                motionView.addEntityAndPosition(entity);
            }
        });
    }

    private void addStickerFromAssets(final String path) {
        motionView.post(new Runnable() {
            @Override
            public void run() {
                Layer layer = new Layer();
                Bitmap pica = Tools.loadBitmapFromAssets(TestActivity.this, path);

                if (pica != null) {
                    ImageEntity entity = new ImageEntity(layer, pica, motionView.getWidth(), motionView.getHeight());
                    motionView.addEntityAndPosition(entity);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.main_done) {
            saveSticker();
        } else if (item.getItemId() == R.id.main_add) {
            setHeight(enumHeight.HIGH);
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveSticker() {
        Bitmap bitmap = motionView.getThumbnailImage();
        // motionView.setVisibility(View.GONE);
    }

    @Override
    public void onStickerSelected(String stickerPath) {
//        Toast.makeText(this, "PATH: " + stickerPath, Toast.LENGTH_SHORT).show();
        addStickerFromAssets(stickerPath);
        setHeight(enumHeight.HIDE);
    }
}
