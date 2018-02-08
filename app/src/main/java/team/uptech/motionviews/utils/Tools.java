package team.uptech.motionviews.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;

import java.io.InputStream;

import team.uptech.motionviews.GlideApp;

/**
 * Created by Reza Amozadeh on 2/6/2018.
 */

public class Tools {

    public static Bitmap loadBitmapFromAssets(Context context, String path) {
        InputStream stream = null;
        try {
            stream = context.getAssets().open(path);
            return BitmapFactory.decodeStream(stream);
        } catch (Exception ignored) {
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    public static void loadImageIntoImageview(Context context, String path, ImageView imageView) {
        GlideApp
                .with(context)
                .load(path)
                .centerCrop()
                .into(imageView);
    }

    public static void loadImageFromAssetsIntoImageview(Context context, String path, ImageView imageView) {
        GlideApp
                .with(context)
                .load(Uri.parse("file:///android_asset/" + path))
                .fitCenter()
                .into(imageView);
    }

}
