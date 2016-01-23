package com.bcgtgjyb.fladdybird;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import game.GameSurface;

public class MainActivity extends AppCompatActivity {

    private LinearLayout addView;
    private TextView pause;
    private GameSurface view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView  = (LinearLayout)findViewById(R.id.addView);
        view = new GameSurface(this);
        addView.addView(view);
        pause = (TextView)findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (view != null) {
                    if (view.getRunning()) {
                        view.setRunning(false);
                    } else {
                        view.setRunning(true);
                    }
                }
            }
        });
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        Tool.getInstance().setScreenHeight(height);
        Tool.getInstance().setScreenWidth(width);
    }

    @Override
    protected void onResume() {
        if(view!=null) {
            view.setResume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if(view!=null) {
            view.setRunning(false);
        }
        super.onPause();
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
}
