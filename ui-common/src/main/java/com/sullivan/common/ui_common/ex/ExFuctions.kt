package com.sullivan.common.ui_common.ex

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.sullivan.common.ui_common.R

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KFunction0
import kotlin.reflect.KProperty

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}

// 액티비티 전환 후 종료하는 함수
inline fun <reified T : Activity> Activity.launchActivity() {
    val intent = Intent(this, T::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
    overridePendingTransition(0, 0)
    finish()
}


inline fun <reified T : Activity> Activity.reorderToActivity() {
    val intent = Intent(this, T::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
    startActivity(intent)
    overridePendingTransition(0, 0)
    finish()
}

fun <T> Fragment.viewLifecycle(): ReadWriteProperty<Fragment, T> =
    object : ReadWriteProperty<Fragment, T>, DefaultLifecycleObserver {

        private var binding: T? = null

        init {
            this@viewLifecycle
                .viewLifecycleOwnerLiveData
                .observe(this@viewLifecycle, { owner: LifecycleOwner? ->
                    owner?.lifecycle?.addObserver(this)
                })
        }

        override fun onDestroy(owner: LifecycleOwner) {
            binding = null
        }

        override fun getValue(
            thisRef: Fragment,
            property: KProperty<*>
        ): T {
            return this.binding ?: error("Called before onCreateView or after onDestroyView.")
        }

        override fun setValue(
            thisRef: Fragment,
            property: KProperty<*>,
            value: T
        ) {
            this.binding = value
        }
    }

fun Fragment.makeToast(text: String) {
    val toast = Toast.makeText(activity, text, Toast.LENGTH_SHORT)

//    toast.setGravity(Gravity.BOTTOM, 0, 150)
    toast.show()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

fun View.makeVisible() {
    this.isVisible = true
}

fun View.makeGone() {
    this.isVisible = false
}

fun View.makeEnable() {
    this.isEnabled = true
}

fun View.makeDisable() {
    this.isEnabled = false
}

fun RecyclerView.runLayoutAnimation() {
    val context = this.context
    val controller: LayoutAnimationController =
        AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anmiation_fall_down)
    this.layoutAnimation = controller
    this.scheduleLayoutAnimation()
}

fun Context.makeToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun View.makeSnackBar(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_SHORT).show()
}

fun Context.isConnected(): Boolean {
    val connectivityManager =
        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetwork
    return connectivityManager != null
}

fun Context.getResourceId(title: String) = resources.getIdentifier(title, "drawable", packageName)

fun Fragment.openDialog(fragment: BottomSheetDialogFragment, tag: String) {
    val ft = parentFragmentManager.beginTransaction()
    val prev: Fragment? = parentFragmentManager.findFragmentByTag(tag)
    if (prev != null) {
        ft.remove(prev).commit()
    }
//    ft.addToBackStack(null)

    try {
        if (!fragment.isAdded) {
            fragment.show(ft, tag)
        }
    } catch (e: Exception) {
        // Exception is ignored.
    }
}

fun FragmentManager.openDialog(fragment: DialogFragment, tag: String) {
    val ft = this.beginTransaction()
    val prev: Fragment? = this.findFragmentByTag(tag)
    if (prev != null) {
        ft.remove(prev).commit()
    }
//    ft.addToBackStack(null)

    try {
        if (!fragment.isAdded) {
            fragment.show(ft, tag)
        }
    } catch (e: Exception) {
        // Exception is ignored.
    }
}

fun Activity.setupDialogWithAction(
    title: String,
    positiveBtn: String,
    negativeBtn: String,
    behavior: KFunction0<Unit>,
    message: String = ""
) {

    MaterialAlertDialogBuilder(
        this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog
    )
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveBtn) { _, _ ->
            behavior()
        }
        .setNegativeButton(negativeBtn) { dialog, _ ->
            dialog.dismiss()
        }
        .setCancelable(false)
        .show()
}

fun Context.showDialog(title: String, message: String, positiveBtn: String) {
    val dialog = MaterialAlertDialogBuilder(
        this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog
    )
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveBtn) { dialog, _ ->
            dialog.dismiss()
        }
//        .setNegativeButton(R.string.fragment_reservation_dialog_reservation_cancel_negative_btn_title) { dialog, _ ->
//            dialog.dismiss()
//        }
        .setCancelable(false)
        .create()

    dialog.show()
}