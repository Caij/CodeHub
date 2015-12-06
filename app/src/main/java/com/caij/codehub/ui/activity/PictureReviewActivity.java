package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.caij.codehub.Constant;
import com.caij.codehub.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Caij on 2015/12/4.
 */
public class PictureReviewActivity extends BaseCodeHubActivity{

    @Bind(R.id.imageview)
    ImageView mImageView;

    public static Intent newIntent(Activity activity, String imageUrl) {
        Intent intent = new Intent(activity, PictureReviewActivity.class);
        intent.putExtra(Constant.URL, imageUrl);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        mContentContainer.setFitsSystemWindows(false);
        ButterKnife.bind(this);
        ViewCompat.setTransitionName(mImageView, Constant.TRANSIT_PIC);
        String url = getIntent().getStringExtra(Constant.URL);
        Glide.with(PictureReviewActivity.this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().into(mImageView);
    }

    @Override
    protected int getAttachLayoutId() {
        return R.layout.activity_picture;
    }


    @OnClick(R.id.imageview)
    public void imageViewOnclick(View view) {
        onBackPressed();
    }

}
