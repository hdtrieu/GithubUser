package vn.dongtrieu.app.githubtest.views

/**
 * Created by HuyND on 8/10/2017.
 */

interface BaseView {
    fun showLoadingDialog()

    fun dismissLoadingDialog()

    fun showFailedDialog(errorMessage: String?)
}
