package com.example.latestcomponentpractice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ActionProvider.VisibilityListener
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.latestcomponentpractice.Screens.CalculatorActivity
import com.example.latestcomponentpractice.Screens.DrawerActivityExample
import com.example.latestcomponentpractice.Screens.FragmentPracticeActivity
import com.example.latestcomponentpractice.Screens.ListViewActivity
import com.example.latestcomponentpractice.Screens.RecyclerViewActivity
import com.example.latestcomponentpractice.Screens.VideoViewActivity
import com.example.latestcomponentpractice.ViewModel.UserViewModel
import com.example.latestcomponentpractice.ViewModel.ViewModelState
import com.example.latestcomponentpractice.abstraction_practice.Animal
import com.example.latestcomponentpractice.abstraction_practice.Dog
import com.example.latestcomponentpractice.abstraction_practice.ElectronicProduct
import com.example.latestcomponentpractice.abstraction_practice.Fish
import com.example.latestcomponentpractice.abstraction_practice.Horse
import com.example.latestcomponentpractice.abstraction_practice.Mammal
import com.example.latestcomponentpractice.abstraction_practice.ProductForSale
import com.example.latestcomponentpractice.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel : UserViewModel
    var currentText = "1"
    private final val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // factory for view model
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        userViewModel.receiveAllDataFromServer()

        Log.d("TAG", "onCreate: data is being download loaded")
        userViewModel.userLiveData.observe(this) {
            if (it is ViewModelState.Loaded) {
                binding.progressBar.visibility = View.GONE
//                Log.d("TAG", "onCreate: " + it as ArrayList<String>)
                binding.list = "bola tha n : ${it.data}"
            }else {
                binding.list = "Loading..."
                binding.progressBar.visibility = View.VISIBLE
            }
        }

        binding.apply {
            nameView.setOnClickListener {
                Toast.makeText(applicationContext, "From TextView", Toast.LENGTH_LONG).show()
            }
            currentTextView.text = "1"
            button.setOnClickListener {
                Log.d("TAG", "onCreate: ${Thread.currentThread().name}")
                currentTextView.text = "${currentTextView.text.toString().toInt() + 1}"


                var listOfAnimal : MutableList<Animal> = mutableListOf(
                    Dog("Germal shephard", 12, 100.0),
                    Dog("Labra Dog", 23, 120.0),
                    Fish("Fish 1 ", 124, 12.2),
                    Fish("Fish 2 ", 134, 112.2),
                    Fish("Fish 3", 144, 122.2),
                    Horse("Goldy horse", 1222, 1000.0)
                )

                for (animal in listOfAnimal) {
                    doAnimalStuff(animal)
                    if (animal is Mammal) {
                        animal.shedHair()
                    }
                }

                val products : MutableList<ProductForSale> = mutableListOf(
                    ElectronicProduct("Monitor", 8500.0, "Mohit has bought this monitor."),
                    ElectronicProduct("Mouse", 150.0, "Sonia has taken on his birthday.")
                )

                for(product in products) {
                    listProducts(product)
                }
            }
            longRunningBtn.setOnClickListener {
                CoroutineScope(Dispatchers.IO).apply {
                    launch {
                        doLongTask()
                    }
                }
            }

            calculator.setOnClickListener {
                val calculatorIntent = Intent(this@MainActivity, CalculatorActivity::class.java)
                startActivity(calculatorIntent)
            }

            videoViewBtn.setOnClickListener {
                val videoIntent = Intent(this@MainActivity, VideoViewActivity::class.java)
                startActivity(videoIntent)
            }

            openListView.setOnClickListener {
                val listViewIntent = Intent(this@MainActivity, ListViewActivity::class.java)
                startActivity(listViewIntent)
            }

            openRecyclerView.setOnClickListener {
                val listViewIntent = Intent(this@MainActivity, RecyclerViewActivity::class.java)
                startActivity(listViewIntent)
            }

            openFragmentActivity.setOnClickListener {
                val listViewIntent = Intent(this@MainActivity, FragmentPracticeActivity::class.java)
                startActivity(listViewIntent)
            }

            openDrawerLayout.setOnClickListener {
                val listViewIntent = Intent(this@MainActivity, DrawerActivityExample::class.java)
                startActivity(listViewIntent)
            }
        }

        Log.d(TAG, "onCreate: ")

        var mouse : String? = "Mohit"
        var n = mouse?.let {

        }

       var sss =  mouse?.also {

       }

//       var ss =  mouse?.run {
//            ""
//           1
//           1.1
//       }
//
//        var s = mouse?.apply {
//            ""
//            1.1
//        }
//
//        var ssss = with("") {
//            ""
//            11.1
//        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState: ")
        outState.putString("Mohit", "Data has been saved")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState: ")
        savedInstanceState.getString("Mohit")?.let {
            Log.d(TAG, "onRestoreInstanceState: Saved data got $it")
        }
    }

    private fun doLongTask() : Long{
        var x : Long = 1
        for (i in 0..1000000000000000L) {
            x = i
        }
        return x;
    }

    private fun doAnimalStuff(animal : Animal) {
        animal.makeNoise()
        animal.move("slow")
    }

    private fun listProducts(productForSale: ProductForSale) {
        productForSale.details()
    }
}

//class AnyArray {
//    companion object {
//        inline fun <reified T> toTArray(size: Int) : Array<T> {
//            return Array<T>(size) {it as T}
//        }
//    }
//}