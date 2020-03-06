package com.example.common.common

class MyCaughtExcepHandler : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(
        thread: Thread,
        throwable: Throwable
    ) {
    }
}