package game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.bcgtgjyb.fladdybird.R;
import com.bcgtgjyb.fladdybird.Tool;

/**
 * Created by bigwen on 2016/1/18.
 */
public class Pipe {

    private Context mContext;
    private int[] pass=new int[2];
    private int width[] = new int[2];
    private String TAG = Pipe.class.getName();
    public int ww = 0;
    public Pipe(Context context) {
        mContext = context;
    }

    public void draw(Canvas canvas,int width,int height){
        Log.i(TAG, "draw "+height);
        ww = width;
        int  topPipeHeight = canvas.getHeight()/2-Tool.getInstance().pipeCenterHeight/2;
        Bitmap bitmap = PhotoRes.getBitmap(mContext, R.drawable.g2, width+Tool.getInstance().pipeWidth, topPipeHeight);
        canvas.drawBitmap(bitmap,null,new Rect(width,0,width+Tool.getInstance().pipeWidth,topPipeHeight+height),null);

        int  bottomPipeHeight = canvas.getHeight()/2- Tool.getInstance().pipeCenterHeight/2;
        Bitmap bitmap1 = PhotoRes.getBitmap(mContext, R.drawable.g1, width+Tool.getInstance().pipeWidth, bottomPipeHeight);
        canvas.drawBitmap(bitmap1,null,new Rect(width,canvas.getHeight()-bottomPipeHeight+height,width+Tool.getInstance().pipeWidth,canvas.getHeight()),null);

        pass[0]=topPipeHeight+height;
        pass[1]=topPipeHeight+Tool.getInstance().pipeCenterHeight+height;
        this.width[0] = width;
        this.width[1] = width+Tool.getInstance().pipeWidth;
    }

    public int[] getPass() {
        return pass;
    }

    public int[] getWidth() {
        return width;
    }
}
