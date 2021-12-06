package com.lyndexter.androiddevelopment.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.presentation.user_list.UserAdapter
import com.lyndexter.androiddevelopment.presentation.user_list.UserViewModel
import com.lyndexter.androiddevelopment.presentation.user_list.UserViewModelProviderFactory
import timber.log.Timber

class WelcomeActivity : AppCompatActivity() {

    private val viewModel by viewModels<UserViewModel> { UserViewModelProviderFactory() }

    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val userRecycleView: RecyclerView = findViewById(R.id.user_list)
        userRecycleView.adapter = adapter
        userRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.welcome_container, WelcomeFragment.newInstance())
//            .commit()

        viewModel.userList.observe(this, {
            adapter.submitList(it)
        })

        viewModel.errorMessage.observe(this, { message ->
            Timber.d(message)
        })

        viewModel.getUsers()
    }
}
