package com.baojie.learn.jni_project_video

class FastPlayer {
    // 媒体源（文件路径， 直播地址rtmp）
    private var dataSource: String? = null

    private var preparedListener: OnPreparedListener? = null

    companion object {
        // Used to load the 'jni_project_video' library on application startup.
        init {
            System.loadLibrary("jni_project_video")
        }
    }

    /**
     * 设置媒体资源
     */
    fun setDataSource(source: String){
        dataSource = source
    }

    /**
     * 播放前准备工作
     */
    fun prepare(){
        prepareNative(dataSource)
    }

    /**
     * 开始播放
     */
    fun start(){
        startNative()
    }

    /**
     * 停止播放
     */
    fun stop(){
        stopNative()
    }

    /**
     * 释放资源
     */
    fun release(){
        releaseNative()
    }

    /**
     * 给jni反射调用
     */
    fun onPrepared(){
        preparedListener?.onPrepared()
    }

    /**
     * 设置监听
     */
    fun setOnPreparedListener(onPreparedListener: OnPreparedListener){
        preparedListener = onPreparedListener
    }

    /**
     * 准备OK的监听
     */
    interface OnPreparedListener{
       fun onPrepared()
    }


    // TODO >>>>>>>>>>> 下面是native函数区域
    private external fun prepareNative(dataSource: String?)
    private external fun startNative()
    private external fun stopNative()
    private external fun releaseNative()
}