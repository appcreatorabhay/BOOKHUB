package com.example.BookHUB

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import model.Book

class DashboardrecyclerAdapter(val context:Context,val itemList:ArrayList<Book>):RecyclerView.Adapter<DashboardrecyclerAdapter.DashboardViewHolde>() {
    class DashboardViewHolde(view: View):RecyclerView.ViewHolder(view){
        val textView:TextView=view.findViewById(R.id.recycylerview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolde {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.items_dashboard,parent,false)
        return DashboardViewHolde(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolde, position: Int) {
        val book=itemList[position]
        holder.txtBookName.text=book.bookName
        holder.txtBookAuthor.text=book.bookAuthor
        holder.txtBookPrice.text=book.bookcost
        holder.txtBookRating.text=book.bookRating
        holder.imgBookImage.setImageResource(book.bookImage)
        holder.llContent.setOnClickListener{
            Toast.makeText(context,"clicked on $(holder.txtBookName.text}",Toast.LENGTH_SHORT).show()
        }

       // val text=itemList[position]
      //  holder.textView.text=text

    }
    class DashBoardviewHolder(view: View):RecyclerView.ViewHolder(view){
        val txtBookName:TextView=view.findViewById(R.id.txtBookName)
        val txtBookAuthor:TextView=view.findViewById(R.id.txtBookAuthor)
        val txtBookPrice:TextView=view.findViewById(R.id.txtBookPrice)
        val txtBookRating:TextView=view.findViewById(R.id.txtBookRating)
        val imgBookImage:TextView=view.findViewById(R.id.imgBookImage)
        val llContent:LinearLayout=view.findViewById(R.id.llcontent)
    }
}