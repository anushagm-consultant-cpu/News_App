package com.example.news_app

import android.os.Bundle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {

    private lateinit var adaptor: NewsAdaptor
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fetchNews()
    }

    private fun fetchNews() {
        lifecycleScope.launch {
            try {
                Log.d("FirstFragment", "Fetching news...")

                val response = RetrofitInstance.api.getNews("us", "eefee1a52611470e91fffc447798d5f0")

                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    Log.d("FirstFragment", "Fetched ${articles.size} news articles.")


                    //logic for when an item is clicked

                    adaptor = NewsAdaptor(articles){article ->

                        val secondFragment = SecondFragment().apply {
                            arguments = Bundle().apply {
                                putString("title",article.title)
                                putString("description",article.description)
                                putString("imageUrl",article.urlToImage)

                            }
                        }

                        parentFragmentManager.beginTransaction()
                            .replace(R.id.main,secondFragment)
                            .addToBackStack(null)
                            .commit()
                    }

                    recyclerView.adapter = adaptor

                } else {
                    Log.e("FirstFragment", "Request failed. Code: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("FirstFragment", "Error fetching news", e)
            }
        }
    }
}
