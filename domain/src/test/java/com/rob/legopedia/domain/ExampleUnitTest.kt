package com.rob.legopedia.domain

import com.rob.legopedia.domain.dependencies.loggingInterceptor
import com.rob.legopedia.domain.dependencies.okHttpClient
import com.rob.legopedia.domain.dependencies.rebrickableService
import com.rob.legopedia.domain.dependencies.tokenInterceptor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class ExampleUnitTest {

    @get:Rule
    val coroutinesRule = TestCoroutineDispatcherRule()

    val retrofitService by lazy {
        rebrickableService(
            client = okHttpClient(
                interceptors = *arrayOf(tokenInterceptor(), loggingInterceptor())
            ),
            baseUrl = "https://rebrickable.com/"
        )
    }

    @Test
    fun addition_isCorrect() {
        runBlocking {
            retrofitService.searchSet("6004").results
        }
    }
}