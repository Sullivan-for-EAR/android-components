package com.sullivan.common.ui_common.base

import android.os.Bundle
import android.view.View
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.sullivan.common.ui_common.ex.makeGone
import com.sullivan.common.ui_common.ex.makeVisible
import com.sullivan.common.ui_common.ex.viewLifecycle
import com.sullivan.common.ui_common.view.progressbar.ProgressbarListener

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected var binding: T by viewLifecycle()
    protected lateinit var progressBar: ContentLoadingProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

        progressBar = getProgressbarView()
    }


    abstract fun setupView()
    abstract fun getProgressbarView(): ContentLoadingProgressBar

    fun showProgressBar() {
        progressBar.makeVisible()
    }

    fun hideProgressBar() {
        progressBar.makeGone()
    }
}