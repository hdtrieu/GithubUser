package vn.dongtrieu.app.githubtest.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import vn.dongtrieu.app.githubtest.domain.entity.GithubUser
import vn.dongtrieu.app.githubtest.domain.entity.SearchResult
import vn.dongtrieu.app.githubtest.domain.entity.UserDetail
import vn.dongtrieu.app.githubtest.domain.entity.UserRepos

interface GithubAPI {
    @GET("/users?per_page=10")
    fun getUsers(): Single<List<GithubUser>>

    @GET("/search/users?per_page=10")
    fun searchUsers(
        @Query("q")
        name: String,
    ): Single<SearchResult>

    @GET("/users/{username}")
    fun getUserDetail(
        @Path("username")
        userName: String,
    ): Single<UserDetail>

    @GET("/users/{username}/repos?per_page=5")
    fun getUserRepo(
        @Path("username")
        userName: String,
    ): Single<List<UserRepos>>
}