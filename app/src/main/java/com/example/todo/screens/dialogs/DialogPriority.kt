package com.example.todo.screens.dialogs
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.todo.variantsPriority
import com.example.todo.databinding.DialogPriorityBinding
import com.example.todo.interfaces.SelectPriorityClick

class DialogPriority(var clickListener: SelectPriorityClick): DialogFragment() {
    private var _binding: DialogPriorityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogPriorityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textNonePriority.setOnClickListener {
            clickListener.selectPriority(variantsPriority[0])
            dismiss()
        }

        binding.textMiddlePriority.setOnClickListener {
            clickListener.selectPriority(variantsPriority[1])
            dismiss()
        }

        binding.textHighPriority.setOnClickListener {
            clickListener.selectPriority(variantsPriority[2])
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}