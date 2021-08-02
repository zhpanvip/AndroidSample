package com.zhpan.sample

import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.zhpan.library.BaseViewBindingFragment
import com.zhpan.sample.databinding.FragmentMainEntryBinding

class EntryFragment : BaseViewBindingFragment<FragmentMainEntryBinding>() {

	override fun onViewBindingCreated(rootView: View?) {
		val navController = findNavController()
		binding.itemAnimation.setOnClickListener {
			navController.navigate(R.id.action_MainEntryFragment_to_AnimateEntryActivity)
		}
		binding.itemJetpack.setOnClickListener {
			navController.navigate(R.id.action_MainEntryFragment_to_JetpackEntryActivity)
		}

		binding.itemNestedScroll.setOnClickListener {
			navController.navigate(R.id.action_MainEntryFragment_to_NestedEntryActivity)
		}

		binding.itemView.setOnClickListener {
			navController.navigate(R.id.action_MainEntryFragment_to_ViewEntryActivity)
		}

		binding.itemBinder.setOnClickListener {
			navController.navigate(R.id.action_MainEntryFragment_to_BinderActivity)
		}
		binding.itemBanner.setOnClickListener {
			navController.navigate(R.id.action_MainEntryFragment_to_Banner3DActivity)
		}
	}

	override fun createViewBinding(container: ViewGroup?): FragmentMainEntryBinding {
		return FragmentMainEntryBinding.inflate(layoutInflater)
	}
}