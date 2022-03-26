package vn.dongtrieu.app.githubtest.domain.repository

import io.reactivex.Single
import vn.dongtrieu.app.githubtest.domain.entity.GithubUser
import vn.dongtrieu.app.githubtest.domain.entity.SearchResult
import vn.dongtrieu.app.githubtest.domain.entity.UserDetail
import vn.dongtrieu.app.githubtest.domain.entity.UserRepos

interface GithubRepository {
    fun getUsers(): Single<List<GithubUser>>

    fun getUserDetail(name: String): Single<UserDetail>

    fun searchUser(searchName: String): Single<SearchResult>

    fun getUserRepos(name: String): Single<List<UserRepos>>
}