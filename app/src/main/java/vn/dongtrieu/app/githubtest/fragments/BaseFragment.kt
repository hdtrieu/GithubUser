package vn.dongtrieu.app.githubtest.fragments

import androidx.fragment.app.Fragment
import vn.dongtrieu.app.githubtest.R
import vn.dongtrieu.app.githubtest.utils.FlowUtils
import vn.dongtrieu.app.githubtest.views.BaseView

open class BaseFragment : Fragment(), BaseView {

    override fun dismissLoadingDialog() {
        FlowUtils.instance.dismissLoadingDialog()
    }

    override fun showLoadingDialog() {
        context?.run {
            FlowUtils.instance.showLoadingDialog(this)
        }
    }

    override fun showFailedDialog(errorMessage: String?) {
        context?.run {
            val message = errorMessage ?: this.getString(R.string.failed_message)
            FlowUtils.instance.showAlert(this, this.getString(R.string.error), message)
        }
    }

}
