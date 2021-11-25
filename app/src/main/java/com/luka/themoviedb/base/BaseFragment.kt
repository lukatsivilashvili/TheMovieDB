package com.luka.themoviedb.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.luka.themoviedb.R
import com.luka.themoviedb.extensions.setUp


typealias Inflates<T> = (inflate: LayoutInflater, parent: ViewGroup?, attach: Boolean) -> T


abstract class BaseFragment<BIN : ViewBinding>(
    private val inflate: Inflates<BIN>
) :
    Fragment() {

    private var _binding: BIN? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding == null) {
            _binding = inflate.invoke(inflater, container, false)
        }
        initialize(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun initialize(inflater: LayoutInflater, container: ViewGroup?)

    fun showErrorDialog(title: String, description: String) {

        val dialog = Dialog(requireContext())
        dialog.setUp(R.layout.dialog_layout)
        dialog.findViewById<TextView>(R.id.tvDialogTitle).text = title
        dialog.findViewById<TextView>(R.id.tvDialogDescription).text = description

        dialog.findViewById<Button>(R.id.btnDialogClose).text = getString(R.string.btnDialogClose)
        dialog.findViewById<Button>(R.id.btnDialogClose).setOnClickListener {
            activity?.finish()
        }
        dialog.show()
    }

}