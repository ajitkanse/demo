package com.storiyoh.demojetpack.ui.onboarding.splash

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController


import com.storiyoh.demojetpack.base.BaseFragment
import kotlinx.android.synthetic.main.splash_fragment.*
import javax.inject.Inject
import android.content.Intent
import androidx.core.os.HandlerCompat.postDelayed
import android.R
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity




class SplashFragment : BaseFragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    @Inject
    lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view1 = inflater.inflate(com.storiyoh.demojetpack.R.layout.splash_fragment, container, false)

        Log.d("SplashFragment","onCreateView")



        return view1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SplashFragment","onCreate")


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
       // Log.d("SplashFragment","onAttach")
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
       // Log.d("SplashFragment","onAttachFragment")
    }

    override fun onDetach() {
        super.onDetach()
      //  Log.d("SplashFragment",viewModel.str)
    }

    override fun onDestroyView() {
        super.onDestroyView()
      //  Log.d("SplashFragment","onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
       // Log.d("SplashFragment","onDestroy")
    }

    override fun onResume() {
        super.onResume()
       // Log.d("SplashFragment","onResume")

      //  (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    override fun onPause() {
        super.onPause()
      //  Log.d("SplashFragment","onPause")
    }

    override fun onLowMemory() {
        super.onLowMemory()
     //   Log.d("SplashFragment","onLowMemory")
    }

    override fun onStart() {
        super.onStart()
      //  Log.d("SplashFragment","onStart")
    }

    override fun onStop() {
        super.onStop()
      //  Log.d("SplashFragment","onStop")
       // (activity as AppCompatActivity).supportActionBar!!.show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       // viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        Log.d("SplashFragment","onActivityCreated")


        Handler().postDelayed(Runnable {
            view?.findNavController()?.navigate(com.storiyoh.demojetpack.R.id.action_splashFragment_to_loginFragment)
        }, 3000)

       /* activity?.window?.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
          View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
*/
        hideSystemUI()

    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        activity?.window?.decorView?.systemUiVisibility  = (
                View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        activity?.window?.decorView?.systemUiVisibility  = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

}
