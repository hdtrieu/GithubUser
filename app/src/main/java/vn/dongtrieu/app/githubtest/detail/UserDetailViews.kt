package vn.dongtrieu.app.githubtest.detail

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyRecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.gson.Gson
import vn.dongtrieu.app.githubtest.FragmentScope
import vn.dongtrieu.app.githubtest.R
import vn.dongtrieu.app.githubtest.fragments.SearchFragment
import vn.dongtrieu.app.githubtest.utils.FragmentViewLifecycle
import vn.dongtrieu.app.githubtest.utils.LazyInflateViews
import vn.dongtrieu.app.githubtest.utils.bind
import javax.inject.Inject

@FragmentScope
class UserDetailViews @Inject constructor(
    val fragment: SearchFragment,
    private val userDetailViewModel: UserDetailViewModel
): FragmentViewLifecycle, LazyInflateViews {

    @Inject
    lateinit var userDetailController: UserDetailController

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        val lifecycleOwner = fragment.viewLifecycleOwner
        val holder = ViewHolder(view)
        holder.listRepos.setController(userDetailController)
        userDetailViewModel.apply {
            userDetailLiveData.observe(lifecycleOwner) {
                holder.apply {
                    tvUserName.text = it.name.orEmpty()
                    tvUserId.text = it.login.orEmpty()
                    tvCompanyName.text = it.company.orEmpty()
                    tvLocationName.text = it.location.orEmpty()
                    tvBlog.text = it.bio.orEmpty()
                    Glide.with(fragment.requireContext())
                        .load(it.avatarUrl.orEmpty())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imAvatar)
                }

                getUserRepos(it.login.orEmpty())
            }

            userReposLiveData.observe(lifecycleOwner) {
                Log.d("HDT", Gson().toJson(it))
                userDetailController.requestModelBuild()
            }
        }
    }

    override fun onDestroyView() {
        userDetailController.cancelPendingModelBuild()
    }

    override fun observeInflateCondition(view: View, onShouldInflate: () -> Unit) {
        onShouldInflate()
    }

    private class ViewHolder(view: View) {
        val imAvatar: ImageView by view.bind(R.id.imAvatar)
        val tvUserName: TextView by view.bind(R.id.tvUserName)
        val tvUserId: TextView by view.bind(R.id.tvUserId)
        val tvCompanyName: TextView by view.bind(R.id.tvCompanyName)
        val tvLocationName: TextView by view.bind(R.id.tvLocationName)
        val tvBlog: TextView by view.bind(R.id.tvBlog)
        val listRepos: EpoxyRecyclerView by view.bind(R.id.list_user_repos)
    }
}