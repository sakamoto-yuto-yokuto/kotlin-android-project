package jp.co.kotlintemplate.presentation.view.activity

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import dagger.android.AndroidInjection
import jp.co.kotlintemplate.R
import jp.co.kotlintemplate.databinding.ActivityMainBinding
import jp.co.kotlintemplate.domain.model.LoginChallenge
import jp.co.kotlintemplate.domain.model.LoginResult
import jp.co.kotlintemplate.extension.addBug
import jp.co.kotlintemplate.presentation.viewmodel.activity.MainActivityViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var viewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.binding.lifecycleOwner = this
        this.binding.viewModel = this.viewModel

        this.viewModel.loginChallenge.observe(this, Observer<LoginChallenge> {
            this.binding.isLoading = true
        })
        this.viewModel.loginResult.observe(this, Observer<LoginResult> {
            this.binding.isLoading = false
        })
        this.viewModel.skipCommand.subscribe {
            val intent = Intent(this, SecondActivity::class.java)
            this.startActivity(intent)
        }
        this.viewModel.addBug(this.disableObserver.subscriptions)
    }
}
