package utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fitnesafter50.R

object FragmentManager {
    var currentFragment: Fragment? = null

   fun setFragment(newFragment: Fragment, act:AppCompatActivity) {
     val transaction = act.supportFragmentManager.beginTransaction()
      transaction.replace(R.id.placeHolder, newFragment)
      transaction.commit()
       currentFragment = newFragment
   }
}