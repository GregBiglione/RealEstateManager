package com.openclassrooms.realestatemanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.adapters.PropertyAdapter
import com.openclassrooms.realestatemanager.model.PropertyDataSource
import com.openclassrooms.realestatemanager.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var propertyRecyclerView: RecyclerView
    private lateinit var propertyViewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        propertyRecyclerView = root.findViewById(R.id.property_recycler_view)
        //initPropertyViewModel()
        configurePropertyRecyclerView()
        return root
    }

    //----------------------------------------------------------------------------------------------
    //------------------- Configure properties list ------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //------------------- Configure recyclerview ---------------------------------------------------
    //----------------------------------------------------------------------------------------------

    private fun configurePropertyRecyclerView(){
        //val listOfProperty = da //<-- Temporary empty list if not app will crash
        val listOfProperty = PropertyDataSource.createPropertyDataSet()
        propertyRecyclerView.adapter = PropertyAdapter(listOfProperty)
        propertyRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    //----------------------------------------------------------------------------------------------
    //------------------- Initialize PropertyViewModel ---------------------------------------------
    //----------------------------------------------------------------------------------------------

}