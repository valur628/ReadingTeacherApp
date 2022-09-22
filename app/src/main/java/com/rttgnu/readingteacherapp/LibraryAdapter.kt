package com.rttgnu.readingteacherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class LibraryAdapter(val LibraryList: ArrayList<LibraryModel>) : RecyclerView.Adapter<LibraryAdapter.CustomViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryAdapter.CustomViewHolder{
        val view= LayoutInflater.from(parent.context).inflate(R.layout.library_item,parent,false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos: Int = adapterPosition
                val book : LibraryModel = LibraryList.get(curPos)
                Toast.makeText(parent.context,"책이름: ${book.LibraryName} \n저자 : ${book.LibraryAuthor}",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return LibraryList.size
    }

    override fun onBindViewHolder(holder: LibraryAdapter.CustomViewHolder,position: Int) {
        holder.LibraryCover.setImageResource(LibraryList.get(position).LibraryCover)
        holder.LibraryName.text = LibraryList.get(position).LibraryName
        holder.LibraryAuthor.text = LibraryList.get(position).LibraryAuthor


    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val LibraryCover = itemView.findViewById<ImageView>(R.id.id_book_cover) //책 커버
        val LibraryName = itemView.findViewById<TextView>(R.id.book_name)       //책 이름
        val LibraryAuthor = itemView.findViewById<TextView>(R.id.book_author)   //책 저자
    }


}