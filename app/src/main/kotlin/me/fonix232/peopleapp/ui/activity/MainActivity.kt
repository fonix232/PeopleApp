package me.fonix232.peopleapp.ui.activity

import me.fonix232.common.ui.activity.BaseActivity
import me.fonix232.peopleapp.R
import me.fonix232.peopleapp.bindings.MainActivityBinding
import me.fonix232.peopleapp.viewmodel.MainViewModel

class MainActivity : BaseActivity<MainViewModel, MainActivityBinding>(MainViewModel::class, R.layout.activity_main) {

}