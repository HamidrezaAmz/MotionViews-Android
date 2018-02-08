package team.uptech.motionviews.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import team.uptech.motionviews.R;
import team.uptech.motionviews.ui.adapter.StickerTabsAdapter;

/**
 * Created by Reza Amozadeh on 2/6/2018.
 */

public class fragmentAssetsViewer extends Fragment {

    Context context;
    String assetsFolderName = "sticker";
    String slash = "/";

    public fragmentAssetsViewer() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public fragmentAssetsViewer(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asset_viewer, container, false);

        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        return view;
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        StickerTabsAdapter adapter = new StickerTabsAdapter(getChildFragmentManager());
        readStickersFromAssets(adapter);
        viewPager.setAdapter(adapter);
    }

    private void readStickersFromAssets(StickerTabsAdapter adapter) {

        try {
            String[] folders = context.getAssets().list(assetsFolderName);
            ArrayList<String> listfolders = new ArrayList<>(Arrays.asList(folders));

            for (String folder : listfolders) {
                String[] stickers = context.getAssets().list(assetsFolderName + slash + folder);
                ArrayList<String> listStickers = new ArrayList<>(Arrays.asList(stickers));
                adapter.addFragment(new fragmentStickerViewer(context, listStickers, assetsFolderName + slash + folder + slash), folder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

