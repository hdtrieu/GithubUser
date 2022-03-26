package vn.dongtrieu.app.githubtest.utils

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import vn.dongtrieu.app.githubtest.R


class FlowUtils {
    companion object {
        val instance = FlowUtils()
    }

    var mProgressDialog: ProgressDialog? = null

    fun showLoadingDialog(context: Context) {
        if (!isLoadingDialogShowing()) {
            mProgressDialog = ProgressDialog(context)
            mProgressDialog?.run {
                setMessage(context.getString(R.string.loading_string))
                setCanceledOnTouchOutside(false)
                show()
            }
        }
    }

    fun dismissLoadingDialog() {
        if (isLoadingDialogShowing()) {
            try {
                mProgressDialog?.dismiss()
                mProgressDialog = null
            } catch (ex: IllegalArgumentException) {
                ex.printStackTrace()
            }
        }
    }

    fun showAlert(context: Context, title: String, message: String) {
        AlertDialog.Builder(context).let {
            it.setTitle(title)
            it.setMessage(message)
            it.setNeutralButton(context.getString(R.string.ok), null)
            it.create()
        }.show()
    }

    private fun isLoadingDialogShowing() = mProgressDialog?.isShowing ?: false
}
