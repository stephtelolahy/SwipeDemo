package com.creativegames.swipedemo.list

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.creativegames.swipedemo.R
import com.creativegames.swipedemo.databinding.FragmentItemListBinding
import com.creativegames.swipedemo.detail.ItemDetailFragment
import com.creativegames.swipedemo.model.PlaceholderItem

/**
 * A Fragment representing a list of Pings. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a {@link ItemDetailFragment} representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */

class ItemListFragment : Fragment() {

  /**
   * Method to intercept global key events in the
   * item list fragment to trigger keyboard shortcuts
   * Currently provides a toast when Ctrl + Z and Ctrl + F
   * are triggered
   */


  private var _binding: FragmentItemListBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  private lateinit var onTouchListener: RecyclerTouchListener

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentItemListBinding.inflate(inflater, container, false)
    return binding.root

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val recyclerView: RecyclerView = binding.recyclerView
    setupRecyclerView(recyclerView)
    animateOpening()
  }

  private fun animateOpening() {
    Handler(Looper.getMainLooper()).postDelayed({
      onTouchListener.openSwipeOptions(0)
    }, 500)

    Handler(Looper.getMainLooper()).postDelayed({
      onTouchListener.closeVisibleBG(null)
    }, 1500)
  }

  private fun setupRecyclerView(
    recyclerView: RecyclerView
  ) {

    val items: MutableList<PlaceholderItem> = ArrayList()
    for (i in 1..25) {
      items.add(
        PlaceholderItem(
          i.toString(),
          "Article $i",
          "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur nisl urna, aliquam in placerat nec, aliquet eu tortor. Nullam sagittis at nibh vel tincidunt. Suspendisse sodales felis sit amet ipsum bibendum luctus vel consequat ante."
        )
      )
    }

    recyclerView.adapter = RecyclerviewAdapter(items)

    onTouchListener = RecyclerTouchListener(activity, recyclerView)
    onTouchListener
      .setClickable(object : RecyclerTouchListener.OnRowClickListener {
        override fun onRowClicked(position: Int) {
          val item = items[position]
          val bundle = Bundle()
          bundle.putString(
            ItemDetailFragment.ARG_ITEM_ID,
            item.id
          )
          val itemDetailFragmentContainer: View? = view?.findViewById(R.id.item_detail_nav_container)
          if (itemDetailFragmentContainer != null) {
            itemDetailFragmentContainer.findNavController()
              .navigate(R.id.fragment_item_detail, bundle)
          } else {
            findNavController().navigate(R.id.show_item_detail, bundle)
          }
        }

        override fun onIndependentViewClicked(independentViewID: Int, position: Int) {}
      })
      .setSwipeOptionViews(R.id.delete_task)
      .setSwipeable(R.id.rowFG, R.id.rowBG, object : RecyclerTouchListener.OnSwipeOptionsClickListener {

        override fun onSwipeOptionClicked(viewID: Int, position: Int) {
          Toast.makeText(activity, "Ajouté a panier", Toast.LENGTH_SHORT).show()
        }

      })
    recyclerView.addOnItemTouchListener(onTouchListener)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}