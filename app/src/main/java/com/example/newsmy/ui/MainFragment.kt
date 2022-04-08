package com.example.newsmy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.newsmy.APP_ACTIVITY
import com.example.newsmy.Adapter
import com.example.newsmy.Item
import com.example.newsmy.R
import com.example.newsmy.databinding.FragmentMainBinding
import org.json.JSONException

class MainFragment: Fragment () {

    private var binding: FragmentMainBinding? = null
    private val mBinding get() = binding!!

    private lateinit var RV: RecyclerView
    private lateinit var mAdapter: Adapter


    private lateinit var items: ArrayList<Item>
    private lateinit var  mRequestQueue: RequestQueue



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }


    override fun onStart() {
        super.onStart()
        parseJSON()
    }


    private fun parseJSON() {
        items = ArrayList<Item>()
        val url = " *JSON URL* "   // here must be json url

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val jsonArray = response.getJSONArray("articles")
                    for (i in 0 until jsonArray.length()) {
                        val hit = jsonArray.getJSONObject(i)

                        val date = hit.getString("publishedAt").toString()
                        val title = hit.getString("title").toString()
                        val content = hit.getString("content").toString()
                        val image = hit.getString("urlToImage").toString()
                        items.add(Item(date, title, content, image))
                    }

                    initRV()

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }) { error -> error.printStackTrace() }
        mRequestQueue = Volley.newRequestQueue(this.context)
        mRequestQueue.add(request)
    }

    private fun initRV() {
        mAdapter = Adapter(this.context, items)
        RV = mBinding.rcView
        RV.setAdapter(mAdapter)
        RV.setHasFixedSize(true)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        RV.adapter = null
    }



    companion object{
        fun click(item: Item){
            val bundle = Bundle()
            bundle.putSerializable("item", item)
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }

}