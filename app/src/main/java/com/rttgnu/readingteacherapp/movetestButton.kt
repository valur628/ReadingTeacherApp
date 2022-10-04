package com.rttgnu.readingteacherapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.rttgnu.readingteacherapp.databinding.ActivityMovetestButtonBinding

private lateinit var binding: ActivityMovetestButtonBinding

class movetestButton : AppCompatActivity() {
    private lateinit var speechRecognizer: SpeechRecognizer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovetestButtonBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        requestPermission()

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, packageName)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")

        binding.speechButton.setOnClickListener {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
            speechRecognizer.setRecognitionListener(recognitionListener)
            speechRecognizer.startListening(intent)
        }

    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= 23 &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.RECORD_AUDIO), 0)
        }
    }

    private val recognitionListener: RecognitionListener = object : RecognitionListener {
        override fun onReadyForSpeech(params: Bundle) {
            Toast.makeText(applicationContext, "음성 인식 시작", Toast.LENGTH_SHORT).show()
            binding.statusText.text = "말하시오"
        }
        override fun onBeginningOfSpeech() {
            binding.statusText.text = "듣는 중"
        }
        override fun onEndOfSpeech() {
            binding.statusText.text = "완료"
        }
        override fun onRmsChanged(rmsdB: Float) {}
        override fun onBufferReceived(buffer: ByteArray) {}
        override fun onPartialResults(partialResults: Bundle) {}
        override fun onEvent(eventType: Int, params: Bundle) {}
        override fun onResults(results: Bundle) {
            val matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            for (i in matches!!.indices)
                binding.contentsTextView.setText(matches[i])
        }
        override fun onError(error: Int) {
            val message = when (error) {
                SpeechRecognizer.ERROR_AUDIO -> "> 오디오 에러"
                SpeechRecognizer.ERROR_CLIENT -> "> 클라이언트 에러"
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "> 권한 없음"
                SpeechRecognizer.ERROR_NETWORK -> "> 네트워크 에러"
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "> 네트워크 시간초과"
                SpeechRecognizer.ERROR_NO_MATCH -> "> 탐색 실패"
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "> 인식기가 이미 사용 중"
                SpeechRecognizer.ERROR_SERVER -> "> 서버 문제"
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "> 말하기 시간 초과"
                else -> "> 원인불명의 오류"
            }
            binding.statusText.text = "오류: $message"
        }
    }
}