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
import com.example.fitnesafter50.adapters.DayModel
import com.example.fitnesafter50.adapters.DaysAdapter
import com.example.fitnesafter50.adapters.ExerciseModel
import com.example.fitnesafter50.databinding.FragmentDaysBinding
import utils.FragmentManager
import utils.MainViewModel


class DaysFragment : Fragment(), DaysAdapter.Listener {

    private lateinit var binding: FragmentDaysBinding
    private val model: MainViewModel by activityViewModels()
    private var ab: ActionBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDaysBinding.inflate(inflater,container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }


    private fun initRcView() = with(binding) {
        val adapter = DaysAdapter(this@DaysFragment)
        ab = (activity as AppCompatActivity).supportActionBar // ур 23 9-00
        ab?.title = getString(R.string.days)//action bar в окне списка дней
        rcViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        rcViewDays.adapter =adapter
        adapter.submitList(fillDaysArray())
    }


   private fun fillDaysArray(): ArrayList<DayModel>{
       val tArray = ArrayList<DayModel>()
       resources.getStringArray(R.array.Day_exercise).forEach {
            tArray.add(DayModel(it,  0,false))
       }
       return tArray
   }
    private fun   fillExerciseList(day: DayModel){
        val tempList =  ArrayList<ExerciseModel>()
        day.exercises.split(",").forEach {
            val exerciseList = resources.getStringArray(R.array.exercise)
            val exercise = exerciseList[it.toInt()]
            val exerciseArray = exercise.split( "|")
            tempList.add(ExerciseModel(exerciseArray[0],exerciseArray[1], false, exerciseArray[2]))

        }
        model.mutableListExercise.value = tempList
    }

    companion object {

        @JvmStatic
        fun newInstance() = DaysFragment()
    }

    override fun onClick(day: DayModel) {
        fillExerciseList(day)
        model.currentDay = day.dayNumber
       //FragmentManager.setFragment(ExerciseListFragment.newInstance(),
          // activity as AppCompatActivity)


        FragmentManager.setFragment(ExerciseListFragment.newInstance(),activity as AppCompatActivity)


    }
}