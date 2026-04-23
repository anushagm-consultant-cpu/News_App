package com.example.news_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class SecondFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        Log.d("secondFragment", "onCreateView")

        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_second, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("title")
        val description = arguments?.getString("description")
        val imageUrl = arguments?.getString("imageUrl")

        view.findViewById<TextView>(R.id.detailTitle).text = title
        view.findViewById<TextView>(R.id.detailDescription).text = description

        val imageView = view.findViewById<ImageView>(R.id.detailImage)
        Glide.with(this)
            .load(imageUrl)
            .into(imageView)

        Log.d("secondFragment", "onViewCreated")

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("secondFragment","onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        Log.d("secondFragment","onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("secondFragment","onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("secondFragment","onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("secondFragment","onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("secondFragment","onDestroyView")
        super.onDestroyView()
    }


    override fun onDestroy() {
        Log.d("secondFragment","onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("secondFragment", "onDetach")
        super.onDetach()
    }





}