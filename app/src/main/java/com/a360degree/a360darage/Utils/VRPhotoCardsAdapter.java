package com.a360degree.a360darage.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.a360degree.a360darage.App.AppSingletonRequestQueue;
import com.a360degree.a360darage.Models.VRPhotoCard;
import com.a360degree.a360darage.R;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.asha.vrlib.MDVRLibrary;
import com.asha.vrlib.model.MDHitEvent;
import com.asha.vrlib.texture.MD360BitmapTexture;
import com.google.android.apps.muzei.render.GLTextureView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;


public class VRPhotoCardsAdapter extends RecyclerView.Adapter<VRPhotoCardsAdapter.VRPhotoCardViewHolder> {

    private List<VRPhotoCard> mVRPhotoCardList;
    private Context mContext;//must be application context for instantiate AppSingletonRequestQueue
    private ImageLoader mImageLoader;
    private VRLibManager mVRManager;

    public VRPhotoCardsAdapter(Context context, List<VRPhotoCard> vrPhotoCards, VRLibManager vrManager) {
        this.mContext = context;
        this.mVRPhotoCardList = vrPhotoCards;
        mImageLoader = AppSingletonRequestQueue.getInstance(mContext).getImageLoader();
        mVRManager = vrManager;
    }

    @NonNull
    @Override
    public VRPhotoCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vrpic_mode_largepic, parent, false);
        return new VRPhotoCardViewHolder(view, mContext, mVRManager);
    }

    @Override
    public void onBindViewHolder(@NonNull VRPhotoCardViewHolder holder, int position) {
        //get element from DataSet at this position and replace the contents of the view with that element
        VRPhotoCard vrPhotoCard = mVRPhotoCardList.get(position);

        holder.mImgChannelLogo.setImageUrl(vrPhotoCard.getLogoUrl(), mImageLoader);

        holder.mTxtChannelName.setText(vrPhotoCard.getChannelName());

        if (vrPhotoCard.isFollowed()) {
            holder.mBtnUnFollow.setVisibility(View.VISIBLE);
            holder.mBtnFollow.setVisibility(View.GONE);
        } else {
            holder.mBtnFollow.setVisibility(View.VISIBLE);
            holder.mBtnUnFollow.setVisibility(View.GONE);
        }

        if (vrPhotoCard.isAd()) {
            holder.mBtnAd.setVisibility(View.VISIBLE);
        } else {
            holder.mBtnAd.setVisibility(View.GONE);
        }

        holder.mPhotoUri = Uri.parse(vrPhotoCard.getPicUrl());

        holder.mTxtPicTitle.setText(vrPhotoCard.getTitle());

        if (vrPhotoCard.isLiked()) {
//            holder.mImgLike
        } else {

        }

        holder.mTxtTime.setText(vrPhotoCard.getTime());
        holder.mTxtLikeCount.setText(String.valueOf(vrPhotoCard.getLikeCount()));
        holder.mTxtViewCount.setText(String.valueOf(vrPhotoCard.getViewCount()));

        //vrPhotoCardViewHolder.mLayoutItem
    }

    @Override
    public int getItemCount() {
        return mVRPhotoCardList.size();
    }

    public static class VRPhotoCardViewHolder extends RecyclerView.ViewHolder implements MDVRLibrary.IBitmapProvider {

        Context context;
        VRLibManager vrManager;

        NetworkImageView mImgChannelLogo;
        TextView mTxtChannelName;
        MaterialButton mBtnFollow;
        MaterialButton mBtnUnFollow;
        MaterialButton mBtnAd;
        AppCompatImageButton mImgbtnMore;
        TextView mTxtPicTitle;
        AppCompatImageView mImgComment;
        AppCompatImageView mImgLike;
        TextView mTxtTime;
        TextView mTxtLikeCount;
        TextView mTxtViewCount;
        ConstraintLayout mLayoutItem;

        GLTextureView mGLTextureViewPic;
        MDVRLibrary mVRLib;
        Uri mPhotoUri;

        private long ts;

        public VRPhotoCardViewHolder(@NonNull View itemView, Context context, VRLibManager vrmManager) {
            super(itemView);
            this.context = context;
            this.vrManager = vrmManager;

            mImgChannelLogo = (NetworkImageView) itemView.findViewById(R.id.img_channel_logo);
            mTxtChannelName = (TextView) itemView.findViewById(R.id.txt_channel_name);
            mBtnFollow = (MaterialButton) itemView.findViewById(R.id.btn_follow);
            mBtnUnFollow = (MaterialButton) itemView.findViewById(R.id.btn_unfollow);
            mBtnAd = (MaterialButton) itemView.findViewById(R.id.btn_ad);
            mImgbtnMore = (AppCompatImageButton) itemView.findViewById(R.id.btn_more);
            mTxtPicTitle = (TextView) itemView.findViewById(R.id.txt_pic_title);
            mImgComment = (AppCompatImageView) itemView.findViewById(R.id.img_comment);
            mImgLike = (AppCompatImageView) itemView.findViewById(R.id.img_like);
            mTxtTime = (TextView) itemView.findViewById(R.id.txt_time);
            mTxtLikeCount = (TextView) itemView.findViewById(R.id.txt_like_count);
            mTxtViewCount = (TextView) itemView.findViewById(R.id.txt_view_count);
            mLayoutItem = (ConstraintLayout) itemView.findViewById(R.id.layout_item);
            mGLTextureViewPic = (GLTextureView) itemView.findViewById(R.id.texture_view_pic);
            ensureVRLib();

            //set listeners 
            mImgChannelLogo.setOnClickListener(goToChannelOnClickListener);
            mTxtChannelName.setOnClickListener(goToChannelOnClickListener);

            mBtnFollow.setOnClickListener(changeFollowStateOnClickListener);
            mBtnUnFollow.setOnClickListener(changeFollowStateOnClickListener);

            mImgbtnMore.setOnClickListener(showMenuOnClickListener);

            mTxtPicTitle.setOnClickListener(goToPhotoPageOnClickListener);
            mGLTextureViewPic.setOnClickListener(goToPhotoPageOnClickListener);

            mImgComment.setOnClickListener(goToCommentPageOnClickListener);

            mImgLike.setOnClickListener(setLikeOnClickListener);

        }

        @Override
        public void onProvideBitmap(final MD360BitmapTexture.Callback callback) {

            Picasso.get().load(mPhotoUri).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    mVRLib.onTextureResize(bitmap.getWidth(), bitmap.getHeight());
                    callback.texture(bitmap);
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
        }

        public void bind() {
            ensureVRLib();
            mVRLib.notifyPlayerChanged();
        }

        private void ensureVRLib() {
            if (mVRLib == null) {
                mVRLib = vrManager.create(this, mGLTextureViewPic);
                mVRLib.setEyePickChangedListener(new MDVRLibrary.IEyePickListener2() {
                    @Override
                    public void onHotspotHit(MDHitEvent hitEvent) {
                        long delta = System.currentTimeMillis();
                        if (delta < 500) {
                            return;
                        }
//                        ts = System.currentTimeMillis();
                    }
                });
            }
        }

        View.OnClickListener goToChannelOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        View.OnClickListener changeFollowStateOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        View.OnClickListener showMenuOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), v);
                popup.getMenuInflater().inflate(R.menu.context_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(onMenuItemClickListener);
                popup.show();
            }
        };

        PopupMenu.OnMenuItemClickListener onMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_add_to_collection:
                        Toast.makeText(context, "1", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.action_download:
                        //Do stuff
                        Toast.makeText(context, "2", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.action_share:
                        //Do stuff
                        Toast.makeText(context, "3", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.action_report:
                        //Do stuff
                        Toast.makeText(context, "4", Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        };

        View.OnClickListener goToPhotoPageOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        View.OnClickListener goToCommentPageOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        View.OnClickListener setLikeOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

    }
}
