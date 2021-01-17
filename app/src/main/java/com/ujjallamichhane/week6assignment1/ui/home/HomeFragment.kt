package com.ujjallamichhane.week6assignment1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ujjallamichhane.week6assignment1.HomeActivity
import com.ujjallamichhane.week6assignment1.R
import com.ujjallamichhane.week6assignment1.adapter.StudentAdapter
import com.ujjallamichhane.week6assignment1.model.Student

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerViewUser: RecyclerView
    private var lstUser = arrayListOf<Student>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerViewUser = view.findViewById(R.id.recyclerViewUser)
//        val textView: TextView = root.findViewById(R.id.text_home)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it

            lstUser = (activity as HomeActivity).lstUser

            val adapter = StudentAdapter(lstUser, view.context)
            recyclerViewUser.layoutManager = LinearLayoutManager(view.context)
            recyclerViewUser.adapter = adapter
        })
        return view
    }
}