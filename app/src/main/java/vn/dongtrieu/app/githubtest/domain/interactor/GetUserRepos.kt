package vn.dongtrieu.app.githubtest.domain.interactor

import io.reactivex.Single
import vn.dongtrieu.app.githubtest.domain.entity.UserRepos
import vn.dongtrieu.app.githubtest.domain.repository.GithubRepository
import javax.inject.Inject

class GetUserRepos @Inject constructor(
    val repository: GithubRepository,
) {
    operator fun invoke(userName: String): Single<List<UserRepos>> {
        return repository.getUserRepos(userName)
    }
}