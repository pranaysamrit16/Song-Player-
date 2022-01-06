package com.example.walkman

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class SongListMainActivity : AppCompatActivity() {
    private val requestCode: Int = 102
    private val TAG:String = "SongListMainActivityboss"
    private val storagePerm = android.Manifest.permission.READ_EXTERNAL_STORAGE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.song_list_activity_main)
        Log.d(TAG, "onCreate() called with: savedInstanceState = $savedInstanceState")

        if(ContextCompat.checkSelfPermission(this,storagePerm)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(storagePerm),requestCode)
        } else {
            loadUI()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        Log.d(TAG, "onRequestPermissionsResult() called with: requestCode = $requestCode, " +
                "permissions = $permissions, grantResults = $grantResults")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 102) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                loadUI()
            } else {
                Toast.makeText(this, "You can't proceed without giving permissions",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadUI() {

    }

    override fun onResume() {
        Log.d(TAG, "onResume() called")
        super.onResume()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart() called")
        super.onRestart()
    }

    override fun onStart() {
        Log.d(TAG, "onStart() called")
        super.onStart()
    }

    override fun onStop() {
        Log.d(TAG, "onStop() called")
        super.onStop()
    }

    override fun onPause() {
        Log.d(TAG, "onPause() called")
        super.onPause()
    }
}