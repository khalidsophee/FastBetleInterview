package com.myproject.fastbeetle.ui.dashboard

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.fastbeetle.data.Items
import com.myproject.fastbeetle.data.remote.NetworkState
import com.myproject.fastbeetle.databinding.ActivityMainBinding
import com.myproject.fastbeetle.di.Injectable
import com.myproject.fastbeetle.di.ViewModelProviderFactory
import com.myproject.fastbeetle.ui.BasicActivity
import com.myproject.fastbeetle.ui.viewmodel.LoginViewModel
import com.myproject.fastbeetle.ui.viewmodel.room.RoomViewModel
import com.myproject.fastbeetle.ui.viewmodel.room.UserEntity
import com.myproject.fastbeetle.utils.SessionManager
import java.util.*
import javax.inject.Inject

class MainActivity : BasicActivity(), Injectable {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var sessionManager: SessionManager
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, providerFactory).get(LoginViewModel::class.java)
    }

    private val roomViewModel: RoomViewModel by lazy {
        ViewModelProvider(this, providerFactory).get(RoomViewModel::class.java)
    }

    private var allUsers: List<Items>? = null
    private var dataUsers: List<UserEntity>? = null

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val now: Calendar = Calendar.getInstance()
        now.add(Calendar.MINUTE, 30)

        fetchDataFromDB()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            viewModel.login()
        }, 30000)



        viewModel.loginState.observe(this, ::onFetch)
    }

    private fun onFetch(state: NetworkState<List<Items>>) {
        if (state is NetworkState.Loading<Any>) {
            Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
            return
        }
        when (state) {
            is NetworkState.Success -> {
                allUsers = state.items
                saveUsers(allUsers)
            }
            is NetworkState.Failure -> {

                Toast.makeText(this, "Faiure", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUsers(allUsers: List<Items>?) {
        allUsers?.forEach {
            roomViewModel.insert(
                this@MainActivity,
                it.emailId.toString(),
                it.lastName.toString(),
                it.imageUrl.toString(),
                it.firstName.toString(),
                System.currentTimeMillis().toString()
            )
        }
    }

    private fun fetchDataFromDB() {
        val item = mutableListOf<Items>()
        roomViewModel.getAllUsers(this)?.observe(this, Observer {
            dataUsers = it
            if (dataUsers.isNullOrEmpty()) {
                viewModel.login()
            } else {
                dataUsers?.forEach {
                    item.add(Items(it.emailId, it.lastName, it.imageUrl, it.firstName))
                }
                binding.rvUsers.layoutManager = LinearLayoutManager(this)
                binding.rvUsers.adapter = UserAdapter(item)
            }
        })


    }
}