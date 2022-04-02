package com.example.fitnesafter50.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fitnesafter50.R
import com.example.fitnesafter50.adapters.ExerciseModel
import com.example.fitnesafter50.databinding.ExerciseBinding
import pl.droidsonroids.gif.GifDrawable
import utils.FragmentManager
import utils.MainViewModel
import utils.TimeUtils


class ExercisesFragment : Fragment() {
 private  var timer: CountDownTimer? = null
 private lateinit var binding: ExerciseBinding
 private val model: MainViewModel by activityViewModels()
 // счетчик упражнений
 private var exerciseCounter = 0
 //обр к массиву с упр для опр мах колва
 private var exList: ArrayList<ExerciseModel>? = null
    private var ab: ActionBar? = null //cчетчик упр ур 23 1-19

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = ExerciseBinding.inflate(inflater,container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //lesson 25 1-48 запись в лог
        //Log.d("MyLog", "Counter ${model.getPref(model.currentDay.toString())}")
        exerciseCounter =model.getExerciseCount()
        ab = (activity as AppCompatActivity).supportActionBar
        model.mutableListExercise.observe(viewLifecycleOwner){
             exList = it
            nextExercise()
        }
        //урок 19 12-30
        binding.bNext.setOnClickListener {
            nextExercise()
        }
    }
    private fun nextExercise(){
        // 19  урок 7:39
        if (exerciseCounter < exList?.size!!) {
            var ex = exList?.get(exerciseCounter++) ?: return
            showExercise(ex)
            setExerciseType(ex)
            shownextExercise()
    } else {
      //Toast.makeText( activity,"Продолжим", Toast.LENGTH_LONG).show() выод слова Продолжим
          //ур 22 9-00 запускаем экран готово
        FragmentManager.setFragment(DayFinishFragment.newInstance(), activity as AppCompatActivity)
        }
    }

    private fun showExercise(exercise: ExerciseModel) = with(binding){
            imMan.setImageDrawable(GifDrawable(root.context.assets, exercise.image)) //вывод картинкм упр
            tvNameEx.text = exercise.name
            val title = "$exerciseCounter / ${exList?.size}"//  всего упр и сколько сделано
            ab?.title =title //надпись в верхнем баре
    }

    private fun setExerciseType(exercise: ExerciseModel) {
        if (exercise.time.startsWith("x")) {
           binding.tvTime.text = exercise.time
        } else {
            startTimer(exercise)

        }
    }
    //показ след упр внизу экрана ур21 0:43

    private fun shownextExercise() = with(binding){

        if (exerciseCounter < exList?.size!!) {
             val ex = exList?.get(exerciseCounter) ?: return
            imNext.setImageDrawable(GifDrawable(root.context.assets, ex.image)) //вывод картинкм  след упр
            setTimeType(ex)

        //подрись над след упр урок 21 12:17
            //val name = ex.name + ": ${ex.time}"
            //tvNextName.text = name
        } else {// картинка в конце
            imNext.setImageDrawable(GifDrawable(root.context.assets, "Yess.gif"))
            //техт в конце
            tvNextName.text = getString(R.string.done)
        }
    }
    private fun setTimeType(ex: ExerciseModel){ //ур 21 13:21
        if (ex.time.startsWith("x")) {
            binding.tvNextName.text = ex.time
        } else {
            val name = ex.name + ": ${TimeUtils.getTime(ex.time.toLong() * 1000)}"
            binding.tvNextName.text = name +"name str 95"//назв след упр


        }

    }

// урок 20 3:18
    private fun startTimer(exercise: ExerciseModel) = with(binding){
    progressBar.max = exercise.time.toInt() * 1000
    timer?. cancel() //не запускать новый , если таймер уже работает ур 20 8:46
    timer = object  : CountDownTimer(exercise.time.toLong() * 1000, 1){
            override fun onTick(restTime: Long) {
                tvTime.text = TimeUtils.getTime(restTime) + "время"
                progressBar.progress = restTime.toInt()
            }

            override fun onFinish() {
                nextExercise()
            }


        }.start()
    }
//останов таймра если польз нажал отмена ур20 6:53
    override fun onDetach() {
        super.onDetach()
    //lesson24 16-05 сохр дня и кол упр при выходе  из прилож
        model.savePref(model.currentDay.toString(), exerciseCounter - 1)
        timer?.cancel()
    }

    companion object {

        @JvmStatic
        fun newInstance() = ExercisesFragment()
    }
}