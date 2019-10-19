package by.ve.dialogsbinding.ui.dialog.common

import androidx.annotation.StringRes
import by.ve.dialogsbinding.R
import kotlinx.android.parcel.Parcelize

val STANDARD_DIALOG_CONFIG = DialogUiConfig(
    title = R.string.error_title,
    message = R.string.error_message,
    positiveButtonText = R.string.error_retry,
    negativeButtonText = R.string.error_cancel
)

@Parcelize
data class DialogUiConfig(
    @StringRes override val title: Int,
    @StringRes override val message: Int,
    @StringRes override val positiveButtonText: Int? = null,
    @StringRes override val negativeButtonText: Int? = null
) : IDialogUiConfig