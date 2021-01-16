package com.example.playermotion.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.playermotion.MainActivity
import com.example.playermotion.R
import kotlin.math.abs

class PlayerFragment : Fragment() {

    companion object {
        fun newInstance() = PlayerFragment()
    }

    private lateinit var viewModel: MainViewModel
    lateinit var playerMotionLayout: MotionLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.player_v2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerMotionLayout = view.findViewById(R.id.playerMotionLayout)

        view.findViewById<CardView>(R.id.coverCard).setOnClickListener {
            Toast.makeText(requireContext(), "Ko-ko-ko", Toast.LENGTH_LONG).show()
        }

        view.apply {
            setOnTouchListener { toolbar, motionEvent ->
                if (motionEvent.action == ACTION_UP) {
                    toolbar.performClick()
                }

                (view.parent.parent as ViewGroup).onTouchEvent(motionEvent)

                Log.e("Toolbar", "onTouch")
                return@setOnTouchListener true
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

}