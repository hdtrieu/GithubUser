package vn.dongtrieu.app.githubtest.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import dagger.android.support.AndroidSupportInjection
import vn.dongtrieu.app.githubtest.R
import vn.dongtrieu.app.githubtest.detail.UserDetailViewModel
import vn.dongtrieu.app.githubtest.detail.UserDetailViews
import vn.dongtrieu.app.githubtest.utils.LifecycleEventDispatcher
import vn.dongtrieu.app.githubtest.utils.LifecycleEventDispatcherImpl
import vn.dongtrieu.app.githubtest.utils.observeInflate
import javax.inject.Inject

class SearchFragment:
    BaseFragment(),
    LifecycleEventDispatcher by LifecycleEventDispatcherImpl() {

    private val args: SearchFragmentArgs by navArgs()

    @Inject
    lateinit var userDetailViewModel: UserDetailViewModel
    @Inject
    lateinit var userDetailViews: UserDetailViews

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_fragment, container, false)
        dispatchOnCreateView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatchOnViewCreated(view)

        userDetailViews.observeInflate(this@SearchFragment, view)
        userDetailViewModel.getUserDetail(args.userName)
    }
}