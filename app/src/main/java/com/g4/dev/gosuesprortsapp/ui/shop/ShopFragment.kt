package com.g4.dev.gosuesprortsapp.ui.shop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.g4.dev.gosuesprortsapp.GosueSportApplicationClass
import com.g4.dev.gosuesprortsapp.MainActivity
import com.g4.dev.gosuesprortsapp.R
import com.g4.dev.gosuesprortsapp.adapter.CategoryAdapter
import com.g4.dev.gosuesprortsapp.adapter.OnCategoryClickListener
import com.g4.dev.gosuesprortsapp.adapter.OnTransferProductListener
import com.g4.dev.gosuesprortsapp.adapter.ProductAdapter
import com.g4.dev.gosuesprortsapp.core.entity.Product
import com.g4.dev.gosuesprortsapp.data.network.repository.sale.SaleTemporal
import com.g4.dev.gosuesprortsapp.databinding.FragmentComprasBinding
import com.g4.dev.gosuesprortsapp.ui.product.ProductDetailsFragment
import com.g4.dev.gosuesprortsapp.ui.shop.paymentDialog.PaymentViewModel
import com.g4.dev.gosuesprortsapp.ui.shop.paymentDialog.TemporalPaymentData
import com.g4.dev.gosuesprortsapp.util.messages.MessageType
import com.g4.dev.gosuesprortsapp.util.messages.MessageUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ShopFragment : Fragment() , OnCategoryClickListener,OnTransferProductListener {

    private var _binding: FragmentComprasBinding? = null
    lateinit var showViewModel: ShopViewModel



    var FIRST_ID_CATEGORY_TO_INIT = 4

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        showViewModel =
            ViewModelProvider(this).get(ShopViewModel::class.java)

        _binding = FragmentComprasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUpRecyclerView()
        setUpListener()
        return root
    }

    private fun setUpRecyclerView(){
        with(binding.recCategories){
            layoutManager = LinearLayoutManager(requireActivity().applicationContext,
                LinearLayoutManager.HORIZONTAL, false)
        }

        with(binding.recProducts){
            layoutManager = LinearLayoutManager(requireContext().applicationContext)
        }

    }
    private fun setUpListener(){
        binding.fabConfirmarCompra.setOnClickListener{
            if (SaleTemporal.productsForSale.isEmpty()){
                MessageUtil.sendMessage(binding.root,"Porfavor agrega un productos",MessageType.WARNING)

            }else{
                requireActivity()
                    .findNavController(R.id.nav_host_fragment_content_main)
                    .navigate(R.id.nav_confirm_sale)
            }
        }

    }
    private fun setUpObservers(){
        showViewModel.categoriesList.observe(viewLifecycleOwner){
            binding.recCategories.adapter = CategoryAdapter(it, this)

        }
        showViewModel.productsList.observe(viewLifecycleOwner){
            binding.recProducts.adapter = ProductAdapter(it, this)
        }
        showViewModel.isLoading.observe(viewLifecycleOwner){showAlert->
                binding.productPgBar.isVisible = showAlert
        }

    }

    override fun onResume() {
        super.onResume()
        setUpObservers()
        showViewModel.getAllCategories()

        if (TemporalPaymentData.PAYMENT_SUCCESSFULLY){
            //MessageUtil.sendMessage(binding.root, "Venta Exitosa", MessageType.SUCCESS)
            TemporalPaymentData.PAYMENT_SUCCESSFULLY = false
        }

    }

    override fun onStart() {
        super.onStart()
        Log.i("FIRST ID CATEGORY", FIRST_ID_CATEGORY_TO_INIT.toString())
        showViewModel.getProductsByCategory(FIRST_ID_CATEGORY_TO_INIT)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Este metodo es el encargado de transferir el id de la categoria que se dio
     * desde la clase categoria Adapter todo esto para hacer al busqueda de los productos por categoria
     */
    override fun onTransferIdForProducts(idCategory: Int) {
        Log.i("ID CATEGORIA",idCategory.toString())
           showViewModel.getProductsByCategory(idCategory)
    }




    override fun onTransfer(product: Product) {
        SaleTemporal.currentProductSelected = product
        requireActivity()
            .findNavController(R.id.nav_host_fragment_content_main)
            .navigate(R.id.nav_product_details)
    }
}