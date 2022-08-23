package petros.efthymiou.groovy.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_playlist_detail.*
import petros.efthymiou.groovy.R
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistDetailFragment : Fragment() {

    lateinit var viewModel : PlaylistDetailViewModel

    @Inject
    lateinit var viewModelFactory : PlaylistDetailViewModelFactory
    val args : PlaylistDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist_detail, container, false)
        val id : String = args.playlistId
        setupView()

        viewModel.getPlaylistDetails(id)

        observeLiveData()
        observeLoader()


        return view
    }

    private fun observeLoader() {
        viewModel.loader.observe(this as LifecycleOwner) { loading ->
            when (loading) {
                true -> details_loader.visibility = View.VISIBLE
                else -> details_loader.visibility = View.GONE
            }
        }
    }

    private fun observeLiveData() {
        viewModel.playlistDetails.observe(this as LifecycleOwner) { playlistDetails ->
            if (playlistDetails.getOrNull() != null) {
                setupUI(playlistDetails)
            } else {
                Snackbar.make(
                    playlist_details_root,
                    "Something went wrong",
                    Snackbar.LENGTH_SHORT
                ).show()            }
        }
    }

    private fun setupView() {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(PlaylistDetailViewModel::class.java)
    }

    private fun setupUI(playlistDetails: Result<PlaylistDetails>) {
        playlistTitleText.text = playlistDetails.getOrNull()!!.name
        playlistDetailsText.text = playlistDetails.getOrNull()!!.details
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistDetailFragment()
    }
}
