package petros.efthymiou.groovy.playlist

import junit.framework.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.utils.BaseUnitTest

class PlaylistMapperShould : BaseUnitTest(){

    private val playlistRaw = PlaylistRaw("1", "any name", "any category")
    private val playlistRawRock = PlaylistRaw("1", "any name", "rock")

    private val mapper = PlaylistMapper()
    private val playlists = mapper(listOf(playlistRaw))
    private val playlistRock = mapper(listOf(playlistRawRock))[0]
    @Test
    fun keepSameId(){
        assertEquals(playlistRaw.id, playlists[0].id)
    }

    @Test
    fun keepSameName(){
        assertEquals(playlistRaw.name, playlists[0].name)
    }

    @Test
    fun keepSameCategory(){
        assertEquals(playlistRaw.category, playlists[0].category)
    }

    @Test
    fun mapDefaultImage(){
        assertEquals(R.mipmap.playlist, playlists[0].image)
    }

    @Test
    fun mapRockImage(){
        assertEquals(R.mipmap.rock, playlistRock.image)
    }

}