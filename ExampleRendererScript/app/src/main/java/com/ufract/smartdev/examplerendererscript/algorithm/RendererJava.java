package com.ufract.smartdev.examplerendererscript.algorithm;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Created by rasung82 on 2016. 11. 7..
 */

public class RendererJava implements IRenderer {

    public static String TAG = RendererJava.class.getSimpleName();


    @Override
    public String getRendererName() {
        return RendererJava.class.getSimpleName();
    }

    @Override
    public Bitmap render(Bitmap bitmapOrigin) {
        Log.v(TAG, getRendererName()+".renderer()");

        int width = bitmapOrigin.getWidth();
        int height = bitmapOrigin.getHeight();

        Bitmap bitmap = Bitmap.createBitmap(width, height, bitmapOrigin.getConfig());
        int size = width * height;
        int [] pixels = new int[size];
        bitmapOrigin.getPixels(pixels, 0, width, 0, 0, width, height);

        for (int i = 0; i < size; i++) {
            int c = pixels[i];  // 0xAARRGGBB
            int r = (c >> 16) & 0xff;   // 0xRR
            int g = (c >> 8)  & 0xff;   // 0xGG
            int b =  c        & 0xff;   // 0xBB
            int y = (r * 76 + g * 151 + b * 29) >> 8; // luminance
            pixels[i] = y | (y << 8) | (y << 16) | (c & 0xff000000);
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
