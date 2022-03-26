package vn.dongtrieu.app.githubtest.domain.repository

import io.reactivex.Single
import vn.dongtrieu.app.githubtest.domain.entity.GithubUser
import vn.dongtrieu.app.githubtest.domain.entity.SearchResult
import vn.dongtrieu.app.githubtest.domain.entity.UserDetail
import vn.dongtrieu.app.githubtest.domain.entity.UserRepos
import vn.dongtrieu.app.githubtest.service.GithubAPI
import javax.inject.Inject

open class GithubRepositoryImp @Inject constructor(
    private val api: GithubAPI
): GithubRepository  {
    override fun getUsers(): Single<List<GithubUser>> {
        return api.getUsers()
    }

    override fun getUserDetail(name: String): Single<UserDetail> {
        return api.getUserDetail(name)
    }

    override fun searchUser(searchName: String): Single<SearchResult> {
        return api.searchUsers(searchName)
    }

    override fun getUserRepos(name: String): Single<List<UserRepos>> {
        return api.getUserRepo(name)
    }
}