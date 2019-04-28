package pham.honestbee.imagelist.ui.imagelist

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import pham.honestbee.imagelist.TestSchedulerRule
import pham.honestbee.imagelist.api.ImageListService
import pham.honestbee.imagelist.repository.ImageListRepository
import pham.honestbee.imagelist.vo.Image
import java.util.*
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class ImageListRepositoryTest {
    private lateinit var repository: ImageListRepository

    private val service = Mockito.mock(ImageListService::class.java)

    @get:Rule
    val rule = TestSchedulerRule()

    @Before
    fun before() {
        repository = ImageListRepository(service)
    }

    @Test
    fun testGetImageList() {
        val image = Image(1, 1, "title",
                "https://via.placeholder.com/600/d32776",
                "https://via.placeholder.com/150/d32776")
        val imageList = ArrayList<Image>()
        imageList.add(image)
        `when`(service.getImages()).thenReturn(Observable.just(imageList))
        val testObserver:TestObserver<List<Image>> = repository.getImageList().test()
        //repository.getImageList().subscribe(testObserver)
        rule.testScheduler.advanceTimeBy(2, TimeUnit.SECONDS)
        testObserver
                .assertNoErrors()
                .assertValue{ l:List<Image> -> l.size == 1}
    }
}