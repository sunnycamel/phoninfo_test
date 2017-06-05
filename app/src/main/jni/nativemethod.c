//
// Created by sunpeng on 17-6-5.
//
#include <unistd.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <stdlib.h>
#include <fcntl.h>

#include "com_three_phoneinfotest_NativeMethod.h"


JNIEXPORT jstring JNICALL Java_com_three_phoneinfotest_NativeMethod_readFile(JNIEnv * env,  jclass clazz)
{
    int fd;
    unsigned char buf[255];
    int len;
    fd = open("/sys/class/net/wlan0/address", O_RDONLY);
    if (fd != -1)
    {
        len = read(fd, buf, 255);
        buf[len] = 0;
        return (*env)->NewStringUTF(env,buf);
    }

    return (*env)->NewStringUTF(env,"file open failed!");
}