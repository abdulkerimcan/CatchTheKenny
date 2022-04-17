package com.kerim.catchthekenny

import android.content.DialogInterface
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.kerim.catchthekenny.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var score : Int = 0
    var imageList = ArrayList<ImageView>()
    var runnable = Runnable {  }
    var handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        imageList.add(binding.imageView1)
        imageList.add(binding.imageView2)
        imageList.add(binding.imageView3)
        imageList.add(binding.imageView4)
        imageList.add(binding.imageView5)
        imageList.add(binding.imageView6)
        imageList.add(binding.imageView7)
        imageList.add(binding.imageView8)
        imageList.add(binding.imageView9)
        randomlyVisible()


        object : CountDownTimer(20000,1000) {
            override fun onTick(p0: Long) {
                binding.textViewTime.text = "Time : ${(p0/1000)}"
            }

            override fun onFinish() {
                handler.removeCallbacks(runnable)

                for (image in imageList)
                    image.visibility = View.INVISIBLE


                var alert  = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Try Again?")
                alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent = intent
                    finish()
                    startActivity(intent)

                })

                alert.show()
            }
        }.start()
    }

    private fun randomlyVisible() {


        runnable = object : Runnable {
            override fun run() {
                for (image in imageList)
                    image.visibility = View.INVISIBLE

                val random = Random
                var randomIndex = random.nextInt(9)
                imageList[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(this,500)
            }
        }
        handler.post(runnable)
    }

    fun increaseScore(view : View) {
        score++
        binding.textViewSkor.text = "Score : $score"
    }
}