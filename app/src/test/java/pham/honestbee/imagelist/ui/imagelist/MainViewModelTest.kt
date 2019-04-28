package pham.honestbee.imagelist.ui.imagelist

import android.test.mock.MockContext
import io.reactivex.Observable
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import pham.honestbee.imagelist.repository.ImageListRepository
import pham.honestbee.imagelist.vo.Image

@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    val rule = MockitoJUnit.rule()
    private lateinit var mockWebServer: MockWebServer

    @Mock
    val context = MockContext()
    @Mock
    lateinit var imageListRepository: ImageListRepository
    lateinit var viewModel: MainViewModel

    @Before
    fun before() {
        mockWebServer = MockWebServer()
        val image = Image(1, 1, "title",
                "https://via.placeholder.com/600/d32776",
                "https://via.placeholder.com/150/d32776")
        val imageList = ArrayList<Image>()
        imageList.add(image)
        `when`(imageListRepository.getImageList()).thenReturn(Observable.just(imageList))
        viewModel = MainViewModel(context, imageListRepository)
    }

    @Test
    fun init() {
        verify(imageListRepository).getImageList()
        Mockito.verifyNoMoreInteractions(imageListRepository)
    }

    @Test
    fun verifyLoadingIsFalseWhenReturn() {
        verify(imageListRepository, times(1)).getImageList()
        assert(!viewModel.loading.get())
        assert(viewModel.loadSuccess.get())
        MatcherAssert.assertThat(viewModel.images[0].id, `is`(1))
    }
}