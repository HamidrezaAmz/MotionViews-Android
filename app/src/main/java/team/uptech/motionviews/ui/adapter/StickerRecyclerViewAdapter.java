package team.uptech.motionviews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import team.uptech.motionviews.R;
import team.uptech.motionviews.interfaces.StickerViewCallBack;
import team.uptech.motionviews.utils.Tools;

/**
 * Created by Reza Amozadeh on 2/7/2018.
 */

public class StickerRecyclerViewAdapter extends RecyclerView.Adapter<StickerRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> listStickers;
    private Context context;
    private LayoutInflater mInflater;
    private StickerViewCallBack stickerViewCallBack;
    private String folderPrefix;

    public StickerRecyclerViewAdapter(Context context, ArrayList<String> listStickers, String folderPrefix, StickerViewCallBack stickerViewCallBack) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.listStickers = listStickers;
        this.folderPrefix = folderPrefix;
        this.stickerViewCallBack = stickerViewCallBack;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_sticker_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tools.loadImageFromAssetsIntoImageview(context,
                new StringBuilder().append(folderPrefix).append(listStickers.get(position)).toString(),
                holder.SquareImageView);
    }

    @Override
    public int getItemCount() {
        return listStickers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView SquareImageView;

        ViewHolder(View itemView) {
            super(itemView);
            SquareImageView = (ImageView) itemView.findViewById(R.id.SquareImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (stickerViewCallBack != null)
                stickerViewCallBack.onStickerSelected(folderPrefix + listStickers.get(getAdapterPosition()));
        }
    }

}