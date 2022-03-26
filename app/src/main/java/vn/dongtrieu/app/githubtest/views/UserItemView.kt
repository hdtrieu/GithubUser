package vn.dongtrieu.app.githubtest.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import vn.dongtrieu.app.githubtest.R
import vn.dongtrieu.app.githubtest.utils.bind
import vn.dongtrieu.app.githubtest.utils.onClick

@ModelView(defaultLayout = R.layout.github_user_item)
class UserItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): ConstraintLayout(context, attrs) {
    private val tvUserName: TextView by bind(R.id.tvUserName)
    private val imAvatar: ImageView by bind(R.id.imAvatar)

    @ModelProp
    fun setUserAvatar(imageUrl: String) {
        Glide.with(context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imAvatar)
    }

    @ModelProp
    fun setUserName(name: String) {
        tvUserName.text = name
    }

    @CallbackProp
    fun setOnUserClick(value: Function0<Unit>?) {
        tvUserName.onClick = { value?.invoke() }
    }
}