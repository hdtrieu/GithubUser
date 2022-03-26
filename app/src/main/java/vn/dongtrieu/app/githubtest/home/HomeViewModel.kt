package vn.dongtrieu.app.githubtest.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vn.dongtrieu.app.githubtest.FragmentScope
import vn.dongtrieu.app.githubtest.domain.entity.GithubUser
import vn.dongtrieu.app.githubtest.domain.interactor.GetGithubUser
import vn.dongtrieu.app.githubtest.domain.interactor.SearchUser
import vn.dongtrieu.app.githubtest.navigation.Navigator
import javax.inject.Inject

@FragmentScope
class HomeViewModel @Inject constructor(
    private val getGithubUser: GetGithubUser,
    private val searchUser: SearchUser,
    private val navigator: Navigator,
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _listGithubUser = MutableLiveData<List<GithubUser>>()
    val listGithubUser: LiveData<List<GithubUser>> = _listGithubUser

    fun getGithubUsers() {
        compositeDisposable.add(
            getGithubUser.invoke()
            .subscribeOn(Schedulers.io())
            .subscribe { it ->
                _listGithubUser.postValue(it)
            }
        )
    }

    fun searchUser(searchText: String) {
        compositeDisposable.add(
            searchUser.invoke(searchText)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    it -> _listGithubUser.postValue(it.items)
                }
        )
    }

    fun toUserDetail(userName: String) {
        if (userName.isEmpty()) {
            return
        }
        navigator.goUserDetail(userName)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}