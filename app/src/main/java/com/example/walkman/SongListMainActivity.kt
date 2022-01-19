package com.example.walkman

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SongListMainActivity : AppCompatActivity() {
    private val requestCode: Int = 102
    private val storagePerm = android.Manifest.permission.READ_EXTERNAL_STORAGE
    private lateinit var mConstants : Constants

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.song_list_activity_main)
        Log.d(TAG, "onCreate() called with: savedInstanceState = $savedInstanceState")

        mConstants = Constants()

        if(ContextCompat.checkSelfPermission(this,storagePerm)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(storagePerm),requestCode)
        } else {
            loadFilesAndUI()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        Log.d(TAG, "onRequestPermissionsResult() called with: requestCode = $requestCode, " +
                "permissions = $permissions, grantResults = $grantResults")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 102) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                loadFilesAndUI()
            } else {
                Toast.makeText(this, "You can't proceed without giving permissions",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadFilesAndUI() {
        Log.d(TAG, "loadFilesAndUI:")
        SongsDataObject.emptySongList()
        loadMediaFiles()
        loadUI()
    }

    private fun loadUI() {
        Log.d(TAG, "loadUI: ")
        val rec : RecyclerView = findViewById(R.id.rec2)
        rec.layoutManager = LinearLayoutManager(this)
        val adapter = SongListAdapter(object : ClickListener {
            override fun onClick(item: View?, number: Int) {
                onSongClicked(number)
            }
        })
        rec.adapter = adapter
    }

    private fun onSongClicked(songNumber: Int) {
        Toast.makeText(this,"Song clicked number = "+songNumber,Toast.LENGTH_SHORT).show()
    }


    private fun loadMediaFiles() {
        val files= mConstants.mediaFile.listFiles()
        var data: ByteArray?
        val mmr = MediaMetadataRetriever()
        var bitmap: Bitmap

        if(files != null) {
            for (file in files) {
                Log.d(TAG, "file: " + file.name)
                mmr.apply { setDataSource(file.path) }
                data = mmr.embeddedPicture
                if (data != null) {
                    bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
                } else {
                    bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.img1)
                }
                val block = MediaBlock(file.name, bitmap)
                SongsDataObject.songFiles.add(block)
            }
            Log.d(TAG,"Size of songs = "+SongsDataObject.songFiles.size)
        }
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

    companion object{
        private val TAG:String = "SongListMainActivityboss"
    }
}