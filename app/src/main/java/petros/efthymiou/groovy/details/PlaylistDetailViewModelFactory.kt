package petros.efthymiou.groovy.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import petros.efthymiou.groovy.playlist.PlaylistRepository
import petros.efthymiou.groovy.playlist.PlaylistViewModel
import javax.inject.Inject

class PlaylistDetailViewModelFactory @Inject constructor(
    private val service : PlaylistDetailsService

): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaylistDetailViewModel(service) as T


    }


}
