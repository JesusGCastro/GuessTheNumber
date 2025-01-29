package gonzalez.jesus.guessthenumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var maxNumber = 100
    var minNumber = 0
    var num: Int = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessings: TextView = findViewById(R.id.guessings)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        generate.setOnClickListener {
            num = Random.nextInt(minNumber, maxNumber)
            guessings.text = num.toString()
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener {
            minNumber = num
            if (checkingLimit()) {
                num = Random.nextInt(minNumber, maxNumber)
                guessings.text = num.toString()
            } else {
                guessings.text = "No puede ser :( Me ganaste"
            }
        }

        down.setOnClickListener {
            maxNumber = num  // Cambia minNumber a maxNumber aquí
            if (checkingLimit()) {
                num = Random.nextInt(minNumber, maxNumber)
                guessings.text = num.toString()
            } else {
                guessings.text = "No puede ser :( Me ganaste"
            }
        }

        guessed.setOnClickListener {
            if (!won) {
                guessings.text = "Adiviné, tu número es el $num"
                guessed.text = "Volver a jugar"
                won = true
            } else {
                generate.visibility = View.VISIBLE
                guessings.text = "Tap on generate to start"
                guessed.text = "Guessed"
                guessed.visibility = View.GONE
                resetValues()
            }
        }
    }

    fun resetValues() {
        minNumber = 0
        maxNumber = 100
        num = 0
        won = false
    }

    fun checkingLimit(): Boolean {
        return minNumber != maxNumber
    }
}