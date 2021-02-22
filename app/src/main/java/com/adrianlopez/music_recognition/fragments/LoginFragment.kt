package com.adrianlopez.music_recognition.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.adrianlopez.music_recognition.databinding.LoginFragmentBinding
import com.adrianlopez.music_recognition.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**login button is enabled based in the collection of isLoginEnabled flow */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initListeners()

        lifecycleScope.launchWhenStarted {
            viewModel.isLoginEnabled.collect { isLoginEnabled ->
                binding.loginButton.isEnabled = isLoginEnabled
            }
        }
    }

    /**Initializes listeners for email and password inputs */
    private fun initListeners() {
        binding.emailInputEditText.addTextChangedListener { viewModel.emailTextChanged(it.toString()) }
        binding.passwordInputEditText.addTextChangedListener { viewModel.passwordTextChanged(it.toString()) }
    }

}