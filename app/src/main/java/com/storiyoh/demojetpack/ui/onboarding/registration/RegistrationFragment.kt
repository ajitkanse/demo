package com.storiyoh.demojetpack.ui.onboarding.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.storiyoh.demojetpack.R
import com.storiyoh.demojetpack.base.BaseFragment
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.registration_fragment.*
import javax.inject.Inject

class RegistrationFragment : BaseFragment() {

    companion object {
        fun newInstance() =
            RegistrationFragment()
    }
    @Inject
    lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.registration_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainHome.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
        }
    }

}
