package vn.dongtrieu.app.githubtest.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vn.dongtrieu.app.githubtest.FragmentScope
import vn.dongtrieu.app.githubtest.domain.entity.UserDetail
import vn.dongtrieu.app.githubtest.domain.entity.UserRepos
import vn.dongtrieu.app.githubtest.domain.interactor.GetUserDetail
import vn.dongtrieu.app.githubtest.domain.interactor.GetUserRepos
import javax.inject.Inject

@FragmentScope
class UserDetailViewModel @Inject constructor(
    private val getUserDetail: GetUserDetail,
    private val getUserRepos: GetUserRepos,
): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _userDetailMutableLiveData = MutableLiveData<UserDetail>()
    val userDetailLiveData: LiveData<UserDetail> = _userDetailMutableLiveData

    private val _userReposMutableLiveData = MutableLiveData<List<UserRepos>>()
    val userReposLiveData: LiveData<List<UserRepos>> = _userReposMutableLiveData

    fun getUserDetail(userName: String) {
        compositeDisposable.add(
            getUserDetail.invoke(userName)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    it -> _userDetailMutableLiveData.postValue(it)
                }
        )
    }

    fun getUserRepos(userName: String) {
        compositeDisposable.add(
            getUserRepos.invoke(userName)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        _userReposMutableLiveData.postValue(it)
                    }   ,
                    {
                        Log.d("HDT", it.message.toString())
                    }
                )

        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}