package vn.dongtrieu.app.githubtest.navigation

import androidx.navigation.fragment.findNavController
import vn.dongtrieu.app.githubtest.FragmentScope
import vn.dongtrieu.app.githubtest.fragments.HomeFragment
import vn.dongtrieu.app.githubtest.fragments.HomeFragmentDirections
import javax.inject.Inject

@FragmentScope
class Navigator @Inject constructor(
    private val fragment: HomeFragment
) {
    fun goUserDetail(userName: String) {
        val direction = HomeFragmentDirections.actionHomeFragmentToSearchFragment(userName)
        fragment.findNavController().navigate(direction)
    }
}