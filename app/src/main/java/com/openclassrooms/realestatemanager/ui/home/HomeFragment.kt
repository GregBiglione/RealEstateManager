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
import com.openclassrooms.realestatemanager.adapters.HouseAdapter
import com.openclassrooms.realestatemanager.database.dao.RealEstateManagerDatabase
import com.openclassrooms.realestatemanager.injections.ViewModelFactory
import com.openclassrooms.realestatemanager.model.HouseDataSource
import com.openclassrooms.realestatemanager.repositories.AgentRepository
import com.openclassrooms.realestatemanager.repositories.HouseRepository
import com.openclassrooms.realestatemanager.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var houseRecyclerView: RecyclerView
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        houseRecyclerView = root.findViewById(R.id.house_recycler_view)
        configureViewModel()
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
        val listOfProperty = HouseDataSource.createPropertyDataSet() //<-- Temporary list with no data from database
        houseRecyclerView.adapter = HouseAdapter(listOfProperty)
        houseRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    //----------------------------------------------------------------------------------------------
    //------------------- Configure ViewModel ------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    private fun configureViewModel(){
        val agentDao = RealEstateManagerDatabase.getInstance(requireContext()).agentDao
        val houseDao = RealEstateManagerDatabase.getInstance(requireContext()).houseDao
        val agentRepository = AgentRepository(agentDao)
        val houseRepository = HouseRepository(houseDao)
        val factory = ViewModelFactory(agentRepository, houseRepository)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

}