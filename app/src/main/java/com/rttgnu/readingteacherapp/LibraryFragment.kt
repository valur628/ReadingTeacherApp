package com.rttgnu.readingteacherapp

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.rttgnu.readingteacherapp.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {
    // 바인딩 객체 타입에 ?를 붙여서 null을 허용 해줘야한다. ( onDestroy 될 때 완벽하게 제거를 하기위해 )
    lateinit var mLibraryViewModel: LibraryViewModel
    lateinit var mBinding: FragmentLibraryBinding
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    var TAG = "Library"
    val fbdb : FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var dao: LibraryDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        //변수
        mBinding = FragmentLibraryBinding.inflate(inflater, container, false)
        mLibraryViewModel =ViewModelProvider(this).get(LibraryViewModel::class.java)
        mBinding.refreshButton.setOnClickListener{
            insertDataToDatabase()
        }

        val LibraryList : ArrayList<LibraryModel> = arrayListOf()
        val adapter = LibraryAdapter(LibraryList)

        //데이터를 room에 저장해서 계속해서 파이어베이스에서 읽지않게한다. 단 새로고침을 하면 리셋됨.
        //데이터를 리사이클러뷰에 띄워줄때는 room에서 가져온다.
        var libraryModel = LibraryModel(1,"눈의 여왕","한스 얀데르센",R.drawable.book,0,1,"눈의 여왕 설명")
        mLibraryViewModel.insertLibrary(libraryModel)

        libraryModel = LibraryModel(2,"견우와 직녀","작자미상",R.drawable.book,0,1,"견우와 직녀 설명")
        mLibraryViewModel.insertLibrary(libraryModel)


        mLibraryViewModel.readAllData.observe(viewLifecycleOwner, Observer {

        })
        // LibraryList.add(LibraryModel(3,"브레멘 음악대","그림형제",R.drawable.book,0,1,"브레멘 음악대 설명"))
        // LibraryList.add(LibraryModel(4,"삼국지 연의","나관중",R.drawable.book,0,1,"삼국지 연의 설명"))
        // LibraryList.add(LibraryModel(5,"성냥팔이 소녀","한스 얀데르센",R.drawable.book,0,1,"성냥팔이 소녀 설명"))
        // LibraryList.add(LibraryModel(6,"해와 달이 된 오누이","작자 미상",R.drawable.book,0,1,"해와 달이 된 오누이 설명"))


        //바인딩
        mBinding.rviewlibrary.setHasFixedSize(true)

        //어뎁터 연결
        mBinding.rviewlibrary.adapter = adapter

        return mBinding.root
    }

    private fun insertDataToDatabase() {
        //파이어베이스에서 로컬로 데이터 이동
        fbdb.collection("Library")   // 작업할 컬렉션
            //.whereEqualTo("libraryCategory", 1) //조건설정
            .get()      // 문서 가져오기
            .addOnSuccessListener { result ->
                // 성공할 경우
                for (document in result) {  // 가져온 문서들은 result에 들어감
                    Log.d(TAG, "${document.id} => ${document.data}") //가져온데이터
//                    LibraryList.add(LibraryModel(document["libraryID"] as Int,
//                        document["libraryName"] as String, document["libraryAuthor"] as String,
//                        R.drawable.book,0,1,"눈의 여왕 설명"))
                }
            }
            .addOnFailureListener { exception ->
                // 실패할 경우
                Log.w("LibraryFragment", "Error getting documents: $exception")
            }

    }

}