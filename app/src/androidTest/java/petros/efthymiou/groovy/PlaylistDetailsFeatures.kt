package petros.efthymiou.groovy

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import org.hamcrest.CoreMatchers
import org.hamcrest.core.AllOf.allOf
import org.junit.Test
import petros.efthymiou.groovy.playlist.idlingResource

class PlaylistDetailsFeatures : BaseUITest() {


    @Test
    fun displaysErrorMessageWhenNetworkFails() {
        navigateToPlaylistDetails(1)
        assertDisplayed("Something went wrong")
    }


    @Test
    fun displayPlaylistNameAndDetails() {

        navigateToPlaylistDetails(0)
        assertDisplayed("Hard Rock Cafe")
        assertDisplayed("Rock your senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door")
    }

    @Test
    fun displayLoaderWhileFetchingThePlaylist() {
        IdlingRegistry.getInstance().unregister(idlingResource)
        Thread.sleep(3000)
        navigateToPlaylistDetails(0)
        assertDisplayed(R.id.details_loader)
    }

    @Test
    fun hidesLoader() {
        navigateToPlaylistDetails(0)
        assertNotDisplayed(R.id.details_loader)
    }

    @Test
    fun hidesErrorMessage(){
        navigateToPlaylistDetails(2)

        Thread.sleep(3000)

        assertNotExist("Something went wrong")
    }

    private fun navigateToPlaylistDetails(row: Int) {
        onView(
            allOf(
                withId(R.id.playlist_image),
                ViewMatchers.isDescendantOfA(nthChildOf(withId(R.id.playlist_list), row))
            )
        ).perform(click())
    }

}