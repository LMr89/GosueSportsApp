package com.g4.dev.gosuesprortsapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.g4.dev.gosuesprortsapp.core.entity.Usuario
import com.g4.dev.gosuesprortsapp.databinding.FragmentMyDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentMyDetailsBinding? = null
    lateinit var  detailsViewModel: DetailsViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        detailsViewModel =
            ViewModelProvider(this).get(DetailsViewModel::class.java)

        _binding = FragmentMyDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupObservers()

        return root
    }


    override fun onResume() {
        super.onResume()
        detailsViewModel.getUserDetails()
    }
    private fun setupObservers(){
        detailsViewModel.userResponse.observe(viewLifecycleOwner){
            showResponse(it)
        }
        detailsViewModel.isLoading.observe(viewLifecycleOwner){
            _binding!!.pgLoading.isVisible = it
        }
    }


    private fun showResponse(user :Usuario){
        with(_binding!!){
            etName.setText(user.nombre)
            etLastName.setText(user.apellido)
            etDni.setText(user.dni)
            etDireccion.setText(user.direccion)
            etTelefono.setText(user.telefono)
            etNomUsuario.setText(user.nomUsuario)
            etEmail.setText(user.correo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}