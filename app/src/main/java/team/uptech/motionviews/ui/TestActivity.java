package team.uptech.motionviews.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import team.uptech.motionviews.R;
import team.uptech.motionviews.utils.Tools;
import team.uptech.motionviews.viewmodel.Layer;
import team.uptech.motionviews.widget.MotionView;
import team.uptech.motionviews.widget.entity.ImageEntity;

public class TestActivity extends AppCompatActivity {

    private MotionView motionView;
    private ImageView preview;
    private RelativeLayout preview_holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        motionView = (MotionView) findViewById(R.id.main_motion_view);
        preview_holder = (RelativeLayout) findViewById(R.id.preview_holder);
        preview = (ImageView) findViewById(R.id.preview);

        addStickerFromAssets("sticker/heart/15.png");
    }

    private void addSticker(final int stickerResId) {
        // addSticker(R.drawable.pikachu_2);

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
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveSticker() {
        Bitmap bitmap = motionView.getThumbnailImage();
        motionView.setVisibility(View.GONE);
        preview_holder.setVisibility(View.VISIBLE);
        preview.setImageBitmap(bitmap);
    }
}
