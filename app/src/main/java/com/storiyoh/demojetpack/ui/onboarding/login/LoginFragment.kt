package com.storiyoh.demojetpack.ui.onboarding.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.storiyoh.demojetpack.R
import com.storiyoh.demojetpack.base.BaseFragment
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.splash_fragment.*
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSystemUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        gotoHome.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

    }
    private fun showSystemUI() {
        activity?.window?.decorView?.systemUiVisibility  = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}
