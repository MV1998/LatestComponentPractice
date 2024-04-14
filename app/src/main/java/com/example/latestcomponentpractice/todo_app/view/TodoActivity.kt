package com.example.latestcomponentpractice.todo_app.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.latestcomponentpractice.databinding.ActivityTodoBinding
import com.example.latestcomponentpractice.todo_app.adapters.ToDoAdapter
import com.example.latestcomponentpractice.todo_app.adapters.ToDoListAdapter
import com.example.latestcomponentpractice.todo_app.model.Pen
import com.example.latestcomponentpractice.todo_app.repository.NewsAPI
import com.example.latestcomponentpractice.todo_app.repository.NewsRepository
import com.example.latestcomponentpractice.todo_app.repository.NewsRetrofitHelper
import com.example.latestcomponentpractice.todo_app.repository.PenRepository
import com.example.latestcomponentpractice.todo_app.repository.PersonRepository
import com.example.latestcomponentpractice.todo_app.repository.QuotesAPI
import com.example.latestcomponentpractice.todo_app.repository.QuotesRepository
import com.example.latestcomponentpractice.todo_app.repository.RetrofitHelper
import com.example.latestcomponentpractice.todo_app.room_db.AppDatabase
import com.example.latestcomponentpractice.todo_app.view_model.ToDoActivityViewModelFactory
import com.example.latestcomponentpractice.todo_app.view_model.TodoActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class TodoActivity : AppCompatActivity() {

    private val viewBinding : ActivityTodoBinding by lazy {
        ActivityTodoBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: TodoActivityViewModel

//    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var toDoListAdapter: ToDoListAdapter
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(viewBinding.root)


        database = AppDatabase.getInstance(applicationContext)
        val repo = PersonRepository(database.personDAO())
        val penRepository = PenRepository(database.penDAO())
        viewModel = ViewModelProvider(this, ToDoActivityViewModelFactory(repo))[TodoActivityViewModel::class.java]
//
//        viewBinding.lifecycleOwner = this
        viewBinding.apply {
            toDoViewModel = viewModel
            personRecyclerViewId.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//            toDoAdapter = ToDoAdapter(viewModel.personLiveData.value ?: mutableListOf(), onLongClick = {
//                Toast.makeText(this@TodoActivity, "${it.name}", Toast.LENGTH_LONG).show()
//                true
//            }) {
//                //viewModel.deletePerson(it)
//            }
          //  personRecyclerViewId.adapter =  toDoAdapter

            toDoListAdapter = ToDoListAdapter {
                Log.d("TAG", "onCreate: ${it.name}")
            }
            personRecyclerViewId.adapter = toDoListAdapter
        }

        viewBinding.addBtn.setOnClickListener {
            viewModel.addPerson(viewBinding.editTextNameId.text.toString(), viewBinding.editTextAgeId.text.toString())
            clearTextField()
        }

        viewModel.personLiveData.observe(this) {
        //    toDoAdapter.notifyDataChange(it)
            toDoListAdapter.submitList(it)
        }

        registerForContextMenu(viewBinding.personRecyclerViewId)

        val quotesAPI = RetrofitHelper.getInstance().create(QuotesAPI::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val quotesRepository = QuotesRepository(quotesAPI)
            quotesRepository.getQuotes()?.let {
                it.results.forEach { quote ->
                    viewModel.addPerson(quote.content,  Random.nextInt(111111, 999999).toString())
                }
            }
        }

        val newsAPI = NewsRetrofitHelper.getNewsRetrofitHelper().create(NewsAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val newsRepository = NewsRepository(newsAPI)
            newsRepository.getMPNews()?.let {
                Log.d(javaClass.simpleName, "onCreate: $it")
                penRepository.addPen(Pen(0, "Natraj", true, 3.00))
            }
        }
    }

    private fun clearTextField() {
        viewBinding.apply {
            editTextAgeId.text.clear()
            editTextNameId.text.clear()
        }
    }
}