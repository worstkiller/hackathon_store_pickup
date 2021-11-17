package com.falabella.storepickup.orderlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.falabella.storepickup.R
import com.falabella.storepickup.databinding.FragmentHomeBinding
import com.falabella.storepickup.model.StoreAppointmentModel
import com.falabella.storepickup.orderlist.OrderListAdapter.ItemClickListener
import com.falabella.storepickup.productdetails.ProductDetailsActivity
import com.falabella.storepickup.utils.OrderConstants.BundleKeys
import com.falabella.storepickup.utils.UiUtils

/**
 * A placeholder fragment containing a simple view.
 */
class OrderListFragment : Fragment(), ItemClickListener, SearchListener {

    private lateinit var orderListViewModel: OrderListViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderListViewModel = ViewModelProvider(this).get(OrderListViewModel::class.java).apply {
            getAppointments(arguments?.getBoolean(BundleKeys.KEY_IS_COMPLETED) ?: false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = binding.root

        context?.apply {
            orderListViewModel.orderList.observe(viewLifecycleOwner, {
                when {
                    it.isNullOrEmpty() -> {
                        _binding?.errorLayout?.visibility = View.VISIBLE
                        _binding?.orderListRv?.visibility = View.GONE
                    }
                    else -> {
                        _binding?.orderListRv?.adapter = OrderListAdapter(
                            layoutInflater = layoutInflater,
                            list = it,
                            clickListener = this@OrderListFragment,
                            rowLayout = R.layout.order_list_item
                        )
                    }
                }
                _binding?.orderListRv?.addItemDecoration(UiUtils.getSectionCallback(it, this))
            })
        }
        return root
    }

    companion object {

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(isCompleted: Boolean): OrderListFragment {
            return OrderListFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(BundleKeys.KEY_IS_COMPLETED, isCompleted)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOrderClicked(storeAppointmentModel: StoreAppointmentModel) {
        val intent = Intent(activity, ProductDetailsActivity::class.java)
        intent.putExtras(Bundle().apply {
            putParcelable(BundleKeys.KEY_ORDER_ITEM, storeAppointmentModel)
        })
        activity?.startActivity(intent)
    }

    override fun onSearchWithText(query: String?) {
        (_binding?.orderListRv?.adapter as? OrderListAdapter)?.filter?.filter(query)
    }

}

interface SearchListener {
    fun onSearchWithText(query: String?)
}
