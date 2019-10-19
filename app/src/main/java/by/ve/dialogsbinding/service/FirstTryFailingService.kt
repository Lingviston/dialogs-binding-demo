package by.ve.dialogsbinding.service

import io.reactivex.Completable
import java.util.concurrent.TimeUnit

private const val DELAY_SECONDS = 2L

class FirstTryFailingService {

    private var hasAlreadyFailed = false

    fun request(): Completable = if (hasAlreadyFailed) {
        Completable.complete()
    } else {
        hasAlreadyFailed = true
        Completable.error(RuntimeException("First call always fails!"))
            .delay(DELAY_SECONDS, TimeUnit.SECONDS)
    }
}