package com.adrianlopez.music_recognition.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.adrianlopez.music_recognition.R
import com.adrianlopez.music_recognition.databinding.LoginFragmentBinding
import com.adrianlopez.music_recognition.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

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

        initListeners()
        onObserve()

        return binding.root
    }

    /**Initializes listeners for email and password inputs */
    private fun initListeners() {
        binding.emailInputEditText.addTextChangedListener { viewModel.emailTextChanged(it.toString()) }
        binding.passwordInputEditText.addTextChangedListener { viewModel.passwordTextChanged(it.toString()) }
        binding.passwordInputEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE && binding.loginButton.isEnabled) {
                binding.loginButton.performClick()
                true
            } else {
                false
            }
        }

        binding.signupTextView.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    /**Initializes viewModel liveData observers */
    private fun onObserve(){
        viewModel.isLoginEnabled.observe(viewLifecycleOwner) { isValid ->
            binding.loginButton.isEnabled = isValid
        }
    }

}