package vn.dongtrieu.app.githubtest.utils

import android.view.View

interface LazyInflateViews {
  fun observeInflateCondition(view: View, onShouldInflate: () -> Unit)
}

fun <T> T.observeInflate(
  lifecycleEventDispatcher: LifecycleEventDispatcher,
  view: View
) where T : FragmentViewLifecycle, T : LazyInflateViews {
  observeInflateCondition(view) {
    lifecycleEventDispatcher.addFragmentViewLifecycle(view, this)
  }
}
