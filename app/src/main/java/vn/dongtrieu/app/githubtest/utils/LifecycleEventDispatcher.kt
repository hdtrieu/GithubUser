package vn.dongtrieu.app.githubtest.utils

import android.view.View

interface LifecycleEventDispatcher {
  fun dispatchOnCreateView(view: View)

  fun dispatchOnViewCreated(view: View)

  fun dispatchOnStart()

  fun dispatchOnResume()

  fun dispatchOnPause()

  fun dispatchOnStop()

  fun dispatchOnDestroyView()

  fun addFragmentViewLifecycle(view: View?, vararg fragmentViewLifecycles: FragmentViewLifecycle)
}

class LifecycleEventDispatcherImpl : LifecycleEventDispatcher {
  private var lastLifeCycleEvent: FragmentViewLifecycleEvent? = null

  private val fragmentViewLifecycleSet = mutableSetOf<FragmentViewLifecycle>()

  override fun dispatchOnCreateView(view: View) {
    dispatchLifecycleEvent(FragmentViewLifecycleEvent.ON_CREATE_VIEW, view)
  }

  override fun dispatchOnViewCreated(view: View) {
    dispatchLifecycleEvent(FragmentViewLifecycleEvent.ON_VIEW_CREATED, view)
  }

  override fun dispatchOnStart() {
    dispatchLifecycleEvent(FragmentViewLifecycleEvent.ON_START, null)
  }

  override fun dispatchOnResume() {
    dispatchLifecycleEvent(FragmentViewLifecycleEvent.ON_RESUME, null)
  }

  override fun dispatchOnPause() {
    dispatchLifecycleEvent(FragmentViewLifecycleEvent.ON_PAUSE, null)
  }

  override fun dispatchOnStop() {
    dispatchLifecycleEvent(FragmentViewLifecycleEvent.ON_STOP, null)
  }

  override fun dispatchOnDestroyView() {
    dispatchLifecycleEvent(FragmentViewLifecycleEvent.ON_DESTROY_VIEW, null)
  }

  private fun dispatchLifecycleEvent(event: FragmentViewLifecycleEvent, view: View?) {
    fragmentViewLifecycleSet.forEach {
      it.notifyLifecycleEvent(event, view)
    }
    lastLifeCycleEvent = event
  }

  override fun addFragmentViewLifecycle(view: View?, vararg fragmentViewLifecycles: FragmentViewLifecycle) {
    fragmentViewLifecycles.forEach {
      addFragmentViewLifecycle(it, view)
    }
  }

  private fun addFragmentViewLifecycle(fragmentViewLifecycle: FragmentViewLifecycle, view: View?) {
    if (fragmentViewLifecycleSet.contains(fragmentViewLifecycle)) return

    FragmentViewLifecycleEvent.values().forEach { event ->
      lastLifeCycleEvent?.let { lastEvent ->
        if (event.value <= lastEvent.value) {
          fragmentViewLifecycle.notifyLifecycleEvent(event, view)
        }
      }
    }
    fragmentViewLifecycleSet.add(fragmentViewLifecycle)
  }
}

fun FragmentViewLifecycle.notifyLifecycleEvent(
  event: FragmentViewLifecycleEvent,
  view: View?,
) {
  when (event) {
    FragmentViewLifecycleEvent.ON_CREATE_VIEW -> view?.let { onCreateView(it) }
    FragmentViewLifecycleEvent.ON_VIEW_CREATED -> view?.let { onViewCreated(it) }
    FragmentViewLifecycleEvent.ON_START -> onStart()
    FragmentViewLifecycleEvent.ON_RESUME -> onResume()
    FragmentViewLifecycleEvent.ON_PAUSE -> onPause()
    FragmentViewLifecycleEvent.ON_DESTROY_VIEW -> onDestroyView()
  }
}

enum class FragmentViewLifecycleEvent(val value: Int) {
  ON_CREATE_VIEW(0),
  ON_VIEW_CREATED(1),
  ON_START(2),
  ON_RESUME(3),
  ON_PAUSE(4),
  ON_STOP(5),
  ON_DESTROY_VIEW(6),
}
