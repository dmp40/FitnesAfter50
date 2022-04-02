package com.example.fitnesafter50.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fitnesafter50.R
import com.example.fitnesafter50.databinding.DayFinishBinding
import pl.droidsonroids.gif.GifDrawable

// урок 22
class DayFinishFragment : Fragment() {

 private lateinit var binding:DayFinishBinding
    private var ab: ActionBar? = null // ур 23 4-55


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DayFinishBinding.inflate(inflater,container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ab = (activity as AppCompatActivity).supportActionBar //ур 23 5-34
        //надпись в action bar в верху экрана
        ab?.title = getString(R.string.done) //изменить можно  в файле strings.sml
        //вывод картинки ур 22 6-54
        binding.ImEND.setImageDrawable(GifDrawable((activity as AppCompatActivity).assets,
            "Yess.gif"))
        //слушатель кнопки bDone ур 22 10-19
        binding.bDone.setOnClickListener {
            utils.FragmentManager.setFragment(DaysFragment.newInstance(),activity as AppCompatActivity)
        }

    }



        companion object {

        @JvmStatic
        fun newInstance() = DayFinishFragment()
    }
}