package com.falabella.storepickup.orderlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.falabella.storepickup.MainActivity
import com.falabella.storepickup.R
import com.falabella.storepickup.databinding.FragmentHomeBinding
import com.falabella.storepickup.model.StoreAppointmentModel
import com.falabella.storepickup.orderlist.UpcomingOrderListAdapter.ItemClickListener
import com.falabella.storepickup.utils.OrderConstants.BundleKeys
import com.falabella.storepickup.utils.UiUtils

/**
 * A placeholder fragment containing a simple view.
 */
class OrderListFragment : Fragment(), ItemClickListener {

    private lateinit var orderListViewModel: OrderListViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderListViewModel = ViewModelProvider(this).get(OrderListViewModel::class.java).apply {
            setIsCompleted(arguments?.getBoolean(ARG_SECTION_NUMBER) ?: false)
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
                if(orderListViewModel.isCompleted.value == true) {
                    _binding?.orderListRv?.adapter = CompletedOrderListAdapter(layoutInflater, it,
                        this@OrderListFragment, R.layout.completed_order_list_item)
                } else {
                    _binding?.orderListRv?.adapter = UpcomingOrderListAdapter(layoutInflater, it, this@OrderListFragment, R.layout.upcoming_order_list_item)
                }
                _binding?.orderListRv?.addItemDecoration(UiUtils.getSectionCallback(it, this))
            })
        }
        return root
    }

    companion object {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): OrderListFragment {
            return OrderListFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_SECTION_NUMBER, sectionNumber/2 == 1)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOrderClicked(orderId: String, storeAppointmentModel: StoreAppointmentModel) {
        val intent = Intent(activity, MainActivity::class.java)
        intent.putExtras(Bundle().apply {
            putString(BundleKeys.KEY_ORDER_ID, orderId)
            putParcelable(BundleKeys.KEY_ORDER_ITEM, storeAppointmentModel)
        })
        activity?.startActivity(intent)
    }
}