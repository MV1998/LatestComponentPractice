package com.example.latestcomponentpractice

import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ActionProvider.VisibilityListener
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.latestcomponentpractice.Screens.CalculatorActivity
import com.example.latestcomponentpractice.Screens.DrawerActivityExample
import com.example.latestcomponentpractice.Screens.FragmentPracticeActivity
import com.example.latestcomponentpractice.Screens.ListViewActivity
import com.example.latestcomponentpractice.Screens.RecyclerViewActivity
import com.example.latestcomponentpractice.Screens.TabLayoutWithViewPager
import com.example.latestcomponentpractice.Screens.VideoViewActivity
import com.example.latestcomponentpractice.Screens.ViewPagerExample
import com.example.latestcomponentpractice.ViewModel.UserViewModel
import com.example.latestcomponentpractice.ViewModel.UserViewModelProviderFactory
import com.example.latestcomponentpractice.ViewModel.ViewModelState
import com.example.latestcomponentpractice.abstraction_practice.Animal
import com.example.latestcomponentpractice.abstraction_practice.Dog
import com.example.latestcomponentpractice.abstraction_practice.ElectronicProduct
import com.example.latestcomponentpractice.abstraction_practice.Fish
import com.example.latestcomponentpractice.abstraction_practice.Horse
import com.example.latestcomponentpractice.abstraction_practice.Mammal
import com.example.latestcomponentpractice.abstraction_practice.ProductForSale
import com.example.latestcomponentpractice.databinding.ActivityMainBinding
import com.example.latestcomponentpractice.di.UserRegistrationService
import com.example.latestcomponentpractice.foreground_services.MyMediaPlaybackService
import com.example.latestcomponentpractice.observers.Observer
import com.example.latestcomponentpractice.sqrt_app.SqrtActivity
import com.example.latestcomponentpractice.todo_app.view.TodoActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.internal.DaggerGenerated
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import java.util.concurrent.Flow
import javax.inject.Inject
import kotlin.math.log
import kotlin.math.sin

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel : UserViewModel
    var currentText = "1"
    private final val TAG = javaClass.simpleName
    private lateinit var binding : ActivityMainBinding

    @Inject
    lateinit var userRegistrationService: UserRegistrationService

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.addObserver(Observer())


        userRegistrationService.emailService.sendEmail("", "")

        // view model
        userViewModel = ViewModelProvider(this,
            UserViewModelProviderFactory(ViewModelState.Loading))[UserViewModel::class.java]

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

            viewPageOpenBtn.setOnClickListener {
                val listViewIntent = Intent(this@MainActivity, TabLayoutWithViewPager::class.java)
                startActivity(listViewIntent)
            }
            todoBtn.setOnClickListener {
                val listViewIntent = Intent(this@MainActivity, TodoActivity::class.java)
                startActivity(listViewIntent)
            }

            sqrtApp.setOnClickListener {
                val listViewIntent = Intent(this@MainActivity, SqrtActivity::class.java)
                startActivity(listViewIntent)
            }
        }

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
        CoroutineScope(Dispatchers.IO).launch {
            task1()
            getTheFollowers()
        }

        CoroutineScope(Dispatchers.IO).launch {
//            Log.d(TAG, "Second Coroutine Scope")
            task2()
        }

        producer()
        receiver()
//
//        val intent = Intent(this, MyMediaPlaybackService::class.java)
//        ContextCompat.startForegroundService(this, intent)
//

//        GlobalScope.launch {
//            flowProducer().collect { it ->
//                Log.d(TAG, "onCreate: by flow $it")
//                binding.list = it.toString()
//            }
//        }
    }


    var channel = Channel<Int>()
    fun producer() {
        CoroutineScope(Dispatchers.IO).launch {
            channel.send(1)
            delay(10000)
            channel.send(2)
        }
    }

    fun receiver() {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, "receiver: ${channel.receive()}")
//            Log.d(TAG, "receiver: ${channel.receive()}")
        }
    }

    private fun flowProducer() = flow<Int> {
        var list = Array(100) {i -> i + 10}
        list.forEach {
            delay(600)
            emit(it)
        }
    }

    suspend fun task1() {
        Log.d(TAG,"started task 1")
        delay(1000)
        Log.d(TAG,"finished task 1")
    }


    private suspend fun getTheFollowers() {
      var job =  CoroutineScope(Dispatchers.IO).launch {
          try {
              val fb = async { getFBFollowers() }
              val insta = async { getInstaFollowers() }
              binding.list = "${fb.await()} ${insta.await()} ${coroutineContext}"
          }catch (e : CancellationException) {
              Log.d(TAG, "getTheFollowers: ")
          }
        }
        job.cancel()
    }
    suspend fun getFBFollowers() : Int {
        delay(10000)
        return 100
    }
    suspend fun getInstaFollowers() : Int {
        delay(10000)
        return 500
    }

    suspend fun task2() {
        Log.d(TAG,"started task 2")
        delay(1000)
        Log.d(TAG,"finished task 2")
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
