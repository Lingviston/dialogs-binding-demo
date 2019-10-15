package by.ve.dialogsbinding.ui.dialog.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DialogUiConfig(
    val title: String,
    val message: String,
    val positiveButtonText: String? = null,
    val negativeButtonText: String? = null
) : Parcelable