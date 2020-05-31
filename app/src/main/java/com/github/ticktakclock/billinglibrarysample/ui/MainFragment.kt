package com.github.ticktakclock.billinglibrarysample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.ticktakclock.billinglibrarysample.databinding.FragmentMainBinding
import com.github.ticktakclock.billinglibrarysample.domain.billing.Sku
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by inject()

    private lateinit var binding: FragmentMainBinding
    private lateinit var coinController: CoinController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        coinController = CoinController(
            onClickCoin = viewModel::onClickCoin
        )
        binding.recyclerView.setController(coinController)
        lifecycle.addObserver(viewModel)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.run {
            coins.observe(viewLifecycleOwner, coinController.coinsObserver)
            sku.observe(viewLifecycleOwner, skuObserver)
        }
    }

    private val skuObserver = Observer<Sku.Available> { sku ->
        activity?.let {
            viewModel.startBilling(it, sku)
        }
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }
}