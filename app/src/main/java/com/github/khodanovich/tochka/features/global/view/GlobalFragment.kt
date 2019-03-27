package com.github.khodanovich.tochka.features.global.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.khodanovich.tochka.R
import com.github.khodanovich.tochka.features.container.LockDrawerLayout
import com.github.khodanovich.tochka.features.global.navigation.NavigationCommand
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class GlobalFragment : Fragment() {

    protected abstract val layoutRes: Int

    protected abstract val viewModel: GlobalViewModel

    private val compositeDisposable by lazy { CompositeDisposable() }

    protected fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
        this.observe(this@GlobalFragment, Observer<T> { value -> observer.invoke(value) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initNavigation()
        initErrorsMessageView()
        super.onCreate(savedInstanceState)
    }

    private fun initNavigation() {
        viewModel.navigationCommands.observe { command ->
            when (command) {
                is NavigationCommand.To -> findNavController().navigate(command.directions)
                is NavigationCommand.Back -> findNavController().popBackStack()
                is NavigationCommand.BackTo -> findNavController().popBackStack(command.destinationId, command.inclusive)
            }
        }
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
}