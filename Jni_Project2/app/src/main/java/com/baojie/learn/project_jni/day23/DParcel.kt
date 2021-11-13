package com.baojie.learn.project_jni.day23

class DParcel private constructor(nativePtr: Long){

    private var mNativePtr: Long = 0

    object MyObject{
        val DPARCEL = DParcel(0)
    }

    companion object{
        init {
            System.loadLibrary("native-lib")
        }
        fun obtain(): DParcel = MyObject.DPARCEL
    }

    init {
        mNativePtr = nativePtr
        // Native初始化，拿到Native的 DParcel.cpp 对象的首地址(内存地址)
        mNativePtr = nativeCreate()
    }

    // 在native层构建 Parcel.cpp对象
    private external fun nativeCreate(): Long
    // 写入int
    private external fun nativeWriteInt(nativePtr: Long, data:Int)
    // 写完后 设置偏移位置
    private external fun nativeSetDataPosition(nativePtr: Long, pos: Int)
    private external fun nativeReadInt(nativePtr: Long): Int // 读取int

    fun writeInt(data: Int) = nativeWriteInt(this.mNativePtr, data)

    fun readInt() = nativeReadInt(this.mNativePtr)

    fun setDataPosition(pos: Int) = nativeSetDataPosition(this.mNativePtr, pos)
}