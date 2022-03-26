package vn.dongtrieu.app.githubtest.domain.interactor

import io.reactivex.Single
import vn.dongtrieu.app.githubtest.domain.entity.SearchResult
import vn.dongtrieu.app.githubtest.domain.repository.GithubRepository
import javax.inject.Inject

class SearchUser @Inject constructor(
    val repository: GithubRepository,
){
    operator fun invoke(searchName: String): Single<SearchResult> {
        return repository.searchUser(searchName)
    }
}