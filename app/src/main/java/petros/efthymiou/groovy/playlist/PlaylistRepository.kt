package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

open class PlaylistRepository @Inject constructor(
    private val service : PlaylistService,
    private val mapper : PlaylistMapper
) {

    suspend fun getPlaylists() : Flow<Result<List<Playlist>>> {
        val res = service.fetchPlaylists().map {
            if (it.isSuccess)
                Result.success(mapper(it.getOrNull()!!))
            else
                Result.failure(it.exceptionOrNull()!!)
        }

        return res
    }









}
