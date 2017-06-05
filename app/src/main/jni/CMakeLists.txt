#设置最小的CMake编译版本，确保你能够顺利编译
cmake_minimum_required(VERSION 3.4.1)

#指定库名称，指定库是静态还是共享，并提供源代码的相对路径。
#您可以通过添加多个add.library（）命令定义多个库，CMake为您构建它们。
#当您构建应用程序时，Gradle会自动使用您的APK打包共享库。

add_library( #指定库的名称
             NativeMethod

             #将该库设置为一个共享库
             SHARED

             #提供源文件的相对路径
             nativemethod.c)

# 将预构建库链接到你的本地库中，这里的意思是将log库链接到helloJNI库中
target_link_libraries(NativeMethod
                      android
                      log)