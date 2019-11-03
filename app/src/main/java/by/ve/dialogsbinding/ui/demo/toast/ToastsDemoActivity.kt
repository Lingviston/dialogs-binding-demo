package by.ve.dialogsbinding.ui.demo.toast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.ve.dialogsbinding.lifecycle.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToastsDemoActivity : AppCompatActivity() {

    private val viewModel by viewModel<ToastsDemoNavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, ToastsDemoFragment())
                .commit()
        }

        observe(viewModel.backEvent) {
            onBackPressed()
        }
        observe(viewModel.showAnotherFragmentEvent) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, JustAnotherFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}