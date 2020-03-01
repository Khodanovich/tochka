package com.github_khodanovich.tochka.core_ui.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.github.khodanovich.tochka.navigation.navigator.Navigator
import com.github_khodanovich.tochka.core_ui.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.inject


abstract class GlobalFragment : Fragment() {

    protected abstract val layoutRes: Int

    protected abstract val viewModel: GlobalViewModel

    private val compositeDisposable by lazy { CompositeDisposable() }

    private val navigator by inject<Navigator>()

    protected fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
        this.observe(this@GlobalFragment, Observer<T> { value -> observer.invoke(value) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initErrorsMessageView()
        super.onCreate(savedInstanceState)
    }

    private fun initErrorsMessageView(){
        viewModel.errorMessageLiveData.observe {
            AlertDialog.Builder(context!!)
                .setTitle(R.string.error)
                .setMessage(it)
                .setPositiveButton(R.string.ok){ dialogInterface, _ -> dialogInterface.dismiss()}
                .create().show()
        }
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(layoutRes, container, false)

    fun lockDrawerLayout() = (activity as LockDrawerLayout).lockDrawerLayout()

    fun unlockDrawerLayout() = (activity as LockDrawerLayout).unlockDrawerLayout()

    protected fun Disposable.onDestroyUnsubscribe() = compositeDisposable.add(this)

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator.attach(this)
    }
}