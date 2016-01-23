package game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.bcgtgjyb.fladdybird.R;
import com.bcgtgjyb.fladdybird.Tool;

/**
 * Created by bigwen on 2016/1/18.
 */
public class Bird {
    private int height;
    private int CanvasHeight;
    private Context mContext;
    private Rect rect;
    private int birdHeight = -2;

    public Bird(Context context) {
        mContext = context;
    }

    public void draw(Canvas canvas,int height) {
        CanvasHeight = canvas.getHeight();

        int centerWidth = canvas.getWidth()/2 ;
        int centerHeight = height;
        this.height = height;
        Bitmap bitmap = PhotoRes.getBitmap(mContext, R.drawable.b1, Tool.getInstance().birdWidth,Tool.getInstance().birdHeight);

        int birdWidth = bitmap.getWidth();
        int birdHeight = bitmap.getHeight();
        this.birdHeight = birdHeight;
        rect = new Rect(centerWidth - birdWidth / 2, centerHeight - birdHeight / 2,
                centerWidth + birdWidth / 2, centerHeight + birdHeight / 2);
        canvas.drawBitmap(bitmap, null,rect, null);

    }

    public int getHeight() {
        return height;
    }

    public Rect getRect(){
        return rect;
    }

    public int getBirdHeight() {
        return birdHeight;
    }
}
