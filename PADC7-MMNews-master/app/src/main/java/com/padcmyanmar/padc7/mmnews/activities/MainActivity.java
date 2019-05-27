package com.padcmyanmar.padc7.mmnews.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.adapters.NewsAdapter;
import com.padcmyanmar.padc7.mmnews.components.SmartRecyclerView;
import com.padcmyanmar.padc7.mmnews.components.SmartScrollListener;
import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;
import com.padcmyanmar.padc7.mmnews.mvp.presenters.INewsListPresenter;
import com.padcmyanmar.padc7.mmnews.mvp.presenters.NewsListPresenter;
import com.padcmyanmar.padc7.mmnews.mvp.views.NewsListView;
import com.padcmyanmar.padc7.mmnews.utils.Constants;
import com.padcmyanmar.padc7.mmnews.views.pods.EmptyViewPod;
import com.padcmyanmar.padc7.mmnews.views.pods.LoginUserViewPod;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends BaseActivity implements NewsListView {

    private BottomSheetBehavior<NestedScrollView> mBottomSheetBehavior;

    DrawerLayout mDrawerLayout;

    NavigationView mNavigationView;

    Toolbar mToolbar;

    SmartRecyclerView rvNews;

    EmptyViewPod vpEmpty;

    SwipeRefreshLayout swipeRefreshLayout;

    private NewsAdapter mNewsAdapter;

    private SmartScrollListener mSmartScrollListener;
    private LoginUserViewPod vpLoginUser;

    private INewsListPresenter mPresenter;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout=findViewById(R.id.drawer_layout);
        mNavigationView=findViewById(R.id.navigation_view);
        mToolbar=findViewById(R.id.toolbar_main);
        rvNews=findViewById(R.id.rv_news);
        vpEmpty=findViewById(R.id.vp_empty);
        swipeRefreshLayout=findViewById(R.id.swipe_refresh_layout);

        mToolbar.setTitle(R.string.title_latest_news);

        setSupportActionBar(mToolbar);
        vpLoginUser = (LoginUserViewPod) mNavigationView.getHeaderView(0);

        mPresenter= ViewModelProviders.of(this).get(NewsListPresenter.class);
        ((NewsListPresenter) mPresenter).initPresenter(this);
        mPresenter.onUIReady(this);

        mNewsAdapter = new NewsAdapter(mPresenter);
        rvNews.setAdapter(mNewsAdapter);

        mNavigationView = findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_lastest_news:
                        Toast.makeText(getApplicationContext(), getString(R.string.title_latest_news), Toast.LENGTH_SHORT).show();
                        mToolbar.setTitle(R.string.title_latest_news);
                        break;
                    case R.id.menu_news_just_for_you:
                        Toast.makeText(getApplicationContext(), getString(R.string.title_news_just_for_you), Toast.LENGTH_SHORT).show();
                        mToolbar.setTitle(R.string.title_news_just_for_you);
                        break;
                    case R.id.menu_favorite_news:
                        Toast.makeText(getApplicationContext(), getString(R.string.title_favorite_news), Toast.LENGTH_SHORT).show();
                        mToolbar.setTitle(R.string.title_favorite_news);
                        break;
                }
                for (int index = 0; index < mNavigationView.getMenu().size(); index++) {
                    mNavigationView.getMenu().getItem(index).setChecked(false);
                }
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefreshPage(MainActivity.this);
            }
        });

        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReach() {
                Toast.makeText(getApplicationContext(), "onListEndReach", Toast.LENGTH_LONG).show();
                mPresenter.onListEndReach(MainActivity.this
                );
            }
        });

        rvNews.addOnScrollListener(mSmartScrollListener);
        rvNews.setEmptyView(vpEmpty);
        rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL, false));

        NestedScrollView nsvBottomSheet = findViewById(R.id.nsv_bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(nsvBottomSheet);

        mBottomSheetBehavior.setPeekHeight(0);

        vpLoginUser.setDelegate(new LoginUserViewPod.LoginUserViewPodDelegate() {
            @Override
            public void onTapLogout() {
                mPresenter.onTapLogout();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onTapFab(View view) {

        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    private void navigateToLoginScreen() {
        Intent intent = LoginActivity.newIntent(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void displayNewsList(List<? extends NewsVO> newsList) {
        swipeRefreshLayout.setRefreshing(false);
        if (newsList!=null){
            mNewsAdapter.setNewData((List<NewsVO>) newsList);
        }
    }

    @Override
    public void displayMoreNewsList(@NotNull List<? extends NewsVO> newsList) {
        mNewsAdapter.setNewData((List<NewsVO>) newsList);
    }

    @Override
    public void displayFailToLoadData(@NotNull String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayScreenIfUserIsNotLogin() {
        navigateToLoginScreen();
    }

    @Override
    public void displayNewsDetailScreen(@NotNull String newsId) {
        Intent intent = NewsDetailsActivity.Companion.newIntent(getApplicationContext());
        intent.putExtra(Constants.NEWS_ID,newsId);
        startActivity(intent);
    }

    @Override
    public void displayUserInformation(@NotNull LoginUserVO loginUser) {
        vpLoginUser.setData(loginUser);
    }
}
