# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# ffmpeg的路径
set(FFMPEG ${CMAKE_SOURCE_DIR}/ffmpeg)
#rtmp路径
set(RTMP ${CMAKE_SOURCE_DIR}/rtmp)

# Declares and names the project.

project("jni_project_video")
#导入ffmpeg头文件
include_directories(${FFMPEG}/include)
#ffmpeg库指定
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -L${FFMPEG}/libs/${CMAKE_ANDROID_ARCH_ABI}")
#rtmp库指定
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -L${RTMP}/libs/${CMAKE_ANDROID_ARCH_ABI}")

file(GLOB src_files *.cpp)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        jni_project_video

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        ${src_files})

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        jni_project_video

        # 忽略顺序的方式，导入
        -Wl,--start-group
        avcodec avfilter avformat avutil swresample swscale
        -Wl,--end-group

        log # 日志库，打印日志用的
        z # libz.so库，是FFmpeg需要用ndk的z库，FFMpeg需要额外支持  libz.so
        rtmp # rtmp 后面会专门介绍
        android # android 后面会专门介绍，目前先要明白的是 ANativeWindow 用来渲染画面的
        OpenSLES # OpenSLES 后面会专门介绍，目前先要明白的是 OpenSLES 用来播放声音的
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})