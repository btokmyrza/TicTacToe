package kz.btokmyrza.tictactoeexam

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class GameResultDialogFragment(private val winnerStr: String?) : DialogFragment(R.layout.fragment_game_result_dialog) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFullScreen()

        val tvGameResult = view.findViewById<TextView>(R.id.tv_game_result)

        if (winnerStr != null)
            tvGameResult.text = "Выйграл $winnerStr!"
        else
            tvGameResult.text = "Ничья!"

        view.findViewById<Button>(R.id.btn_game_restart).setOnClickListener{
            (activity as MainActivity).gameReset()
            dismiss()
        }
    }

}

fun GameResultDialogFragment.setFullScreen() {
    dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
}