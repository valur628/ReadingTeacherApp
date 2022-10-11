package com.rttgnu.readingteacherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.rttgnu.readingteacherapp.databinding.FragmentDownloadBinding

class DownLoafFragment: BottomSheetDialogFragment() {
    lateinit var mbinding: FragmentDownloadBinding
    val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    var TAG = "Download"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        mbinding = FragmentDownloadBinding.inflate(inflater, container, false)
        return mbinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //sqlite에서 데이터를 불러와서 text뷰를 수정할 예정

//        val name = arguments?.getString("name")
//        val author = arguments?.getString("author")
//        val description = arguments?.getString("description")
//        mbinding.tvName.setText(name)
//        mbinding.tvAuthor.setText(author)
//        mbinding.tvDescription.setText(description)
    }




}