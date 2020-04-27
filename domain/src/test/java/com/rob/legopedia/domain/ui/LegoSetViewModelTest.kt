package com.rob.legopedia.domain.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rob.legopedia.domain.TestCoroutineDispatcherRule
import com.rob.legopedia.domain.dependencies.rebrickableService
import com.rob.legopedia.domain.network.RebrickableService
import com.rob.legopedia.domain.observeOnce
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LegoSetViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutinesRule = TestCoroutineDispatcherRule()

    private val mockServer = MockWebServer()
    private lateinit var mockService: RebrickableService
    private lateinit var viewModel: LegoSetViewModel

    @Before
    fun setUp() {
        mockServer.enqueue(mockSuccessResponse)
        mockServer.start()

        mockService = rebrickableService(
            OkHttpClient(), mockServer.url("").toString()
        )

        viewModel = LegoSetViewModel(mockService)
    }

    @Test
    fun `test success case`() {
        viewModel.legoSets.observeOnce {
            println(it.toString())
        }

        viewModel.searchSet("Test")

        viewModel.legoSets.observeForever {
            println(it.toString())
        }
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    companion object {
        private const val SUCCESS_RESPONSE =
            "{\"count\": 1,\"next\": null,\"previous\": null,\"results\": [{\"set_num\": \"60043-1\"\"name\": \"Prisoner Transporter\"\"year\": 2014\"theme_id\": 61\"num_parts\": 197\"set_img_url\": \"https://cdn.rebrickable.com/media/sets/60043-1/7267.jpg\"\"set_url\": \"https://rebrickable.com/sets/60043-1/prisoner-transporter/\"\"last_modified_dt\": \"2016-05-29T00:30:52.149029Z\"}]}"
        private const val ERROR_RESPONSE =
            "{\"detail\": \"Invalid token header. No credentials provided.\"}"

        private val mockSuccessResponse = MockResponse()
            .setResponseCode(200)
            .setBody(SUCCESS_RESPONSE)

        private val mockErrorResponse = MockResponse()
            .setResponseCode(401)
            .setBody(ERROR_RESPONSE)
    }
}