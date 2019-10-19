package by.ve.dialogsbinding.ui.dialog.common

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DialogUiConfig(
    @StringRes val title: Int,
    @StringRes val message: Int,
    @StringRes val positiveButtonText: Int? = null,
    @StringRes val negativeButtonText: Int? = null
) : Parcelable