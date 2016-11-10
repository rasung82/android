package com.ufract.smartdev.examplerendererscript.algorithm;

import android.graphics.Bitmap;
import android.util.Log;

import com.ufract.smartdev.examplerendererscript.RsJni;

/**
 * Created by rasung82 on 2016. 11. 8..
 */

public class RendererNdkNeon implements IRenderer {

    public static String TAG = RendererNdkNeon.class.getSimpleName();

    @Override
    public String getRendererName() {
        return RendererNdkNeon.class.getSimpleName();
    }


    @Override
    public Bitmap render(Bitmap bitmapOrigin) {
        Log.v(TAG, getRendererName()+".renderer()");

        int width = bitmapOrigin.getWidth();
        int height = bitmapOrigin.getHeight();

        // 1.
        Bitmap bitmap = Bitmap.createBitmap(width, height, bitmapOrigin.getConfig());

        // 2. In 메모리 할당 및 pixels 배열 복사
        RsJni.CopyToIn(bitmapOrigin);

        // 3. Out 메모리 할당
        RsJni.CreateMemoryToOut(width * height);

        // 4. GrayScale 변환
        RsJni.CalcNdkNeon(width * height);

        // 5. Out 메모리의 내용을 pixels 배열로 복사
        RsJni.CopyFromOut(bitmap);

        return bitmap;
    }
}
