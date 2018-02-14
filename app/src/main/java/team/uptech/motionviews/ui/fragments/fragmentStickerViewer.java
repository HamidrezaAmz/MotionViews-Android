package team.uptech.motionviews.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import team.uptech.motionviews.R;
import team.uptech.motionviews.interfaces.StickerViewCallBack;
import team.uptech.motionviews.ui.adapter.StickerRecyclerViewAdapter;

/**
 * Created by Reza Amozadeh on 2/6/2018.
 */

public class fragmentStickerViewer extends Fragment implements StickerViewCallBack {

    Context context;
    ArrayList<String> listStickers;
    RecyclerView stickers_recycler_view;
    StickerRecyclerViewAdapter adapter;
    int numberOfColumns = 4;
    String folderPrefix = "";
    StickerViewCallBack stickerViewCallBack;

    public fragmentStickerViewer() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public fragmentStickerViewer(Context context, ArrayList<String> listStickers, String folderPrefix, StickerViewCallBack stickerViewCallBack) {
        this.context = context;
        this.listStickers = listStickers;
        this.folderPrefix = folderPrefix;
        this.stickerViewCallBack = stickerViewCallBack;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sticker_viewer, container, false);
        stickers_recycler_view = (RecyclerView) view.findViewById(R.id.stickers_recycler_view);
        stickers_recycler_view.setLayoutManager(new GridLayoutManager(context, numberOfColumns));
        adapter = new StickerRecyclerViewAdapter(context, listStickers, folderPrefix, this);
        stickers_recycler_view.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStickerSelected(String stickerPath) {
        stickerViewCallBack.onStickerSelected(stickerPath);
    }
}

