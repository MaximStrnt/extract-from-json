package com.example.newsmy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsmy.Item
import com.example.newsmy.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {


    private var binding: FragmentDetailBinding? = null
    private val mBinding get() = binding!!



    private lateinit var item: Item


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        item = arguments?.getSerializable("item") as Item
        return mBinding.root
    }


    override fun onStart() {
        super.onStart()

       mBinding.tvDDate.text = item.date
       mBinding.tvDTitle.text = item.title
       mBinding.tvDContent.text = item.content

        val x = item.image
        val y = mBinding.tvDImage
        Picasso.get().load(x).into(y)


    }




    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}