package cn.edu.gittest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    private  val REQUEST_CODE_SPEECH_INPUT = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //点击button,显示语音转文本对话框
        voiceBth.setOnClickListener {
            speak();
        }
    }

    private fun speak() {
        //显示意图
        val mIntent = Intent (RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hi speak something")

        try {
            //没有错误时，显示对话框
            startActivityForResult(mIntent,REQUEST_CODE_SPEECH_INPUT)
        }
        catch (e:Exception){
            //有错误时，获取错误信息并显示
            Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CODE_SPEECH_INPUT -> {
                if (resultCode == Activity.RESULT_OK && null != data){
                    //get text from result
                    val result= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    //set the text to textview
                    textTv.text = result!![0]
                }
            }
        }
    }


}