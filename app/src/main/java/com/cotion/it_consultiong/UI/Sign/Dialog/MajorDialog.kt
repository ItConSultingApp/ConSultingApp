package com.cotion.it_consultiong.UI.Sign.Dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.widget.TextView
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.data.data_model.Major

class MajorDialog(context: Context) : View.OnClickListener {

    val TAG: String = "로그"

    private val dlg = Dialog(context)


    interface MajorDialogInterface {

        fun onPositiveClicked(major: TextView)

    }

    lateinit var major: Major


    private lateinit var majorDialogInterface: MajorDialogInterface





    fun setOnOKClickedListener(listener: (String) -> Unit) {
        this.majorDialogInterface = object : MajorDialogInterface {
            override fun onPositiveClicked(major: TextView) {
                Log.d(TAG, "MajorDialog - onPositiveClicked() called")
                listener(major.text.toString())
            }


        }
    }


    fun start() {
        dlg.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))// 배경 투명

        dlg.setContentView(R.layout.activity_choose_major) //xml

        major = Major(
            dlg.findViewById(R.id.android_img),
            dlg.findViewById(R.id.android_text),

            dlg.findViewById(R.id.img_ios),
            dlg.findViewById(R.id.ios_text),

            dlg.findViewById(R.id.web_frontEnt_img),
            dlg.findViewById(R.id.web_frontEnt_text),

            dlg.findViewById(R.id.web_backEnd_img),
            dlg.findViewById(R.id.web_backEnd_text),

            dlg.findViewById(R.id.web_function_class_img),
            dlg.findViewById(R.id.web_function_class_text),

            dlg.findViewById(R.id.game_img),
            dlg.findViewById(R.id.game_text),

            dlg.findViewById(R.id.game_function_class_img),
            dlg.findViewById(R.id.game_function_class_text),

            dlg.findViewById(R.id.Cyber_security_img),
            dlg.findViewById(R.id.Cyber_security_text),

            dlg.findViewById(R.id.Cyber_security_function_class_img),
            dlg.findViewById(R.id.Cyber_security_function_class_text),

            dlg.findViewById(R.id.ai_img),
            dlg.findViewById(R.id.ai_text),

            dlg.findViewById(R.id.threeD_img),
            dlg.findViewById(R.id.threeD_text),

            dlg.findViewById(R.id.cloud_img),
            dlg.findViewById(R.id.cloud_text),

            dlg.findViewById(R.id.iot_img),
            dlg.findViewById(R.id.iot_text),

            dlg.findViewById(R.id.robotics_img),
            dlg.findViewById(R.id.robotics_text),

            dlg.findViewById(R.id.etc_img),
            dlg.findViewById(R.id.etc_text),
        )

        major.and_img?.setOnClickListener(this)
        major.img_ios?.setOnClickListener(this)
        major.web_frontEnt_img?.setOnClickListener(this)
        major.web_backEnd_img?.setOnClickListener(this)
        major.web_function_class_img?.setOnClickListener(this)
        major.game_img?.setOnClickListener(this)
        major.game_function_class_img?.setOnClickListener(this)
        major.Cyber_security_img?.setOnClickListener(this)
        major.Cyber_security_function_class_img?.setOnClickListener(this)
        major.ai_img?.setOnClickListener(this)
        major.threeD_img?.setOnClickListener(this)
        major.cloud_img?.setOnClickListener(this)
        major.iot_img?.setOnClickListener(this)
        major.robotics_img?.setOnClickListener(this)
        major.etc_img?.setOnClickListener(this)

        dlg.show()


    }

    private fun setOnClick(major: TextView?) {
        if (major != null) {
            majorDialogInterface.onPositiveClicked(major)
        }
        dlg.dismiss()
    }


    override fun onClick(v: View?) {
        when (v) {
            major.and_img -> setOnClick(major.and_text)
            major.img_ios -> setOnClick(major.ios_text)
            major.web_frontEnt_img -> setOnClick(major.web_frontEnt_text)
            major.web_backEnd_img -> setOnClick(major.web_backEnd_text)
            major.web_function_class_img -> setOnClick(major.web_function_class_text)
            major.game_img -> setOnClick(major.game_text)
            major.game_img -> setOnClick(major.game_text)
            major.Cyber_security_img -> setOnClick(major.Cyber_security_text)
            major.game_function_class_img -> setOnClick(major.game_function_class_text)
            major.ai_img -> setOnClick(major.ai_text)
            major.threeD_img -> setOnClick(major.threeD_text)
            major.cloud_img -> setOnClick(major.cloud_text)
            major.iot_img -> setOnClick(major.iot_text)
            major.robotics_img -> setOnClick(major.robotics_text)
            major.etc_img -> setOnClick(major.etc_text)

        }
    }

}
