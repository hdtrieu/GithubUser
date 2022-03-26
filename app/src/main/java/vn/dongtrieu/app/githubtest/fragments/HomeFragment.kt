package vn.dongtrieu.app.githubtest.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import vn.dongtrieu.app.githubtest.R
import vn.dongtrieu.app.githubtest.home.HomeViewModel
import vn.dongtrieu.app.githubtest.home.HomeViews
import vn.dongtrieu.app.githubtest.utils.LifecycleEventDispatcher
import vn.dongtrieu.app.githubtest.utils.LifecycleEventDispatcherImpl
import vn.dongtrieu.app.githubtest.utils.observeInflate
import javax.inject.Inject

class HomeFragment:
    BaseFragment(),
    LifecycleEventDispatcher by LifecycleEventDispatcherImpl()
{
    @Inject
    lateinit var homeViews: HomeViews
    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        dispatchOnCreateView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatchOnViewCreated(view)
        homeViews.observeInflate(this@HomeFragment, view)
    }
}