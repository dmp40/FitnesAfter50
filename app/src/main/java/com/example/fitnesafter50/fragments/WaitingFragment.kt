package com.example.fitnesafter50.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fitnesafter50.R
import utils.FragmentManager
import utils.TimeUtils
import com.example.fitnesafter50.databinding.WaitingFragmentBinding as WaitingFragmentBinding1

const val COUNT_DOWN_TIME = 6000L //время ожидания  перед стартом
class WaitingFragment : Fragment() {
    private var ab: ActionBar? = null// ур23 10-42

    // урок 16 время 9;20
    private lateinit var binding: com.example.fitnesafter50.databinding.WaitingFragmentBinding

    private lateinit var timer: CountDownTimer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = WaitingFragmentBinding1.inflate(inflater,container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ab = (activity as AppCompatActivity).supportActionBar// ур 23 10-59
        ab?.title = getString(R.string.waiting)
        binding.pBar.max = COUNT_DOWN_TIME.toInt()
        startTimer()// запуск таймера

    }
// 1000 скачок отрисовки  Прогресс бара 1 сек .чтобы плавно надо 1
    private fun startTimer() = with(binding){
        timer = object  : CountDownTimer(COUNT_DOWN_TIME, 20){
            override fun onTick(restTime: Long) {
                tvTimer.text = TimeUtils.getTime(restTime)
                pBar.progress = restTime.toInt()
            }// ниже вспл надпись  полсе оконч таймера  для примера

            override fun onFinish() {
                FragmentManager.setFragment(ExercisesFragment.newInstance(),
                activity as AppCompatActivity)
            }


        }.start()
    }
//урок 16  12 минута
    override fun onDetach() {
        super.onDetach()
        timer.cancel()
    }
    companion object {

        @JvmStatic
        fun newInstance() = WaitingFragment()
    }
}