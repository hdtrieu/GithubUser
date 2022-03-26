package vn.dongtrieu.app.githubtest.home

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import vn.dongtrieu.app.githubtest.FragmentScope
import vn.dongtrieu.app.githubtest.R
import vn.dongtrieu.app.githubtest.fragments.HomeFragment
import vn.dongtrieu.app.githubtest.utils.buildSubModel
import vn.dongtrieu.app.githubtest.views.dividerView
import vn.dongtrieu.app.githubtest.views.userItemView
import javax.inject.Inject
import javax.inject.Provider

@FragmentScope
class HomeController @Inject constructor(
    private val viewModel: Provider<HomeViewModel>,
): EpoxyController() {

    override fun buildModels() {
        val listUser = viewModel.get().listGithubUser.value
        if (listUser.isNullOrEmpty()) {
            return
        }
        val size = listUser.size - 1

        listUser.forEachIndexed { index, githubUser ->
            userItemView {
                id(githubUser.id)
                userAvatar(githubUser.avatarUrl.orEmpty())
                userName(githubUser.login.orEmpty())
                onUserClick {
                    this@HomeController.viewModel.get().toUserDetail(githubUser.login.orEmpty())
                }
            }

            if (index != size) {
                dividerView {
                    id(index)
                    height(1)
                    separatorBackgroundColor(R.color.gray_20)
                }
            }
        }
    }
}