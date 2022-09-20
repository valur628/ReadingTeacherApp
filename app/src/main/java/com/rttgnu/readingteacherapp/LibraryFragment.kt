package com.rttgnu.readingteacherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rttgnu.readingteacherapp.databinding.FragmentLibraryBinding.inflate

class LibraryFragment : Fragment() {
    // 바인딩 객체 타입에 ?를 붙여서 null을 허용 해줘야한다. ( onDestroy 될 때 완벽하게 제거를 하기위해 )
    private var mBinding: LibraryFragment? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // 액티비티 와는 다르게 layoutInflater 를 쓰지 않고 inflater 인자를 가져와 뷰와 연결한다.
        val LibraryList = arrayListOf(
            LibraryModel(1,"눈의 여왕","한스 얀데르센",R.drawable.book,0,1,"눈의여왕 설명"),
            LibraryModel(1,"삼국지연의","나관중",R.drawable.book,0,1,"눈의여왕 설명"),
            LibraryModel(1,"해와 달이 된 오누이","작자 미상",R.drawable.book,0,1,"눈의여왕 설명"),
            LibraryModel(1,"성냥팔이 소녀","한스 얀데르센",R.drawable.book,0,1,"눈의여왕 설명"),
            LibraryModel(1,"브레멘 음악대","그림 형제",R.drawable.book,0,1,"눈의여왕 설명"),
            LibraryModel(1,"견우와 직녀","작자 미상",R.drawable.book,0,1,"눈의여왕 설명")
        )

        //mBinding = LibraryFragment.inflate(inflater, container, false)
        //mBinding.rv_library.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        //mBinding.rv_librart.setFixedSize(true)


        return inflater.inflate(R.layout.fragment_library, container, false)
    }
}