package com.example.bottombar.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.MiscUtils;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity {
    private BottomBar mBottomBar;
    private TextView mMessageView;
    BottomBarBadge a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMessageView = (TextView) findViewById(R.id.messageView);

        mBottomBar = BottomBar.attach(this, savedInstanceState);

        mBottomBar.noTopOffset();
        mBottomBar.noTabletGoodness();
        mBottomBar.ignoreNightMode();
        mBottomBar.noResizeGoodness();
        mBottomBar.noNavBarGoodness();
        mBottomBar.useFixedMode();
        mBottomBar.setInActiveTabColor(Color.parseColor("#9A9B9D"));
        mBottomBar.setActiveTabColor(Color.parseColor("#AC1A2F"));

        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                mMessageView.setText(getMessage(menuItemId, false));
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                Toast.makeText(getApplicationContext(), getMessage(menuItemId, true), Toast.LENGTH_SHORT).show();
            }
        });

        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.
        mBottomBar.mapColorForTab(0, Color.LTGRAY);
        mBottomBar.mapColorForTab(1, Color.LTGRAY);
        mBottomBar.mapColorForTab(2, Color.LTGRAY);
        mBottomBar.mapColorForTab(3, Color.LTGRAY);
        mBottomBar.mapColorForTab(4, Color.LTGRAY);

        //mBottomBar.setItems();

        a = mBottomBar.makeBadgeForTabAt(1, Color.RED, 10);
        a.setHideIfBadgeCountIsZero(true);
        a.setAutoShowAfterUnSelection(true);
        a.setCount(10);
        //a.hide();
    }


    private String getMessage(int menuItemId, boolean isReselection) {
        String message = "Content for ";

        switch (menuItemId) {
            case R.id.bb_menu_recents:
                message += "recents";
                break;
            case R.id.bb_menu_favorites:
                message += "favorites";
                break;
            case R.id.bb_menu_nearby:
                message += "nearby";
                //a.setCount(0);
                break;
            case R.id.bb_menu_friends:
                message += "friends";
                a.setCount(1);
                a.show();
                break;
            case R.id.bb_menu_food:
                message += "food";
                //a.setCount(500);
                //a.show();
                break;
        }

        if (isReselection) {
            message += " WAS RESELECTED! YAY!";
        }

        return message;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }
}
