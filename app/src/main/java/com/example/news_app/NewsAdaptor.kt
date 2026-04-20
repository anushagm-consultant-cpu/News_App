package com.example.news_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdaptor(private var articles: List<Article>,private val onItemClick:(Article) -> Unit ) :RecyclerView.Adapter<NewsAdaptor.NewsViewHolder>(){

    class NewsViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.description)

        val image: ImageView = view.findViewById(R.id.newsImage)


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)

    }

    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int
    ) {


       val article = articles[position]//to get the specific article for this row

        holder.title.text=article.title
        holder.description.text=article.description


        Glide.with(holder.itemView.context)
            .load(article.urlToImage)
            .into(holder.image)




        holder.itemView.setOnClickListener {
            onItemClick(article)
        }

    }

    override fun getItemCount(): Int {
       return articles.size

    }




}