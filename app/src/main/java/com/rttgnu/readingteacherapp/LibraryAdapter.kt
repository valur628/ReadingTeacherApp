package com.rttgnu.readingteacherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rttgnu.readingteacherapp.databinding.ActivityLoginBinding.bind
import io.grpc.ClientCall

class LibraryAdapter : RecyclerView.Adapter<LibraryAdapter.CustomViewHolder>()
{
    private var LibraryList = emptyList<LibraryModel>() //선언
    //var bottomSheet: BottomSheet? = null
    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val LibraryCover = itemView.findViewById<ImageView>(R.id.id_book_cover) //책 커버
        val LibraryName = itemView.findViewById<TextView>(R.id.book_name)       //책 이름
        val LibraryAuthor = itemView.findViewById<TextView>(R.id.book_author)
        val Download = itemView.findViewById<Button>(R.id.downLoad)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder{
        //item과 리사이클러뷰를 연결
        val view= LayoutInflater.from(parent.context).inflate(R.layout.library_item,parent,false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return LibraryList.size//사이즈 리턴
    }

    //뷰홀더
    override fun onBindViewHolder(holder: CustomViewHolder,position: Int) {
        val item = LibraryList[position]
        //item과 데이터를 연결
        holder.LibraryCover.setImageResource(LibraryList.get(position).LibraryCover)
        holder.LibraryName.text = LibraryList.get(position).LibraryName
        holder.LibraryAuthor.text = LibraryList.get(position).LibraryAuthor

        holder.Download.setOnClickListener{
            itemClickListner.onClick(it,position)
        }
    }
    //click
    interface OnItemClickListner {
        fun onClick(v:View, position: Int)
    }
    private lateinit var itemClickListner: OnItemClickListner

    fun setItemClickListener(itemClickListner: OnItemClickListner){
        this.itemClickListner = itemClickListner
    }

    fun setData(libraryModel :List<LibraryModel>){
        //유저리스트가 변경 되었을때, 업데이트해줍니다.
        this.LibraryList = libraryModel
        notifyDataSetChanged()
    }
}