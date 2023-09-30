package com.example.addbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var relativeLayout: RelativeLayout? = null

    private var lastButtonIndex = 0
    private var lastButtonId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout = findViewById(R.id.relativeLayout)
        findViewById<View>(R.id.addButton)?.setOnClickListener {
            addNewButton()
        }
    }

    private fun addNewButton() {
        // Создаём кнопку
        val newButton = createNewButton(lastButtonIndex, lastButtonId)

        // Добавляем кнопку в контейнер
        relativeLayout?.addView(newButton)

        // Обновляем значения для следующих использований
        ++lastButtonIndex
        lastButtonId = newButton.id
    }

    private fun createNewButton(
        currentButtonIndex: Int,
        previousButtonId: Int,
    ): View {
        val isFirstButton = currentButtonIndex == 0

        return Button(this).also { button ->
            button.text = "Button # $lastButtonIndex"

            // идентификатор нам нужен, чтобы привязываться к кнопкам.
            button.id = View.generateViewId()

            button.layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT,
            ).apply {
                this.marginEnd = 10
                if (isFirstButton) {
                    // Если у нас первая кнопка — привязываем её к родительскому контейнеру
                    this.addRule(RelativeLayout.ALIGN_PARENT_END)
                } else {
                    // Если кнопка не первая — привязываем её к предыдущей кнопке
                    this.addRule(RelativeLayout.START_OF, previousButtonId)
                }
            }
        }
    }

}