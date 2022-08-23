package petros.efthymiou.groovy.details

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest
import petros.efthymiou.groovy.utils.captureValues
import petros.efthymiou.groovy.utils.getValueForTest
import java.lang.RuntimeException

class PlaylistDetailsViewModelShould:BaseUnitTest() {

    lateinit var viewModel: PlaylistDetailViewModel
    private val id = "1"
    private val service : PlaylistDetailsService = mock()
    private val playlistDetails : PlaylistDetails = mock()
    private val expected = Result.success(playlistDetails)
    private val exception = RuntimeException("Something went wrong")
    private val error = Result.failure<PlaylistDetails>(exception)


    @Test
    fun getPlaylistDetailsFromService() = runBlockingTest {
        mockSuccessCase()
        viewModel.playlistDetails.getValueForTest()

        verify(service, times(1)).fetchPlaylistDetails(id)

    }

    @Test
    fun emitPlaylistDetailsFromService() = runBlockingTest{
        mockSuccessCase()
        viewModel.getPlaylistDetails(id)

        assertEquals(expected, viewModel.playlistDetails.getValueForTest())

    }

    @Test
    fun emitErrorWhenServiceFails() = runBlockingTest {
        mockFailCase()

        assertEquals(error, viewModel.playlistDetails.getValueForTest())
    }

    @Test
    fun showLoaderWhileLoading() = runBlockingTest{
        mockSuccessCase()
        viewModel.loader.captureValues{
            viewModel.getPlaylistDetails(id)
            viewModel.playlistDetails.getValueForTest()

            Assert.assertEquals(true, values[0])
        }
    }

    @Test
    fun closeLoaderAfterPlaylistDetailsLoad() = runBlockingTest{
        mockSuccessCase()

        viewModel.loader.captureValues{
            viewModel.getPlaylistDetails(id)
            viewModel.playlistDetails.getValueForTest()

            Assert.assertEquals(false, values.last())
        }
    }

    private suspend fun mockFailCase() {
        whenever(service.fetchPlaylistDetails(id)).thenReturn(
            flow {
                emit(error)
            }
        )
        viewModel = PlaylistDetailViewModel(service)
        viewModel.getPlaylistDetails(id)
    }

    private suspend fun mockSuccessCase() {
        whenever(service.fetchPlaylistDetails(id)).thenReturn(
            flow {
                emit(expected)
            }
        )
        viewModel = PlaylistDetailViewModel(service)
        viewModel.getPlaylistDetails(id)
    }


}