package by.ve.dialogsbinding.solutions.chooser

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import by.ve.dialogsbinding.R
import by.ve.dialogsbinding.databinding.ActivitySolutionChooserBinding
import by.ve.dialogsbinding.lifecycle.loadViewModel
import by.ve.dialogsbinding.lifecycle.observe
import by.ve.dialogsbinding.solutions.solution1.Solution1Activity
import by.ve.dialogsbinding.solutions.solution2.Solution2Activity
import by.ve.dialogsbinding.solutions.solution3.Solution3Activity
import kotlin.reflect.KClass


class SolutionChooserActivity : AppCompatActivity() {

    private val solutionChooserViewModel by loadViewModel<SolutionChooserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySolutionChooserBinding>(
            this,
            R.layout.activity_solution_chooser
        ).also {
            it.lifecycleOwner = this
            it.viewModel = solutionChooserViewModel
        }

        with(solutionChooserViewModel) {
            observe(solution1SelectedEvent) {
                startActivity(Solution1Activity::class)
            }

            observe(solution2SelectedEvent) {
                startActivity(Solution2Activity::class)
            }

            observe(solution3SelectedEvent) {
                startActivity(Solution3Activity::class)
            }
        }
    }

    private fun <T : Activity> startActivity(clazz: KClass<T>) {
        val intent = Intent(this, clazz.java)
        startActivity(intent)
    }
}