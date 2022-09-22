package com.rttgnu.readingteacherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.rttgnu.readingteacherapp.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {
    // 바인딩 객체 타입에 ?를 붙여서 null을 허용 해줘야한다. ( onDestroy 될 때 완벽하게 제거를 하기위해 )
    lateinit var mBinding: FragmentLibraryBinding
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    var TAG = "Library"
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        //변수
        val LibraryList = arrayListOf<LibraryModel>()

        db.collection("Library")
            .whereEqualTo("libraryCategory", 1)
            .get()

        //바인딩
        mBinding = FragmentLibraryBinding.inflate(inflater, container, false)
        //xml에 넣어줌
        //mBinding.library.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mBinding.rviewlibrary.setHasFixedSize(true)

        //어뎁터 연결
        mBinding.rviewlibrary.adapter = LibraryAdapter(LibraryList)
        Log.d(TAG, "어댑터 연결?")

        return mBinding.root
    }
}