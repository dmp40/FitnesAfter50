package utils

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnesafter50.adapters.ExerciseModel

class MainViewModel  : ViewModel (){
    val mutableListExercise = MutableLiveData<ArrayList<ExerciseModel>>()
    //урок 24 0-42
    var pref: SharedPreferences? = null
    //lesson 24 13-38
    var currentDay = 0
    fun savePref(key: String, value: Int) {
        pref?.edit()?.putInt(key, value)?.apply()
    }

    fun getExerciseCount( ): Int {
        return pref?.getInt(currentDay.toString(), 0) ?: 0
    }
}
//Оператор безопасного вызова, обозначаемый вопросительным
// знаком с точкой, похож на проверку на null с if в варианте
// без else. Он проверят, что значение слева от него не равно null.
// Если же оно равно null, то ничего не происходит.
// Точнее, все выражение возвращает null.
//Если же значение слева от оператора ?. возвращает что-то отличное
// от null, то вызывается метод, стоящий справа от этого оператора.
// Можно представить, что слева от ?. стоит проверка условия на
// неравенство null, а справа – это тело if, которое выполняется,
// если условие вернуло истину