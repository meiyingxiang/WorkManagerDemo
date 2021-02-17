package com.example.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*

const val INPUT_DATA_KEY = "input_data_key"
const val OUTPUT_DATA_KEY = "output_data_key"
const val INPUT_A_NAME = "Work A"
const val INPUT_B_NAME = "Work B"

class MainActivity : AppCompatActivity() {
    private val workManager: WorkManager = WorkManager.getInstance(application)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {

            val workRequestA = createWork(INPUT_A_NAME)
            val workRequestB = createWork(INPUT_B_NAME)

            workManager.beginWith(workRequestA)
                .then(workRequestB)
                .enqueue()


//            workManager.enqueue(workRequestA)
//            workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this, Observer {
//                if (it.state == WorkInfo.State.SUCCEEDED) {
//                    Log.e("Frank", "onCreate: value = ${it.outputData.getString(OUTPUT_DATA_KEY)}");
//                }
//            })
        }
    }

    private fun createWork(name: String): OneTimeWorkRequest {
        val constraints = Constraints.Builder()
            //                .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest = OneTimeWorkRequestBuilder<MyWork>()
            .setConstraints(constraints)
            .setInputData(workDataOf(INPUT_DATA_KEY to name))
            .build()
        return workRequest
    }
}