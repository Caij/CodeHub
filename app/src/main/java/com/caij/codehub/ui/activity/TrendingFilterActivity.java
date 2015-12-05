package com.caij.codehub.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.ui.transitions.FabDialogMorphSetup;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author:  Caij
 * Email:   worldcaij@gmail.com
 * Date:    2015/12/5
 * Description:
 */
public class TrendingFilterActivity extends BaseCodeHubActivity{

    @Bind(R.id.time)
    RadioGroup mSinceRadioGroup;
    @Bind(R.id.languages)
    RadioGroup mLanguageRadioGroup;
    private int mSinceCheckId;
    private int mLanguageCheckId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = findViewById(R.id.dialog);
        ViewCompat.setTransitionName(view, Constant.TRANSIT_PIC);
        FabDialogMorphSetup.setupSharedEelementTransitions(this, view,
                getResources().getDimensionPixelSize(R.dimen.dialog_corners));
        mSinceCheckId = getIntent().getIntExtra(Constant.TRENDING_REPOSITORY_CHECK_SINCE_ID, R.id.daily);
        mLanguageCheckId = getIntent().getIntExtra(Constant.TRENDING_REPOSITORY_CHECK_LANGUAGE_ID, R.id.all);
        ((RadioButton) mSinceRadioGroup.findViewById(mSinceCheckId)).setChecked(true);
        ((RadioButton) mLanguageRadioGroup.findViewById(mLanguageCheckId)).setChecked(true);
        TextView tv1 = (TextView) findViewById(android.R.id.button1);
        tv1.setText("OK");
        TextView tv2 = (TextView) findViewById(android.R.id.button2);
        tv2.setText("CANCEL");
    }

    @Override
    protected int getAttachLayoutId() {
        return R.layout.activity_trending_filter;
    }

    @OnClick(android.R.id.content)
    public void dismiss(View view) {
        onBackPressed();
    }

    @OnClick(android.R.id.button1)
    public void onOkClick(View view) {
        int sinceCheckId = mSinceRadioGroup.getCheckedRadioButtonId();
        int languageCheckId = mLanguageRadioGroup.getCheckedRadioButtonId();
        if (mSinceCheckId != sinceCheckId || mLanguageCheckId != languageCheckId) {
            Intent intent = new Intent();
            intent.putExtra(Constant.TRENDING_REPOSITORY_CHECK_SINCE_ID, sinceCheckId);
            intent.putExtra(Constant.TRENDING_REPOSITORY_CHECK_LANGUAGE_ID, languageCheckId);
            setResult(RESULT_OK, intent);
        }
        dismiss(view);
    }

    @OnClick(android.R.id.button2)
    public void onCancelClick(View view) {
        dismiss(view);
    }
}
