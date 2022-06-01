package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow

open class PlaylistRepository {
    suspend fun getPlaylists() : Flow<Result<List<Playlist>>> {
    }

}
