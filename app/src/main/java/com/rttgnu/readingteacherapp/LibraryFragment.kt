package com.rttgnu.readingteacherapp

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.rttgnu.readingteacherapp.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {
    // 바인딩 객체 타입에 ?를 붙여서 null을 허용 해줘야한다. ( onDestroy 될 때 완벽하게 제거를 하기위해 )
    lateinit var mBinding: FragmentLibraryBinding
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    var TAG = "Library"
    val db : FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        //변수
        val LibraryList : ArrayList<LibraryModel> = arrayListOf()
        val adapter = LibraryAdapter(LibraryList)

        //데이터를 sqlite에 저장해서 계속해서 파이어베이스에서 읽지않게한다. 단 새로고침을 하면 리셋됨

        //데이터를 리사이클러뷰에 띄워줄때는 sqlite에서 가져온다.

        //데이터 가져오기 <-지금이부분은 새로고침눌렀을때 sqlite에 저장되도록 한다.
        db.collection("Library")   // 작업할 컬렉션
            //.whereEqualTo("libraryCategory", 1) //조건설정
            .get()      // 문서 가져오기
            .addOnSuccessListener { result ->
                // 성공할 경우
                for (document in result) {  // 가져온 문서들은 result에 들어감
                    Log.d(TAG, "${document.id} => ${document.data}") //가져온데이터
                    //val item = LibraryModel(1,document["libraryName"] as String, document["libraryAuthor"] as String, R.drawable.book,0,1,"눈의 여왕 설명")
                    LibraryList.add(LibraryModel(document["libraryID"] as Int,document["libraryName"] as String, document["libraryAuthor"] as String, R.drawable.book,0,1,"눈의 여왕 설명"))
                }
               adapter.notifyDataSetChanged()  // 리사이클러 뷰 갱신
            }
            .addOnFailureListener { exception ->
                // 실패할 경우
                Log.w("LibraryFragment", "Error getting documents: $exception")
            }

        //바인딩
        mBinding = FragmentLibraryBinding.inflate(inflater, container, false)
        //xml에 넣어줌
        //mBinding.library.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mBinding.rviewlibrary.setHasFixedSize(true)

        //어뎁터 연결
        mBinding.rviewlibrary.adapter = adapter

        return mBinding.root
    }
}