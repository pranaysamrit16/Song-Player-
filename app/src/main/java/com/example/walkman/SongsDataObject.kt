package com.example.walkman

object SongsDataObject{
    var songFiles: MutableList<MediaBlock> = mutableListOf()

    fun emptySongList() {
        songFiles = mutableListOf()
    }
}