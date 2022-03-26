package vn.dongtrieu.app.githubtest.domain.interactor

import io.reactivex.Single
import vn.dongtrieu.app.githubtest.domain.entity.GithubUser
import vn.dongtrieu.app.githubtest.domain.repository.GithubRepository
import javax.inject.Inject

class GetGithubUser @Inject constructor(
    private val repository: GithubRepository
) {
    operator fun invoke(): Single<List<GithubUser>> {
        return repository.getUsers()
    }
}