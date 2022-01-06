package com.example.walkman

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class SongListMainActivity : AppCompatActivity() {
    private val TAG:String = "SongListMainActivity"
    private val EXTERNAL_REQUEST: Int = 138
    private val EXTERNAL_PERMS: Array<String> = arrayOf(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )


    private lateinit var sharedPref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.song_list_activity_main)
        /*sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        if(!sharedPref.getBoolean("Permission_Granted",false)){
            requestForPermission()
        }*/
        if(ContextCompat.checkSelfPermission(applicationContext,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .equals(PackageManager.PERMISSION_DENIED)){
            requestPermissionLauncher.launch(
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this,"Permission granted",Toast.LENGTH_SHORT).show()
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }

    private fun requestForPermission() {
        var isPermission : Boolean = true
        if(Build.VERSION.SDK_INT > 23){
            if(PackageManager.PERMISSION_GRANTED ==
                ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        requestForPermission()
                    requestPermissions(EXTERNAL_PERMS, EXTERNAL_REQUEST)
            }
        }
    }
}