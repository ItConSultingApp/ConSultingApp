package com.example.it_consultiong

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.it_consultiong.databinding.ActivityChooseMajorBinding
import com.example.it_consultiong.databinding.ActivitySignUpBinding

class MajorDialog(
    context: Context,
    myCustomDialogInterface: DialogInterface
) : Dialog(context), View.OnClickListener {

    val TAG: String = "로그"
    private val binding by lazy { ActivityChooseMajorBinding.inflate(layoutInflater) }

    //
    private var myCustomDialogInterface: DialogInterface? = null

    // 인터페이스 연결
    init {
        this.myCustomDialogInterface = myCustomDialogInterface
    }

    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_choose_major)

        Log.d(TAG, "MyCustomDialog - onCreate() called")
        // 배경 투명
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.ImageAndroid.setOnClickListener(this)


    }

    override fun onClick(view: View?) {
        when (view) {

            // 좋아요 버튼이 클릭 되었을때
            binding.ImageAndroid -> {


                this.myCustomDialogInterface?.onItemClickListener()
            }


        }

    }
}
