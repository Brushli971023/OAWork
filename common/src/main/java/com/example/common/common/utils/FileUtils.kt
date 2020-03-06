package com.example.common.common.utils

import android.os.Environment
import java.io.*

class FileUtils {
    /**
     * 保存异常信息到本地
     * @param ex
     */
    private fun saveCrashLogToLocal(ex: Throwable) {
        val writer: Writer = StringWriter()
        val printWriter = PrintWriter(writer)
        ex.printStackTrace(printWriter)
        var exCause = ex.cause
        while (exCause != null) {
            exCause.printStackTrace(printWriter)
            exCause = exCause.cause
        }
        printWriter.close()
        //错误日志文件名称
        val fileName = createLogFileName()
        //文件存储位置
        val path =
            Environment.getExternalStorageDirectory().path + logSubPath
        //保存crash log到本地
        SaveFile(
            path,
            fileName,
            writer.toString()
        )
        //将crash日志 上传到服务器
    }

    /**
     * 创建日志文件名称
     * @return
     */
    private fun createLogFileName(): String {
        val timeMillis = System.currentTimeMillis()
        //错误日志文件名称
        return "crash-$timeMillis.log"
    }

    companion object {
        /**
         * 保存文件
         *
         * @param path     保存文件路径
         * @param fileName 文件名
         * @param content  内存内容
         */
        fun SaveFile(
            path: String,
            fileName: String,
            content: String
        ) { //判断sd卡可正常使用
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) { //文件存储位置
                val fl = File(path)
                //创建文件夹
                if (!fl.exists()) {
                    fl.mkdirs()
                }
                try {
                    val fileOutputStream =
                        FileOutputStream(path + fileName)
                    fileOutputStream.write(content.toByteArray())
                    fileOutputStream.close()
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        /**
         * log存储的子目录
         */
        private const val logSubPath = "/crash_logInfo/"
    }
}