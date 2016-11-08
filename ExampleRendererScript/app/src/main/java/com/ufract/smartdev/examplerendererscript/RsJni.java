package com.ufract.smartdev.examplerendererscript;

import android.graphics.Bitmap;

/**
 * Created by rasung82 on 2016. 11. 8..
 */

public class RsJni {
    static {
        System.loadLibrary("Rs-jni");
    }

    public native static void CopyToIn(Bitmap bitmap);

    public native static void CreateMemoryToOut(int size);

    public native static void CopyFromOut(Bitmap bitmap);

}
