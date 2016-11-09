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

        // 1.
        Bitmap bitmap = Bitmap.createBitmap(width, height, bitmapOrigin.getConfig());

        // 2. 메모리 할당 및 Pixels 배열 복사
        RsJni.CopyToIn(bitmapOrigin);


        //RsJni.CreateMemoryToOut(width * height);


        //RsJni.CalcNdk(width * height);


        //RsJni.CopyFromOut(bitmap);
        return bitmap;
    }
}
