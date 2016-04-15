package com.victor.independenceday.objects.enemies;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;

import com.victor.independenceday.objects.AbstractObject;
import com.victor.independenceday.objects.ObjectConstants;
import com.victor.independenceday.physics.LinearVertical;
import com.victor.independenceday.physics.Trajectory;

/**
 * Created by Віктор on 29.09.2015.
 */
public class Arrow extends AbstractObject {

    Trajectory trajectory;

    private int bitmapHeight;
    private int bitmapWidth;
    private Rect src;

    private float xPrev;

    private static final int SPRITE_START = 4;
    private static final int SPRITE_AMOUNT = 7;

    public static final float DISTRIBUTION = .9f;
    public static final float ARROW_SIZE = .15f;

    private int spriteNumber;

    public Arrow(Bitmap bitmap, PointF basePoint, float size, int speed) {
        super(null, bitmap, basePoint, size, size);
        this.trajectory = new LinearVertical(100, 100);
        setTrajectory(trajectory);
        bitmapHeight = bitmap.getHeight();
        bitmapWidth = bitmap.getWidth() / SPRITE_AMOUNT;
        setHealthPoints(ObjectConstants.ARROW_HEALTH);
        setSpeed(speed);

        src = new Rect();
        src.left = bitmapWidth * (SPRITE_START - 1);
        src.top = 0;
        src.right = bitmapWidth * SPRITE_START;
        src.bottom = bitmapHeight;
        setSrc(src);

        setWidth(size * (((float) bitmapWidth) / ((float) bitmapHeight)) * 1f);
        refreshDst();

        spriteNumber = SPRITE_START;
        xPrev = basePoint.x;
    }

    @Override
    public void changeSprite(int order) {
        src = getSrc();
        src.left = bitmapWidth * (order - 1);
        src.right = bitmapWidth * order;
        setSrc(src);
        spriteNumber = order;
    }

    @Override
    public int getSpriteNumber() {
        return spriteNumber;
    }

    @Override
    public float getXPrev() {
        return xPrev;
    }

    @Override
    public void setXPrev(float xPrev) {
        this.xPrev = xPrev;
    }
}
