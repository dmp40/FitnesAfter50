package com.example.fitnesafter50.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnesafter50.R
import com.example.fitnesafter50.adapters.ExerciseAdapter
import com.example.fitnesafter50.databinding.ExerciceListFragmentBinding
import utils.FragmentManager
import utils.MainViewModel


class ExerciseListFragment : Fragment() {

    private lateinit var binding: ExerciceListFragmentBinding
    private lateinit var adapter: ExerciseAdapter
    private val model: MainViewModel by activityViewModels()
    private var ab: ActionBar? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = ExerciceListFragmentBinding.inflate(inflater,container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model.mutableListExercise.observe(viewLifecycleOwner){
            for ( i in 0 until model.getExerciseCount()){
                it[i] = it[i].copy(isDone = true)
            }

            adapter.submitList(it)
        }

    }

    private fun init() = with(binding){
        ab = (activity as AppCompatActivity).supportActionBar// ур 23 10-06
        ab?.title = getString(R.string.exercises )
        adapter = ExerciseAdapter()
        rcView.layoutManager = LinearLayoutManager(activity)
        rcView.adapter = adapter
        bStart.setOnClickListener {
           FragmentManager.setFragment(WaitingFragment.newInstance(),
               activity as AppCompatActivity)
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = ExerciseListFragment()
    }
}