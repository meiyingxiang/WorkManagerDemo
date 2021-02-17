package com.example.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class MyWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val name = inputData.getString(INPUT_DATA_KEY)
        Log.e("Frank", "$name start: ")
        Log.e("Frank", "$name doWorking ")
        Thread.sleep(3000)
        Log.e("Frank", "$name end: ")
        return Result.success(workDataOf(OUTPUT_DATA_KEY to "$name output  Work"))
    }

}