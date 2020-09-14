package ee.taltech.ylesanne1

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ee.taltech.ylesanne1.model.Question
import ee.taltech.ylesanne1.model.Quiz
import kotlinx.android.synthetic.main.activity_questions.*

class QuestionsActivity : AppCompatActivity() , View.OnClickListener{
    private var maxPointer: Int = 0;
    private var currPointer: Int = 0;
    private var currProgress: Int = 0;

    companion object {
        private const val COUNT_QUESTION = 10
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        var listQ: List<Question>? = Quiz.getQuestions(this)?.shuffled()


        if (listQ != null) {
            var progressStep = 100 / listQ.size;
            val res_id =
                resources.getIdentifier(listQ.get(currPointer).imgResID, "drawable", packageName);
            questionText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F);
            questionText.text = "What is the capital of ${listQ.get(currPointer).countryName}?"
            getActionBar()?.hide();
            //setTitle("What is the capital of ${listQ.get(currPointer).countryName}?");
            img_main.setImageResource(res_id)
            btn_1.text = listQ.get(currPointer).correctAnswer
            currProgress += progressStep;
            progressBar.setProgress(currProgress)

            btn_1.setOnClickListener(this)
            btn_2.setOnClickListener(this)
            btn_3.setOnClickListener(this)
            btn_4.setOnClickListener(this)
            btn_action.setOnClickListener(this)

        }


//        btn_action.setOnClickListener {
//            img_main.setImageDrawable(getResources().getDrawable(R.drawable.ee))
//            progressBar.setProgress(50)
//        }

    }

    fun getResByName(context: Context, name: String?): Int {
        return context.resources.getIdentifier("$name.jpg", "string", context.packageName)
    }

    override fun onClick(v: View?) {
        setTitle(v?.id.toString());
        when(v!!.id){
            btn_1.id->{setTitle(btn_1.text)}
            btn_2.id->{setTitle(btn_2.text)}
            btn_3.id->{setTitle(btn_3.text)}
            btn_4.id->{setTitle(btn_4.text)}
            else->setTitle("bb")
        }

    }

}