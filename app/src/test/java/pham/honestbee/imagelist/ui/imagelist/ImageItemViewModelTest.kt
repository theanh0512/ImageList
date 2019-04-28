package pham.honestbee.imagelist.ui.imagelist

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.junit.MockitoJUnit
import pham.honestbee.imagelist.vo.Image

/**
 * Created by Pham on 28/4/2019.
 */
@RunWith(JUnit4::class)
class ImageItemViewModelTest {
    @get:Rule
    val rule = MockitoJUnit.rule()

    lateinit var viewModel: ImageItemViewModel

    @Before
    fun before() {
        val image = Image(1, 1, "title",
                "https://via.placeholder.com/600/d32776",
                "https://via.placeholder.com/150/d32776")
        viewModel = ImageItemViewModel(image)
    }

    @Test
    fun getDate() {
        MatcherAssert.assertThat(viewModel.getUrl(), CoreMatchers.`is`("https://via.placeholder.com/600/d32776"))
    }

    @Test
    fun getDegree() {
        MatcherAssert.assertThat(viewModel.getThumbnail(), CoreMatchers.`is`("https://via.placeholder.com/150/d32776"))
    }
}