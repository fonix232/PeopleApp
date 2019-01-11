package me.fonix232.peopleapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import me.fonix232.common.ui.fragment.BaseFragment
import me.fonix232.peopleapp.NavGraphDirections
import me.fonix232.peopleapp.R
import me.fonix232.peopleapp.bindings.PeopleListFragmentBinding
import me.fonix232.peopleapp.ui.adapter.PeopleListAdapter
import me.fonix232.peopleapp.viewmodel.PeopleListViewModel

class PeopleListFragment : BaseFragment<PeopleListViewModel, PeopleListFragmentBinding>(
    PeopleListViewModel::class,
    R.layout.fragment_people_list
) {
    val adapter = lazy { PeopleListAdapter(viewModel.people,this) { _, item -> findNavController().navigate(NavGraphDirections.gotoPersonDetail(item.id)) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.adapter = adapter.value
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
    }
}