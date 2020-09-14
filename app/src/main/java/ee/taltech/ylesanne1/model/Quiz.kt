package ee.taltech.ylesanne1.model

import android.content.Context
import android.util.Log
import java.io.IOException


object Quiz {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    private const val filename = "relations.csv"

    fun getQuestions(context: Context): List<Question>? {
        val fileContent: String
        val list: MutableList<Question> = mutableListOf<Question>()
        try {
            fileContent = context.assets.open(filename).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            return list
        }

        fileContent.lines().map {
            list.add(
                Question(
                    it.split(";")[0],
                    it.split(";")[1],
                    it.split(";")[2]
                )
            )
        }
        return list
    }

}