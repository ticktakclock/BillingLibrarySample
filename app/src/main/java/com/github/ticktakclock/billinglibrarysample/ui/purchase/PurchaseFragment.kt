package com.github.ticktakclock.billinglibrarysample.ui.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.billingclient.api.Purchase
import com.github.ticktakclock.billinglibrarysample.databinding.FragmentPurchaseBinding
import org.koin.android.ext.android.inject
import timber.log.Timber

class PurchaseFragment : Fragment() {

    private val viewModel: PurchaseViewModel by inject()

    private lateinit var binding: FragmentPurchaseBinding
    private lateinit var purchaseController: PurchaseController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPurchaseBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        purchaseController = PurchaseController(
            onClickPurchase = viewModel::onClickPurchase
        )
        binding.recyclerView.setController(purchaseController)
        lifecycle.addObserver(viewModel)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.run {
            purchases.observe(viewLifecycleOwner, purchaseController.purchaseObserver)
            consume.observe(viewLifecycleOwner, consumeObserver)
        }
    }

    private val consumeObserver = Observer<Purchase> { purchase ->
        context?.let { context ->
            val dialog = AlertDialog.Builder(context)
                .setMessage("消費しますか？")
                .setPositiveButton("消費する") { _, _ ->
                    Timber.d("select positive")
                    viewModel.onApplyConsume(purchase)
                }
                .setNegativeButton("やめる") { _, _ ->
                    Timber.d("select negative")
                }
                .create()
            dialog.show()
        }
    }
}