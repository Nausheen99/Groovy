package petros.efthymiou.groovy.details

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest
import java.lang.RuntimeException

class PlaylistDetailsServiceShould :BaseUnitTest(){

    private val api: PlaylistDetailsAPI = mock()
    private lateinit var service : PlaylistDetailsService
    private val id = "1"
    private val playlistDetails: PlaylistDetails = mock()
    private val exception = RuntimeException("Damn backend developers again 500!!!")



    @Test
    fun fetchPlaylistDetailsFromAPI () = runBlockingTest{
        mockSuccessfulCase()

        service = PlaylistDetailsService(api)

        service.fetchPlaylistDetails(id).single()
        verify(api, times(1)).fetchPlaylistDetails(id)

    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runBlockingTest{
        mockSuccessfulCase()

        Assert.assertEquals(
            Result.success(playlistDetails),
            service.fetchPlaylistDetails(id).single()
        )
    }

    @Test
    fun emitErrorWhenNetworkFails() = runBlockingTest{
        mockErrorCase()

        Assert.assertEquals(
            "Something went wrong",
            service.fetchPlaylistDetails(id).single().exceptionOrNull()?.message
        )
    }

    private suspend fun mockErrorCase() {
        whenever(api.fetchPlaylistDetails(id)).thenThrow(exception)

        service = PlaylistDetailsService(api)
    }

    private suspend fun mockSuccessfulCase() = runBlockingTest{
        whenever(api.fetchPlaylistDetails(id)).thenReturn(playlistDetails)

        service = PlaylistDetailsService(api)
    }

}