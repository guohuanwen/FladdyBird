package com.bcgtgjyb.fladdybird;

import android.util.Log;

/**
 * Created by bigwen on 2016/1/22.
 */
public class Tool {
    private static Tool tool;
    private String TAG = Tool.class.getName();

    public static Tool getInstance(){
        if(tool==null){
            tool = new Tool();
        }
        return tool;
    }

    private Tool(){

    }
    private int screenWidth;
    private int screenHeight;

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
        pipeHeight = screenHeight*2/5;
        birdHeight = (int)(screenHeight*0.055);
        birdWidth = birdHeight*34/24;
        pipeWidth = (int)(birdWidth*1.3);
        pipeCenterHeight = screenHeight/5;
        Log.i(TAG, "setScreenHeight "+screenHeight+"   pipeHeight"+pipeHeight+"   birdWidth"+birdWidth+"   " +
                "pipeWidth"+pipeWidth+"   pipeCenterHeight"+pipeCenterHeight);
        jumpHeight = screenHeight*50/850;
    }

    public int birdWidth = 0;
    public int birdHeight = 0;
    public int pipeWidth =0;
    public int pipeHeight=0;
    public int pipeCenterHeight=0;
    public int jumpHeight = 0;
    private float dpi = 0;

    public float getDpi() {
        return dpi;
    }

    public void setDpi(float dpi) {
        this.dpi = dpi;
    }
}
