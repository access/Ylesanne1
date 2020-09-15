package ee.taltech.ylesanne1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ee.taltech.ylesanne1.model.Quiz
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val uname = intent.getStringExtra(Quiz.USER_NAME)
        val total = intent.getIntExtra(Quiz.TOTAL_QUESTIONS, 0)
        val score = intent.getIntExtra(Quiz.SCORE, 0)
        val correctAnswers = intent.getIntExtra(Quiz.CORRECT_ANSWERS, 0)
        actionBar?.hide()
        resultTxt.text = "Well done, $uname \r\nYour points is: $score \r\n $correctAnswers of $total done!"

        btn_finish.setOnClickListener() { startActivity(Intent(this, MainActivity::class.java)) }

    }
}