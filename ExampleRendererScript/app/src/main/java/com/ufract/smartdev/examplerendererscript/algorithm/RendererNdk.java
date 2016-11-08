package com.ufract.smartdev.examplerendererscript.algorithm;

import android.graphics.Bitmap;

import com.ufract.smartdev.examplerendererscript.RsJni;

/**
 * Created by rasung82 on 2016. 11. 8..
 */

public class RendererNdk implements IRenderer {

    @Override
    public String getRendererName() {
        return RendererNdk.class.getSimpleName();
    }

    @Override
    public Bitmap render(Bitmap bitmapOrigin) {
        int width = bitmapOrigin.getWidth();
        int height = bitmapOrigin.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, bitmapOrigin.getConfig());
        RsJni.CopyToIn(bitmapOrigin);
        //RsJni.CreateMemoryToOut(width * height);
        //RsJni.CalcNdk(width * height);
        //RsJni.CopyFromOut(bitmap);
        return bitmap;
    }
}
