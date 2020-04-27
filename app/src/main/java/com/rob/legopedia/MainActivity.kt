package com.rob.legopedia

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.ui.core.setContent
import com.rob.legopedia.domain.dependencies.appComponent
import com.rob.legopedia.domain.ui.LCE
import com.rob.legopedia.domain.ui.LegoSet
import com.rob.legopedia.domain.ui.LegoSetViewModel
import com.rob.legopedia.domain.viewModelFromProvider

class MainActivity : AppCompatActivity() {

    private val legoSetViewModel: LegoSetViewModel by viewModelFromProvider { appComponent.provider }

    private var isDetail: Boolean = false

    private val onImeAction: (String) -> Unit = {
        legoSetViewModel.searchSet(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmptyScreen(onImeAction)
        }

        legoSetViewModel.legoSets.observe(this, Observer {
            when (it) {
                is LCE.Loading -> setContent { WaitingScreen() }
                is LCE.Error -> showError(it.error)
                is LCE.Complete -> showSuccess(it.data)
            }
        })
    }

    private fun showError(error: Throwable?) {
        println("ERROR: ${error?.message}")

        setContent { ErrorScreen(onImeAction = onImeAction) }
        isDetail = true
    }

    private fun showSuccess(data: List<LegoSet>?) {
        if (data.isNullOrEmpty()) {
            setContent { EmptyScreen(onImeAction, "No sets where found, search for other IDs") }
        } else {
            setContent {
                SetView(data, onImeAction) {
                    showDetail(it.detailUrl)
                }
            }
        }
        isDetail = true
    }

    private fun showDetail(detailUrl: String) {
        val tabsIntent =
            CustomTabsIntent.Builder()
                .addDefaultShareMenuItem()
                .setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .build()

        tabsIntent.launchUrl(this, Uri.parse(detailUrl))
    }

    override fun onBackPressed() {
        if (isDetail) {
            setContent {
                EmptyScreen(onImeAction)
            }
            isDetail = false
        } else {
            super.onBackPressed()
        }
    }
}