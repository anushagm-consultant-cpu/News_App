package com.example.news_app

import android.os.Bundle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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

        Log.d("FirstFragment", "onCreateView")

        return inflater.inflate(R.layout.fragment_first, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        Log.d("FirstFragment", "onViewCreated")


        fetchNews()
    }

    private fun fetchNews() {
        //
        lifecycleScope.launch {
            try {
                Log.d("FirstFragment", "Fetching news...")

                //This line waits for the network but DOES NOT freeze the app
                val response = RetrofitInstance.api.getNews("us", "eefee1a52611470e91fffc447798d5f0")

                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                  //  Log.d("FirstFragment", "Fetched ${articles.size} news articles.")


                    //logic for when an item is clicked

                    adaptor = NewsAdaptor(articles){article ->


                        val bundle = Bundle().apply {
                                putString("title",article.title)
                                putString("description",article.description)
                                putString("imageUrl",article.urlToImage)

                            }

//                        parentFragmentManager.beginTransaction()
//                            .replace(R.id.main,secondFragment)
//                            .addToBackStack(null)
//                            .commit()

                        findNavController().navigate(R.id.action_firstFragment_to_secondFragment,bundle)

                    }

                    recyclerView.adapter = adaptor

                } else {
                    Log.e("FirstFragment", "Request failed. Code: ${response.code()}")

                    Toast.makeText(requireContext(),"Server Error", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("FirstFragment", "Error fetching news", e)

                showErrorDailog()
            }
        }
    }

    private fun showErrorDailog(){

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Something went wrong. Please try again later.")
        builder.setPositiveButton("Retry"){_, _ ->
            fetchNews()


    }
        builder.setNegativeButton("close"){_,_ ->
            requireActivity().finish()
        }
        builder.setCancelable(false)
            .show()

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("FirstFragment","onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        Log.d("FirstFragment","onStart")
        super.onStart()
    }



    override fun onResume() {
        Log.d("FirstFragment","onResume")
        super.onResume()
    }


    override fun onPause() {
        Log.d("FirstFragment","onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("FirstFragment","onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("FirstFragment","onDestroyView")
        super.onDestroyView()
    }


    override fun onDestroy() {
        Log.d("FirstFragment","onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("FirstFragment", "onDetach")
        super.onDetach()
    }
}
