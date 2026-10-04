package com.example.lab_21_planet_quiz

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class AnswersFragment : Fragment(R.layout.fragment_answers) {

    // Index in the planets array of the correct answer per question:
    // Jupiter (4), Saturn (5), Uranus (6)
    private val correctAnswers = listOf(4, 5, 6)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionIndex = requireArguments().getInt(ARG_QUESTION_INDEX)
        val questions = resources.getStringArray(R.array.questions)
        val planets = resources.getStringArray(R.array.planets)
        val details = resources.getStringArray(R.array.details)

        view.findViewById<TextView>(R.id.question_text).text = questions[questionIndex]
        val resultText = view.findViewById<TextView>(R.id.result_text)
        val container = view.findViewById<LinearLayout>(R.id.options_container)

        val margin = (8 * resources.displayMetrics.density).toInt()

        planets.forEachIndexed { planetIndex, planetName ->
            val button = MaterialButton(requireContext()).apply {
                text = planetName
                textSize = 18f
                letterSpacing = 0.1f
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, margin, 0, margin) }

                setOnClickListener {
                    resultText.text = if (planetIndex == correctAnswers[questionIndex]) {
                        getString(R.string.correct) + " " + details[questionIndex]
                    } else {
                        getString(R.string.wrong)
                    }
                }
            }
            container.addView(button)
        }
    }

    companion object {
        private const val ARG_QUESTION_INDEX = "question_index"

        fun newInstance(questionIndex: Int) = AnswersFragment().apply {
            arguments = bundleOf(ARG_QUESTION_INDEX to questionIndex)
        }
    }
}