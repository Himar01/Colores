package com.example.colores

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.colores.databinding.ActivityColoresBinding
import com.google.android.material.slider.LabelFormatter
import com.google.android.material.slider.Slider
import java.lang.Math.sqrt
import kotlin.random.Random

class ColoresActivity : AppCompatActivity() {

    private lateinit var binding : ActivityColoresBinding
    private var rTarget: Float = 0F
    private var gTarget: Float = 0F
    private var bTarget: Float = 0F

    private var rGuess: Float = 0F
    private var gGuess: Float = 0F
    private var bGuess: Float = 0F

    private lateinit var rSlider: Slider
    private lateinit var gSlider: Slider
    private lateinit var bSlider: Slider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityColoresBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTargetcolor()
        prepareActuators()
    }

    private fun checkResults() {
        val rDiff = rGuess - rTarget
        val gDiff = gGuess - gTarget
        val bDiff = bGuess - bTarget
        var diff = sqrt((rDiff*rDiff+gDiff*gDiff+bDiff*bDiff).toDouble()/3).toInt()
        diff = (100-diff)
        Toast.makeText(applicationContext, "Tu resultado es ${diff}",Toast.LENGTH_SHORT).show()
    }

    private fun setTargetcolor() {
        rTarget = Random(System.nanoTime()).nextFloat()*255
        gTarget = Random(System.nanoTime()).nextFloat()*255
        bTarget = Random(System.nanoTime()).nextFloat()*255

        binding.targetColor.setBackgroundColor(Color.rgb(rTarget.toInt(),gTarget.toInt()
            ,bTarget.toInt()))


    }

    private fun prepareActuators() {
        rSlider = binding.RedSlider
        gSlider = binding.GreenSlider
        bSlider = binding.BlueSlider


        rSlider.setLabelFormatter {
            String.format("%.0f",it)
        }
        gSlider.setLabelFormatter {
            String.format("%.0f",it)
        }
        // Same as before but without lambda as example
        bSlider.setLabelFormatter(object: LabelFormatter{
            override fun getFormattedValue(v:Float):String{
                return String.format("%.0f",v)
            }
        })

        rSlider.addOnChangeListener(){_,_,_ ->
            rGuess = rSlider.value
            gGuess = gSlider.value
            bGuess = bSlider.value
            binding.guessColor.setBackgroundColor(Color.rgb(rGuess.toInt(),gGuess.toInt()
                ,bGuess.toInt()))
            binding.guessText.text = resources.getString(R.string.texto,rGuess,gGuess,bGuess)
        }
        gSlider.addOnChangeListener(){_,_,_ ->
            rGuess = rSlider.value
            gGuess = gSlider.value
            bGuess = bSlider.value
            binding.guessColor.setBackgroundColor(Color.rgb(rGuess.toInt(),gGuess.toInt()
                ,bGuess.toInt()))
            binding.guessText.text = resources.getString(R.string.texto,rGuess,gGuess,bGuess)
        }
        bSlider.addOnChangeListener(){_,_,_ ->
            rGuess = rSlider.value
            gGuess = gSlider.value
            bGuess = bSlider.value
            binding.guessColor.setBackgroundColor(Color.rgb(rGuess.toInt(),gGuess.toInt()
                ,bGuess.toInt()))
            binding.guessText.text = resources.getString(R.string.texto,rGuess,gGuess,bGuess)
        }

        binding.HitMe.setOnClickListener(){
            _->
            checkResults()
            setTargetcolor()
        }



    }
}