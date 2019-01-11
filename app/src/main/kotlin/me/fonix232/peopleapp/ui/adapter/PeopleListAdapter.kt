package me.fonix232.peopleapp.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import me.fonix232.common.ui.adapter.BaseAdapter
import me.fonix232.common.ui.adapter.BaseViewHolder
import me.fonix232.peopleapp.R
import me.fonix232.peopleapp.model.Person
import me.fonix232.peopleapp.bindings.PersonItemBinding
import me.fonix232.peopleapp.model.People

class PeopleListAdapter(items: LiveData<People>, owner: LifecycleOwner, onClick: (View, Person) -> Unit) :
    BaseAdapter<Person, PersonItemBinding, PersonVH>(items, owner, R.layout.item_person, onClick) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = object:PersonVH(inflate(parent), onClick) {}
}

typealias PersonVH = BaseViewHolder<Person, PersonItemBinding>