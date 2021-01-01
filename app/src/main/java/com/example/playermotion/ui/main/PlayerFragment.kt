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

        val activityMotionLayout =  (activity as MainActivity).motionLayout

        activityMotionLayout.apply {
            setTransitionListener(
                object : MotionLayout.TransitionListener {
                    override fun onTransitionChange(
                        motionLayout: MotionLayout?,
                        startId: Int,
                        endId: Int,
                        progress: Float
                    ) {
                        playerMotionLayout.also {
                            it.progress = abs(progress)
                        }
                    }

                    override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {

                    }

                    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                    }

                    override fun onTransitionTrigger(
                        p0: MotionLayout?,
                        p1: Int,
                        p2: Boolean,
                        p3: Float
                    ) {

                        Log.e("ActivityMotionLayout", "$p1, $p2, $p3")

                    }
                }
            )

            setOnTouchListener { view, motionEvent ->
                if (motionEvent.action == ACTION_UP) {
                    view.performClick()
                }

                activityMotionLayout.onTouchEvent(motionEvent)
                Log.e("activityMotionLayout", "onTouch")
                return@setOnTouchListener true
            }
        }

        view.findViewById<CardView>(R.id.coverCard).setOnClickListener {
            Toast.makeText(requireContext(), "Ko-ko-ko", Toast.LENGTH_LONG).show()
        }

        view.findViewById<Toolbar>(R.id.toolbar).apply {
            setOnTouchListener { view, motionEvent ->
                if (motionEvent.action == ACTION_UP) {
                    view.performClick()
                }

                activityMotionLayout.onTouchEvent(motionEvent)
                Log.e("Toolbar", "onTouch")
                return@setOnTouchListener true
            }

            setOnClickListener {
                if (playerMotionLayout.currentState == R.id.fullState) {
                    activityMotionLayout.transitionToStart()
                } else {
                    activityMotionLayout.transitionToEnd()
                }
            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

}