package com.cursokotlin.mvvmexample.ui.view

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cursokotlin.mvvmexample.R
import com.cursokotlin.mvvmexample.data.model.QuoteModel
import com.cursokotlin.mvvmexample.data.network.QuoteApiClient
import com.cursokotlin.mvvmexample.databinding.ActivityMainBinding
import com.cursokotlin.mvvmexample.di.NetworkModule
import com.cursokotlin.mvvmexample.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import androidx.appcompat.widget.SearchView;
import androidx.core.view.isVisible
import com.cursokotlin.mvvmexample.data.QuoteRepository
import com.cursokotlin.mvvmexample.data.model.QuoteProvider
import com.cursokotlin.mvvmexample.data.network.QuoteService
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var retrofit: Retrofit
    private lateinit var apiClient: QuoteApiClient
    private lateinit var repository: QuoteRepository
    private lateinit var service: QuoteService
    private lateinit var provider: QuoteProvider
    private lateinit var search: SearchView
    private val quoteViewModel: QuoteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Pasa el layout a linkear con el binding.
        binding = ActivityMainBinding.inflate(layoutInflater)

        //settemos la vista
        setContentView(binding.root)

        //Iniciamos Retrofit:

        retrofit = NetworkModule.provideRetrofit()
        apiClient = NetworkModule.provideQuoteApiClient(retrofit)
        service = QuoteService(apiClient)
        provider = QuoteProvider()
        //Set the repository
        repository = QuoteRepository(service, provider)
        //Inizializate the view.
        search = findViewById(R.id.EtId)
        search.isClickable = true
        search.setOnQueryTextListener(this)


        //Inicializamos el controlador UI-data (ViewModel)
        quoteViewModel.onCreate()

        //Establecemos un observer en los datos
        quoteViewModel.quoteModel.observe(this, Observer {
            binding.tvName.text = it?.name
            binding.tvEmail.text = it?.email
            binding.tvAddress.text = it?.addrees?.city + ' ' + it?.addrees?.street
            binding.tvPhone.text = it?.phone
            binding.tvWebsite.text = it?.website
            binding.tvCompany.text = it?.company?.name
        })
        //Establecemos un observer en la carga de la página.
        quoteViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchByName(query.toLowerCase())
        return true
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("Error", "entra en SearchByName")

            //Falla Aqui al inovocar al getUserById.
            val resp = apiClient.getUserById("users/$query")

            if (resp.isSuccessful) {
                Log.d("Error", "resp is succesfull" + resp.isSuccessful)

                val userSearch = resp.body() as QuoteModel

                binding.tvName.text = userSearch?.name
                binding.tvAddress.text =
                    userSearch?.addrees?.city + ' ' + userSearch?.addrees?.street
                binding.tvCompany.text = userSearch?.company?.name
                binding.tvEmail.text = userSearch?.email
                binding.tvPhone.text = userSearch?.phone
                binding.tvWebsite.text = userSearch?.website
            } else {
                Log.d("Error", "resp is not succesfull" + resp.isSuccessful)
            }
        }

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}