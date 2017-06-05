package com.three.phoneinfotest;

/**
 * Created by sunpeng on 17-6-5.
 */

public class NativeMethod {
    static {
        System.loadLibrary("NativeMethod");
    }

    public native String readFile();
}
