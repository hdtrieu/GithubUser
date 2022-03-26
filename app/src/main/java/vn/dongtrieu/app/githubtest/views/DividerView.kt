package vn.dongtrieu.app.githubtest.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import vn.dongtrieu.app.githubtest.R
import vn.dongtrieu.app.githubtest.utils.bind
import vn.dongtrieu.app.githubtest.utils.dip


@ModelView(defaultLayout = R.layout.common_ui_item_divider)
class DividerView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
  private val separator: View by bind(R.id.vSeparator)

  @JvmOverloads
  @ModelProp
  fun setWidth(value: Int = ViewGroup.LayoutParams.MATCH_PARENT) {
    separator.updateLayoutParams<LayoutParams> {
      width = if (value > 0) {
        dip(context, value)
      } else {
        value
      }
    }
  }

  @JvmOverloads
  @ModelProp(group = "height")
  fun setHeight(value: Int = 8) {
    separator.updateLayoutParams<LayoutParams> {
      height = if (value > 0) {
        dip(context, value)
      } else {
        value
      }
    }
  }

  @JvmOverloads
  @ModelProp(group = "height")
  fun setHeightPx(value: Int = 0) {
    separator.updateLayoutParams<LayoutParams> {
      height = value
    }
  }

  @JvmOverloads
  @ModelProp
  fun setBackgroundColorResource(@ColorRes value: Int? = null) {
    if (value == 0 || value == null) {
      super.setBackground(null)
      return
    }
    super.setBackgroundColor(ContextCompat.getColor(context, value))
  }

  @JvmOverloads
  @ModelProp
  fun setSeparatorBackgroundColor(@ColorRes value: Int? = null) {
    if (value == 0 || value == null) {
      separator.background = null
      return
    }
    separator.setBackgroundColor(ContextCompat.getColor(context, value))
  }

  @JvmOverloads
  @ModelProp
  fun setPaddingLeft(value: Int = 0) {
    updatePadding(
      left = dip(context, value)
    )
  }

  @JvmOverloads
  @ModelProp
  fun setPaddingTop(value: Int = 0) {
    updatePadding(
      top = dip(context, value)
    )
  }

  @JvmOverloads
  @ModelProp
  fun setPaddingRight(value: Int = 0) {
    updatePadding(
      right = dip(context, value)
    )
  }

  @JvmOverloads
  @ModelProp
  fun setPaddingBottom(value: Int = 0) {
    updatePadding(
      bottom = dip(context, value)
    )
  }

  @JvmOverloads
  @ModelProp
  fun setMarginTop(marginTop: Int = 0) {
    updateLayoutParams<MarginLayoutParams> {
      setMargins(leftMargin, dip(context, marginTop), rightMargin, bottomMargin)
    }
  }
}
