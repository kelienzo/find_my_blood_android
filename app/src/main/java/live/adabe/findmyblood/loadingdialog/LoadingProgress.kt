package live.adabe.findmyblood.loadingdialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import live.adabe.findmyblood.R

class LoadingProgress(private val activity: Activity) {
    private lateinit var loadDialog: AlertDialog

    @SuppressLint("InflateParams")
    fun startLoading() {
        val loadingView = activity.layoutInflater.inflate(R.layout.loading_dialog, null)
        val builder = AlertDialog.Builder(activity)

        with(builder) {
            setView(loadingView)
            setCancelable(true)
        }

        loadDialog = builder.create()
        loadDialog.show()
    }

    fun endLoading() {
        loadDialog.dismiss()
    }
}