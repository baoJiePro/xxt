//
// Created by baojie on 2021/11/2.
//

#include "DParcel.h"
DParcel::DParcel() {
    this->mData = static_cast<char *>(malloc(1024));
}

DParcel::~DParcel() {
    if (this->mData){
        free(this->mData);
        this->mData = nullptr;
    }
    if (this->mDataPos){
        this->mDataPos = NULL;
    }
}

void DParcel::writeInt(int data) {
    // DParcel对象首地址(内存地址)的挪动位置mDataPos 第一次：0位置
   * reinterpret_cast<int *>(this->mData + this->mDataPos) = data;
    // this->mDataPos += sizeof(val); // DParcel对象首地址(内存地址)的挪动位置mDataPos 第二次：4位置
    changePos(sizeof (data));
}

void DParcel::changePos(int data) {
    this->mDataPos += data;
}

void getLength(char * content, jint & len){

}

void DParcel::setDataPosition(int mDataPos) {
    this->mDataPos = mDataPos;
}

jint DParcel::readInt() {
    jint ret = * reinterpret_cast<int *>(this->mData + this->mDataPos);
    changePos(sizeof (int ));
    return ret;
}