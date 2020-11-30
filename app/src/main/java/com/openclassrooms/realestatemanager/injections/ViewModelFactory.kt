package com.openclassrooms.realestatemanager.injections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.openclassrooms.realestatemanager.repositories.AgentRepository
import com.openclassrooms.realestatemanager.repositories.PropertyRepository
import com.openclassrooms.realestatemanager.viewmodel.MainViewModel

class ViewModelFactory(private val agentRepository: AgentRepository,
                       private val propertyRepository: PropertyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(agentRepository, propertyRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}