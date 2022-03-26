package vn.dongtrieu.app.githubtest.home

import android.view.View
import androidx.appcompat.widget.SearchView
import com.airbnb.epoxy.EpoxyRecyclerView
import vn.dongtrieu.app.githubtest.R
import vn.dongtrieu.app.githubtest.FragmentScope
import vn.dongtrieu.app.githubtest.fragments.HomeFragment
import vn.dongtrieu.app.githubtest.utils.*
import javax.inject.Inject

@FragmentScope
class HomeViews @Inject constructor(
    private val homeFragment: HomeFragment,
): FragmentViewLifecycle, LazyInflateViews {

    @Inject
    lateinit var homeController: HomeController
    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        val holder = ViewHolder(view)
        val lifecycleOwner = homeFragment.viewLifecycleOwner

        holder.listUserRecyclerView.setController(homeController)
        holder.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    return true
                }
                homeViewModel.searchUser(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
        homeViewModel.listGithubUser.observe(lifecycleOwner) {
            homeController.requestModelBuild()
        }
    }

    override fun onDestroyView() {
        homeController.cancelPendingModelBuild()
    }

    private class ViewHolder(view: View) {
        val searchView: SearchView by view.bind(R.id.searchView)
        val listUserRecyclerView: EpoxyRecyclerView by view.bind(R.id.content_recycler_view)
    }

    override fun observeInflateCondition(view: View, onShouldInflate: () -> Unit) {
        onShouldInflate()
        if (homeViewModel.listGithubUser.value.isNullOrEmpty()) {
            homeViewModel.getGithubUsers()
        }
    }
}