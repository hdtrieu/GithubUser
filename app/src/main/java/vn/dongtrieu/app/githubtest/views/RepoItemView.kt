package vn.dongtrieu.app.githubtest.views

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import vn.dongtrieu.app.githubtest.R
import vn.dongtrieu.app.githubtest.utils.bind
import kotlin.math.ln
import kotlin.math.pow

@ModelView(defaultLayout = R.layout.user_repo_item)
class RepoItemView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): ConstraintLayout(context, attrs) {
    private val tvRepoName: TextView by bind(R.id.tvRepoName)
    private val tvRepoDescription: TextView by bind(R.id.tvRepoDescription)
    private val tvStarCount: TextView by bind(R.id.tvStarCount)
    private val tvLanguage: TextView by bind(R.id.tvLanguage)

    @ModelProp
    fun setRepoName(repoName: String) {
        tvRepoName.text = repoName
    }

    @ModelProp
    fun setRepoDescription(description: String) {
        tvRepoDescription.text = description
    }

    @ModelProp
    fun setLanguage(language: String) {
        tvLanguage.text = language
    }

    @ModelProp
    fun setStarCount(count: Int) {
        if (count < 1000) {
            tvStarCount.text = count.toString()
            return
        }
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        tvStarCount.text = String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
    }
}