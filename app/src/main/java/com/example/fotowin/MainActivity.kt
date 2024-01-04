package com.example.fotowin

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var answerEditText: EditText
    private lateinit var checkButton: Button
    private lateinit var allQuest: TextView
    private lateinit var allYesAnswer: TextView
    private lateinit var allNoAnswer: TextView
    private var yesAnswer : Int = 0
    private var noAnswer : Int = 0
    private val timer : Int = 15
    private var bull : Boolean = true
    private var ans : String = "0"
    var QuestionIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        questionTextView = findViewById(R.id.questionTextView)
        answerEditText = findViewById(R.id.answerEditText)
        checkButton = findViewById(R.id.checkButton)
        allQuest = findViewById(R.id.allQuest)
        allYesAnswer = findViewById(R.id.allYesAnswer)
        allNoAnswer = findViewById(R.id.allNoAnswer)

        displayNextQuestion()


        checkButton.setOnClickListener {
            val userAnswer = answerEditText.text.toString()
            checkAnswer(userAnswer)
            QuestionIndex++
            allQuest.text = "Всего вопросов: $QuestionIndex."
            allYesAnswer.text = "Правильных ответов: $yesAnswer."
            allNoAnswer.text = "Не правильных ответов $noAnswer."
        }
    }

    private fun displayNextQuestion() {

        if (bull == true) {
            val firstNumber = (1..10).random()
            val secondNumber = (1..10).random()
            val operation = listOf("+", "-", "*", "/").random()

            val question = when (operation) {
                "+" -> "$firstNumber $operation $secondNumber = ?"
                "-" -> if (firstNumber > secondNumber) {"$firstNumber $operation $secondNumber = ?" } else {"$secondNumber $operation $firstNumber = ?"  }
                "*" -> "$firstNumber $operation $secondNumber = ?"
                "/" -> "${firstNumber * secondNumber} $operation $secondNumber = ?"
                else -> "Error"
            }
            val answer = when (operation) {
                "+" -> (firstNumber + secondNumber).toString()
                "-" -> if (firstNumber > secondNumber) {(firstNumber - secondNumber).toString() } else { (secondNumber - firstNumber).toString() }
                "*" -> (firstNumber * secondNumber).toString()
                "/" -> (firstNumber).toString()
                else -> "Error"
            }
            ans = answer

            questionTextView.text = question
            answerEditText.text.clear()
        } else {
            questionTextView.text = "Вопросы закончились."
            answerEditText.isEnabled = false
            checkButton.isEnabled = false
            answerEditText.text.clear()
        }
    }

    private fun checkAnswer(userAnswer: String) {
        if (userAnswer == ans) {
            yesAnswer++
            Toast("УРА! $yesAnswer правильно!")
            // Ответ правильный
            // Можно здесь добавить любую логику для поздравления пользователя
            // Например: Toast сообщение или другое уведомление
            displayNextQuestion()
        } else {
            noAnswer--
            Toast("НЕТ! $noAnswer неправильно!")
            answerEditText.text.clear()
            // Ответ неправильный
            // Можно здесь добавить логику для сообщения об ошибке
            // Например: Toast сообщение или другое уведомление
            // Пример:
            // Toast.makeText(this, "Ответ неправильный", Toast.LENGTH_SHORT).show()
        }
    }

    private fun Toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}