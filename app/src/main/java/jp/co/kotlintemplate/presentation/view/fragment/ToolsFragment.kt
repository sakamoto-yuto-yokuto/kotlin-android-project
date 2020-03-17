package jp.co.kotlintemplate.presentation.view.fragment

import androidx.lifecycle.Observer
import android.content.Context
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding.support.v7.widget.scrollEvents
import dagger.android.support.AndroidSupportInjection
import jp.co.kotlintemplate.R
import jp.co.kotlintemplate.databinding.FragmentToolsBinding
import jp.co.kotlintemplate.extension.addBug
import jp.co.kotlintemplate.presentation.view.adapter.ToolListAdapter
import jp.co.kotlintemplate.presentation.viewmodel.fragment.ToolsFragmentViewModel
import rx.subjects.PublishSubject
import javax.inject.Inject

class ToolsFragment : BaseFragment() {
    @Inject
    lateinit var viewModel: ToolsFragmentViewModel

    private lateinit var binding: FragmentToolsBinding
    private lateinit var adapter: ToolListAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private val loadCompletedStream = PublishSubject.create<Unit>()
    private var isInitialize = false

    companion object {
        @JvmStatic
        fun newInstance() = ToolsFragment()
    }

    override fun onAttach(context: Context) {
        if (!isInitialize) AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (!isInitialize) {
            this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tools, container, false)

            this.layoutManager = LinearLayoutManager(activity)
            this.adapter = ToolListAdapter(this)
            this.viewModel.tools.observe(this, Observer {
                this.adapter.entities.postValue(it)
                this.loadCompletedStream.onNext(Unit)
            })
            this.viewModel.addBug(this.disableObserver.subscriptions)

            this.binding.viewModel = this.viewModel
            this.binding.recyclerView.layoutManager = this.layoutManager
            this.binding.recyclerView.adapter = this.adapter
            this.binding.recyclerView.scrollEvents()
                    .skipUntil(this.loadCompletedStream)
                    .filter { this.layoutManager.itemCount - 1 <= this.layoutManager.findLastVisibleItemPosition() }
                    .take(1)
                    .repeat()
                    .subscribe { this.viewModel.loadNextPage() }

            this.viewModel.getFirstData()
        }
        isInitialize = true
        return this.binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
    }
}
