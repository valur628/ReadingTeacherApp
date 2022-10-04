package com.rttgnu.readingteacherapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.google.android.gms.common.internal.RootTelemetryConfigManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.rttgnu.readingteacherapp.databinding.FragmentDownloadBinding

class DownLoafFragment: BottomSheetDialogFragment() {
    lateinit var binding: FragmentDownloadBinding
    val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    var TAG = "Download"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentDownloadBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //sqlite에서 데이터를 불러와서 text뷰를 수정할 예정
    }




}