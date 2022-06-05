package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import javax.inject.Inject


open class PlaylistService @Inject constructor(private val api : PlaylistAPI){

    open suspend fun fetchPlaylists() : Flow<Result<List<PlaylistRaw>>>{
        return flow {
            emit(Result.success(api.fetchAllPlaylists()))
            //emit(Result.success(fetchAllPlaylists()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

/*
    private fun fetchAllPlaylists(): List<Playlist> {
        val list = arrayListOf<Playlist>()
        list.add(
            Playlist(
                "1",
                "Hard Rock Cafe",
                "rock"
            )
        )
        list.add(
            Playlist(
                "2",
                "Chilled House",
                "house"
            )
        )
        list.add(
            Playlist(
                "3",
                "US TOP 40 HITS",
                "mixed"
            )
        )
        list.add(
            Playlist(
                "4",
                "90's Rock",
                "rock"
            )
        )
        list.add(
            Playlist(
                "5",
                "Purple Jazz",
                "jazz"
            )
        )
        list.add(
            Playlist(
                "6",
                "90's flashback",
                "pop"
            )
        )
        list.add(
            Playlist(
                "7",
                "Machine Funk",
                "electro"
            )
        )
        list.add(
            Playlist(
                "8",
                "Let's Groove",
                "mixed"
            )
        )
        list.add(
            Playlist(
                "9",
                "Feel The Beat",
                "electro"
            )
        )
        list.add(
            Playlist(
                "10",
                "Best Songs 2020",
                "mixed"
            )
        )

        return list

    }

 */
}
