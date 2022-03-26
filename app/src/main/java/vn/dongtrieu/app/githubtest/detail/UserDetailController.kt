package vn.dongtrieu.app.githubtest.detail

import android.util.Log
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import vn.dongtrieu.app.githubtest.FragmentScope
import vn.dongtrieu.app.githubtest.utils.buildSubModel
import vn.dongtrieu.app.githubtest.views.repoItemView
import javax.inject.Inject

@FragmentScope
class UserDetailController @Inject constructor(
    private val viewModel: UserDetailViewModel
): EpoxyController() {
    override fun buildModels() {
        val repos = viewModel.userReposLiveData.value

        if (repos.isNullOrEmpty()) {
            Log.d("HDT", "return")
            return
        }

        val carouseModels = buildSubModel {
            repos.forEachIndexed { _, userRepos ->
                repoItemView {
                    id(userRepos.id)
                    repoName(userRepos.name.orEmpty())
                    repoDescription(userRepos.description.orEmpty())
                    starCount(userRepos.stargazersCount ?: 0)
                    language(userRepos.language.orEmpty())
                }
            }
        }

        carousel {
            id("repo")
            models(carouseModels)
        }
    }
}