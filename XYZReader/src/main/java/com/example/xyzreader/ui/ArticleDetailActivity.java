package com.example.xyzreader.ui;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;

import com.example.xyzreader.R;
import com.example.xyzreader.data.Article;
import com.example.xyzreader.data.ArticleViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a single Article detail screen, letting you swipe between articles.
 */
public class ArticleDetailActivity extends AppCompatActivity {

    private static final String TAG = "ArticleDetailActivity";

    private static final String ARTICLE_ID = "article_id";
    private long mStartId;
    private ArticleViewModel viewModel;
    private List<Article> allArticles;
    private int mSelectedItemId;
    private int mSelectedItemUpButtonFloor = Integer.MAX_VALUE;
    private int mTopInset;
    private MyPagerAdapter mPagerAdapter;
    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.up_container)
    View mUpButtonContainer;
    @BindView(R.id.action_up)
    View mUpButton;
    @Override
    @TargetApi(20)
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        setContentView(R.layout.activity_article_detail);
        ButterKnife.bind(this);

        mPagerAdapter = new MyPagerAdapter(getFragmentManager());
        mStartId = getIntent().getExtras().getLong(ARTICLE_ID);
        mPager.setAdapter(mPagerAdapter);
        mPager.setOffscreenPageLimit(0);
        Log.d(TAG, "onCreate: start id " + mStartId);
        viewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        viewModel.getAllArticles().observe(this, new Observer<List<Article>>() {

            @Override
            public void onChanged(@Nullable List<Article> articles) {
                allArticles = articles;
                mPagerAdapter.setArticles(articles);
            }

        });

        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {

                super.onPageScrollStateChanged(state);
                mUpButton.animate()
                        .alpha((state == ViewPager.SCROLL_STATE_IDLE) ? 1f : 0f)
                        .setDuration(300);
            }

            @Override
            public void onPageSelected(int position) {
            }
        });

        mPager.setPageMargin((int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()));
        mPager.setPageMarginDrawable(new ColorDrawable(0x22000000));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mUpButtonContainer.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                @Override
                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    view.onApplyWindowInsets(windowInsets);
                    mTopInset = windowInsets.getSystemWindowInsetTop();
                    mUpButtonContainer.setTranslationY(mTopInset);
                    return windowInsets;
                }
            });
        }

        if (savedInstanceState == null) {
            mSelectedItemId = 0;
        }
    }

    private void updateUpButtonPosition() {
        int upButtonNormalBottom = mTopInset + mUpButton.getHeight();
        mUpButton.setTranslationY(Math.min(mSelectedItemUpButtonFloor - upButtonNormalBottom, 0));
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        private List<Article> articles;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public int getCount() {
            return (articles != null) ? articles.size() : 0;
        }

        void setArticles(List<Article> articles) {
            this.articles = articles;
            notifyDataSetChanged();

        }

        @Override
        public Fragment getItem(int position) {
            Log.d(TAG, "getItem ID: " + position);
            return ArticleDetailFragment.newInstance(articles.get(position).getId());
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            ArticleDetailFragment fragment = (ArticleDetailFragment) object;
            if (fragment != null) {
                mSelectedItemUpButtonFloor = fragment.getUpButtonFloor();
                updateUpButtonPosition();
            }
        }


    }
}
