package vn.dongtrieu.app.githubtest.utils

import android.view.View
import androidx.annotation.IdRes
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.ModelCollector

fun <T : View?> View.bind(@IdRes id: Int, initializer: (T.() -> Unit)? = null) = lazy(
    LazyThreadSafetyMode.NONE) {
    val view = this@bind.findViewById<View>(id) as T
    initializer?.invoke(view)
    view
}

var View.onClick: Function0<Unit>?
    get() = null
    set(value) {
        val onClick = value?.let {
            View.OnClickListener {
                value.invoke()
            }
        }
        setOnClickListener(onClick)
    }

fun buildSubModel(block: ModelCollector.() -> Unit): List<EpoxyModel<*>> {
    val list = mutableListOf<EpoxyModel<*>>()
    val collector = object : ModelCollector {
        override fun add(model: EpoxyModel<*>) {
            list.add(model)
        }
    }
    block.invoke(collector)
    return list
}