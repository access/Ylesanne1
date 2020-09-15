package ee.taltech.ylesanne1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ee.taltech.ylesanne1.model.Question
import ee.taltech.ylesanne1.model.Quiz
import kotlinx.android.synthetic.main.activity_questions.*


class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private val colorOfBtn = Color.LTGRAY;
    private val COUNT_QUESTIONS = 10;
    private var currPointer: Int = 0;
    private var currProgress: Int = 0;
    private var currentAnswer: String? = null
    private var goodAnswer: String? = null
    private var listQ: List<Question>? = null;
    private var SCORE = 0;
    private var answerLooked: Boolean = false
    private var correctCount: Int = 0
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        btn_1.setOnClickListener(this)
        btn_2.setOnClickListener(this)
        btn_3.setOnClickListener(this)
        btn_4.setOnClickListener(this)
        btn_action.setOnClickListener(this)
        listQ = Quiz.getQuestions(this)?.shuffled()
        setQ()
        username = intent.getStringExtra(Quiz.USER_NAME)
        btn_giveanswer.setOnClickListener {
            answerLooked = true
            when (goodAnswer) {
                btn_1.text -> btn_1.setBackgroundColor(Color.GREEN)
                btn_2.text -> btn_2.setBackgroundColor(Color.GREEN)
                btn_3.text -> btn_3.setBackgroundColor(Color.GREEN)
                btn_4.text -> btn_4.setBackgroundColor(Color.GREEN)
            }
            btn_action.text = "NEXT"
        }
    }


    fun setQ(): Unit {
        if (listQ != null) {
            var progressStep = 100 / COUNT_QUESTIONS;
            val resID = resources.getIdentifier(listQ!!.get(currPointer).imgResID, "drawable", packageName);
            questionText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F);
            questionText.text = "What is the capital of ${listQ!!.get(currPointer).country}?"
            progressSteps.text = "[${currPointer + 1}/$COUNT_QUESTIONS]"
            actionBar?.hide();
            //setTitle("What is the capital of ${listQ.get(currPointer).countryName}?");
            img_main.setImageResource(resID)
            btn_1.text = listQ!!.get(currPointer).capital
            currProgress += progressStep;
            progressBar.setProgress(currProgress)
            resetBtnColor()
            // create 4 buttons for random answers
            goodAnswer = listQ?.get(currPointer)?.capital
            val btnAnsw = getBtnAnswers(listQ!!.get(currPointer), listQ!!);
            Log.e("btnAnsw", "size: ${btnAnsw.size}")
            btn_1.text = btnAnsw.get(0).capital
            btn_2.text = btnAnsw.get(1).capital
            btn_3.text = btnAnsw.get(2).capital
            btn_4.text = btnAnsw.get(3).capital
            if (!answerLooked) btn_action.text = currentAnswer?.let { "ANSWER" } ?: kotlin.run { "SKIP" }
            currPointer++;
        }
    }

    override fun onClick(v: View?) {
        //setTitle(v?.id.toString());
        Log.e("setQ", "currP: $currPointer maxP: $COUNT_QUESTIONS")

        if (!answerLooked || v?.id == btn_action.id) {
            btn_1.setBackgroundColor(colorOfBtn)
            btn_2.setBackgroundColor(colorOfBtn)
            btn_3.setBackgroundColor(colorOfBtn)
            btn_4.setBackgroundColor(colorOfBtn)
            when (v!!.id) {
                btn_1.id -> {
                    btn_1.setBackgroundColor(Color.CYAN); currentAnswer = btn_1.text.toString(); //setTitle(currentAnswer);
                }
                btn_2.id -> {
                    btn_2.setBackgroundColor(Color.CYAN);currentAnswer = btn_2.text.toString(); //setTitle(currentAnswer);
                }
                btn_3.id -> {
                    btn_3.setBackgroundColor(Color.CYAN);currentAnswer = btn_3.text.toString(); //setTitle(currentAnswer);
                }
                btn_4.id -> {
                    btn_4.setBackgroundColor(Color.CYAN);currentAnswer = btn_4.text.toString(); //setTitle(currentAnswer);
                }
                btn_action.id -> {
                    Log.e("score", "currAn: $currentAnswer goodA: $goodAnswer")
                    if (currPointer <= COUNT_QUESTIONS) {
                        if (currentAnswer == goodAnswer && !answerLooked) {
                            SCORE += 10; correctCount++
                        } else {
                            if (SCORE > 0 && !answerLooked) SCORE -= 5
                        }
                    }
                    answerLooked = false
                    currentAnswer = null;
                    if (currPointer < COUNT_QUESTIONS) {
                        setQ()
                    } else {
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Quiz.CORRECT_ANSWERS, correctCount)
                        intent.putExtra(Quiz.TOTAL_QUESTIONS, COUNT_QUESTIONS)
                        intent.putExtra(Quiz.SCORE, SCORE)
                        intent.putExtra(Quiz.USER_NAME, username)
                        startActivity(intent)
                        finish()
                    }
                    setTitle("Your score: $SCORE");
                }
                //else -> setTitle("bb")
            }
        }
        if (!answerLooked) btn_action.text = currentAnswer?.let { "ANSWER" } ?: kotlin.run { "SKIP" }

    }

    private fun getBtnAnswers(correctAnswer: Question, possibleAnswers: List<Question>): List<Question> {
        val answerButtonsList: MutableList<Question> = mutableListOf<Question>()
        answerButtonsList.add(correctAnswer) // add correct answer
        for (i in possibleAnswers.shuffled()) {
            if (i != correctAnswer && answerButtonsList.size < 4) {
                answerButtonsList.add(i)
            } //else break
        }
        return answerButtonsList.shuffled()
    }

    private fun resetBtnColor(): Unit {
        if (!answerLooked) {
            btn_1.setBackgroundColor(colorOfBtn)
            btn_2.setBackgroundColor(colorOfBtn)
            btn_3.setBackgroundColor(colorOfBtn)
            btn_4.setBackgroundColor(colorOfBtn)
            // btn_action.setBackgroundColor(colorOfBtn)
        }
    }

}