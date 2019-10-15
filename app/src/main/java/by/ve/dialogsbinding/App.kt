package by.ve.dialogsbinding

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import by.ve.dialogsbinding.service.FirstTryFailingService
import by.ve.dialogsbinding.ui.demo.chooser.SolutionChooserViewModel
import by.ve.dialogsbinding.ui.dialog.fragment.DialogEventBus
import by.ve.dialogsbinding.ui.dialog.fragment.DialogNavigator
import by.ve.dialogsbinding.ui.dialog.fragment.DialogViewModel
import by.ve.dialogsbinding.ui.demo.solution1.Solution1ViewModel
import by.ve.dialogsbinding.ui.demo.solution2.ErrorView
import by.ve.dialogsbinding.ui.demo.solution2.Solution2ViewModel
import by.ve.dialogsbinding.ui.demo.solution3.Solution3ViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val serviceModule = module {

            factory { FirstTryFailingService() }
        }

        val chooserActivityModule = module {

            viewModel { SolutionChooserViewModel() }
        }

        val solution1ActivityModule = module {

            viewModel { Solution1ViewModel(get(), get()) }
        }

        val solution2ActivityModule = module {

            viewModel { Solution2ViewModel(get()) }

            factory<LifecycleOwner> { (activity: FragmentActivity) -> activity }

            factory { params -> ErrorView(get { params }, get { params }, get()) }
        }

        val solution3ActivityModule = module {

            viewModel { Solution3ViewModel(get()) }
        }

        val dialogModule = module {

            single { DialogEventBus() }

            viewModel { DialogViewModel() }

            factory { (activity: FragmentActivity) -> activity.supportFragmentManager }

            factory { params -> DialogNavigator(get { params }) }
        }

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    serviceModule,
                    dialogModule,
                    chooserActivityModule,
                    solution1ActivityModule,
                    solution2ActivityModule,
                    solution3ActivityModule
                )
            )
        }
    }
}