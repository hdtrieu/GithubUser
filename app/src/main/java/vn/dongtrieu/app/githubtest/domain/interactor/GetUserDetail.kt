package vn.dongtrieu.app.githubtest.domain.interactor

import io.reactivex.Single
import vn.dongtrieu.app.githubtest.domain.entity.UserDetail
import vn.dongtrieu.app.githubtest.domain.repository.GithubRepository
import javax.inject.Inject

class GetUserDetail @Inject constructor(
    val repository: GithubRepository,
){
    operator fun invoke(userName: String): Single<UserDetail> {
        return repository.getUserDetail(userName)
    }
}