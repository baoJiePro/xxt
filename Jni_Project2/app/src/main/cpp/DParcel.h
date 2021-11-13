//
// Created by baojie on 2021/11/2.
//
#include <malloc.h>
#include <jni.h>

#ifndef JNI_PROJECT_DPARCEL_H
#define JNI_PROJECT_DPARCEL_H


class DParcel {
public:
    DParcel();
    virtual ~DParcel();
    void writeInt(int data);
    void setDataPosition(int mDataPos);
    jint readInt();

private:
    // DParcel对象共享内存的首地址(内存地址)
    char * mData = 0;
    // DParcel对象共享内存的首地址(内存地址)的指针地址挪动位置
    int mDataPos = 0;
    // 用于改变指针地址挪动位置
    void changePos(int data);
    // StringObj对象的 首地址(内存地址)
    long * stringObj = nullptr;
};


#endif //JNI_PROJECT_DPARCEL_H
