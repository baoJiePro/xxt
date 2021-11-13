//
// Created by baojie on 2021/10/30.
//
#include <iostream>
#include <android/log.h>
#define TAG "baoJie"
// __VA_ARGS__ 代表 ...的可变参数
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG,  __VA_ARGS__);
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG,  __VA_ARGS__);
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG,  __VA_ARGS__);
int age = 18;
void show(){
    LOGI("show run age: %d\n", age);
}