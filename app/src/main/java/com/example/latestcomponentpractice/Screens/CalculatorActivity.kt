package com.example.latestcomponentpractice.Screens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.latestcomponentpractice.R
import com.example.latestcomponentpractice.databinding.ActivityCalculatorBinding
import com.example.latestcomponentpractice.databinding.ActivityMainBinding

class CalculatorActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCalculatorBinding
    private val expression : StringBuilder = StringBuilder()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setClickListeners()
    }

    private fun setClickListeners() {
        binding.apply {
            textViewOne.setOnClickListener(this@CalculatorActivity)
            textViewTwo.setOnClickListener(this@CalculatorActivity)
            textViewThree.setOnClickListener(this@CalculatorActivity)
            textViewFour.setOnClickListener(this@CalculatorActivity)
            textViewFive.setOnClickListener(this@CalculatorActivity)
            textViewSix.setOnClickListener(this@CalculatorActivity)
            textViewSeven.setOnClickListener(this@CalculatorActivity)
            textViewEight.setOnClickListener(this@CalculatorActivity)
            textViewNine.setOnClickListener(this@CalculatorActivity)
            textViewZero.setOnClickListener(this@CalculatorActivity)
            textViewBackspace.setOnClickListener(this@CalculatorActivity)
            textViewAC.setOnClickListener(this@CalculatorActivity)
            textViewMinus.setOnClickListener(this@CalculatorActivity)
            textViewPlus.setOnClickListener(this@CalculatorActivity)
            textViewDivide.setOnClickListener(this@CalculatorActivity)
            textViewMultiply.setOnClickListener(this@CalculatorActivity)
        }
    }

    override fun onClick(view: View?) {
        view?.let {
            when(view.id) {
                R.id.textViewOne->
                    expression.append('1')
                R.id.textViewTwo->
                    expression.append('2')
                R.id.textViewThree->
                    expression.append('3')
                R.id.textViewFour->
                    expression.append('4')
                R.id.textViewFive->
                    expression.append('5')
                R.id.textViewSix->
                    expression.append('6')
                R.id.textViewSeven->
                    expression.append('7')
                R.id.textViewEight->
                    expression.append('8')
                R.id.textViewNine->
                    expression.append('9')
                R.id.textViewZero->
                    expression.append('0')
                R.id.textViewPlus->
                    expression.append('+')
                R.id.textViewMinus->
                    expression.append('-')
                R.id.textViewMultiply->
                    expression.append('*')
                R.id.textViewDivide->
                    expression.append('/')
                R.id.textViewBackspace ->
                    if (expression.isNotEmpty()) {
                        expression.deleteAt(expression.length-1)
                    } else { }
                R.id.textViewAC ->
                    expression.clear()

                else -> {
                    Log.d(javaClass.simpleName, "onClick: ")
                }
            }
        }

        binding.expressionView.text = expression.toString()
        var result = 0
        var number  : StringBuilder = StringBuilder()
        for (word in expression) {
            if (word == '+') {
                result += Integer.parseInt(number.toString())
                number.clear()
            }else if (word == '*'){
                result *= Integer.parseInt(number.toString())
                number.clear()
            }else if (word == '/'){
                result /= Integer.parseInt(number.toString())
                number.clear()
            }else if (word == '-'){
                result -= Integer.parseInt(number.toString())
                number.clear()
            }else {
                number.append(word)
            }
        }
        Log.d(javaClass.simpleName, "onClick: $result")
    }
}