package vn.dongtrieu.app.githubtest.utils

import android.view.View

interface FragmentViewLifecycle {
    fun onCreateView(view: View) {
    }

    fun onViewCreated(view: View) {
    }

    fun onStart() {
    }

    fun onResume() {
    }

    fun onPause() {
    }

    fun onStop() {
    }

    fun onDestroyView() {
    }
}