package com.pingan.mainframe;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity  {

    Fragment fragmentHome;
    Fragment fragmentHeath;
    Fragment fragmentRecommend;
    Fragment fragmentMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    public void onClickBottombar(View view) {
        int id = view.getId();
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        switch (id){
            case R.id.tv_home:
                if(fragmentHome == null){
                    fragmentHome = new HomeFragment();
                }
                fragmentTransaction.replace(R.id.fragment_container, fragmentHome);
                break;
            case R.id.tv_heath:
                if(fragmentHeath == null){
                    fragmentHeath = new HeathFragment();
                }
                fragmentTransaction.replace(R.id.fragment_container, fragmentHeath);
                break;
            case R.id.tv_plus:
                showPopupWindow(view);
                break;
            case R.id.tv_recommend:
                if(fragmentRecommend == null){
                    fragmentRecommend = new RecommendFragment();
                }
                fragmentTransaction.replace(R.id.fragment_container, fragmentRecommend);
                break;
            case R.id.tv_me:
                if(fragmentMe == null){
                    fragmentMe = new MeFragment();
                }
                fragmentTransaction.replace(R.id.fragment_container, fragmentMe);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
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

    PopupWindow popWindow;
    private void showPopupWindow(View parent) {

        if (popWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.popwindow_plus, null);
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            popWindow = new PopupWindow(view, dm.widthPixels, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.showAsDropDown(parent, 0,0);

        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });

        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                popWindow.dismiss();
                return false;
            }
        });
    }
}
