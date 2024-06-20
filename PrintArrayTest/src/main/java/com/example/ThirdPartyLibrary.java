package com.example;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public interface ThirdPartyLibrary extends Library {
    ThirdPartyLibrary INSTANCE = (ThirdPartyLibrary) Native.loadLibrary(
        System.getProperty("user.dir")+"/libthirdparty.so",
        ThirdPartyLibrary.class
    );

    int printArray(int ip, short port, byte[] buffer, int length);
}
