/**
 *
 * Ulesanne1
Test. Riikide pealinnad
Koostage test 10 küsimustest (kasutage klassid, Image ja ArrayList).
Iga õige vastuse eest saab kasutaja +10 palli, vale vastuse eest – 5 palli
Kasutaja võib vaadata vastus küsimusele, sel juhul ta saab 0 palli.
Pärast testi sooritamist näidatakse tulemuse.
Rakenduses on võimalus pöörata ekraani.
 * */
package ee.taltech.ylesanne1

import android.content.Intent
import android.widget.Toast
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ee.taltech.ylesanne1.model.Quiz
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = "te"
        val intent = Intent(this, QuestionsActivity::class.java)
        intent.putExtra(Quiz.USER_NAME, username)

        //        //getActionBar()?.hide();
        //        startActivity(intent)
        //        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        //        finish()

        personName.requestFocus()
        buttonGo.setOnClickListener {
            val username = personName.text.toString()
            if (username.isBlank()) {
                Toast.makeText(this, "Please enter your name for starting", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, QuestionsActivity::class.java)
                intent.putExtra(Quiz.USER_NAME, username)
                startActivity(intent)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                finish()
            }
        }
    }
}