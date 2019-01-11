package me.fonix232.peopleapp.ui.fragment

import android.os.Bundle
import android.view.View
import me.fonix232.common.ui.fragment.BaseFragment
import me.fonix232.peopleapp.R
import me.fonix232.peopleapp.bindings.PersonDetailFragmentBinding
import me.fonix232.peopleapp.viewmodel.PersonDetailViewModel

class PersonDetailFragment : BaseFragment<PersonDetailViewModel, PersonDetailFragmentBinding>(
    PersonDetailViewModel::class,
    R.layout.fragment_person_detail
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = PersonDetailFragmentArgs.fromBundle(arguments!!)
        viewModel.init(args.id)
    }
}