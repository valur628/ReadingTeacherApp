package com.rttgnu.readingteacherapp


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.rttgnu.readingteacherapp.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {
    // 바인딩 객체 타입에 ?를 붙여서 null을 허용 해줘야한다. ( onDestroy 될 때 완벽하게 제거를 하기위해 )
    lateinit var mLibraryViewModel: LibraryViewModel
    lateinit var mBinding: FragmentLibraryBinding
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    var TAG = "Library"
    val fbdb : FirebaseFirestore = FirebaseFirestore.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        //변수
        mBinding = FragmentLibraryBinding.inflate(inflater, container, false)
        val adapter = LibraryAdapter()

        Log.d(TAG, "어뎁터 연결") //어뎁터 연결
        mBinding.rviewlibrary.setHasFixedSize(true)
        mBinding.rviewlibrary.adapter = adapter
        Log.d(TAG, "뷰모델 연결") //뷰모델 연결
        var libraryList_ : List<LibraryModel> = listOf()
        mLibraryViewModel = ViewModelProvider(this).get(LibraryViewModel::class.java)
        mLibraryViewModel.readAllData.observe(viewLifecycleOwner, Observer { libraryList ->
            adapter.setData(libraryList)
            libraryList_=libraryList
        })

        //리사이클러뷰 다운로드 버튼 클릭
        adapter.setItemClickListener(object : LibraryAdapter.OnItemClickListner{
            override fun onClick(v: View, position: Int) {
                val item = libraryList_[position]
                //adapter.notifyDataSetChanged()
                val download = DownLoafFragment()
                var bundle = Bundle()
                bundle.putString("name",item.LibraryName)
                bundle.putString("author",item.LibraryAuthor)
                bundle.putString("description",item.LibraryDescription)
                download.arguments = bundle
                Log.d(TAG, "Activity\n${item.LibraryName},${item.LibraryAuthor}")
                download.show(getParentFragmentManager(),download.tag)
//                activity?.supportFragmentManager!!.beginTransaction()
//                    .replace(R.id.main_frame_layout,download)
//                    .commit()
            }
        })

        //새로고침버튼
        mBinding.refreshButton.setOnClickListener{
            mLibraryViewModel.allDeleteLibrary()
            //파이어베이스에서 로컬로 데이터 이동
            fbdb.collection("Library")   // 작업할 컬렉션
                //.whereEqualTo("libraryCategory", 1) //조건설정
                .get()      // 문서 가져오기
                .addOnSuccessListener { result ->
                    for (document in result) {  // 가져온 문서들은 result에 들어감
                        Log.d(TAG, "${document.id} => ${document.data}") //가져온데이터
                        var libraryModel=LibraryModel(document["libraryID"] as Long,
                            document["libraryName"] as String,
                            document["libraryAuthor"] as String,
                            R.drawable.book,
                            document["libraryCategory"] as Long,
                            document["librarySeriesNum"] as Long,
                            document["libraryDescription"] as String)
                        mLibraryViewModel.insertLibrary(libraryModel)
                    }
                }
                .addOnFailureListener { exception ->
                    // 실패할 경우
                    Log.w("LibraryFragment", "Error getting documents: $exception")
                }
            mLibraryViewModel = ViewModelProvider(this).get(LibraryViewModel::class.java)
            mLibraryViewModel.readAllData.observe(viewLifecycleOwner, Observer { libraryList ->
                adapter.setData(libraryList)
                libraryList_=libraryList
            })
        }

        return mBinding.root
    }
}