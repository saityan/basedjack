package saityan.misc.basedjack.view

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import saityan.misc.basedjack.databinding.FragmentMainBinding
import saityan.misc.basedjack.repository.Highpost
import saityan.misc.basedjack.view.adapter.MainAdapter
import saityan.misc.basedjack.viewmodel.MainViewModel
import saityan.misc.basedjack.viewmodel.PostsData

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var data: List<Highpost> = mutableListOf()

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel :: class.java]
    }

    private lateinit var adapter : MainAdapter
    private lateinit var recyclerView: RecyclerView

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        this.adapter = MainAdapter(this.data)
        this.recyclerView = binding.mainRecycler
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = this.adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPostsLiveData().observe(viewLifecycleOwner, { renderPostsData(it) })
        viewModel.sendServerRequest()
    }

    private fun renderPostsData(data: PostsData) {
        when(data) {
            is PostsData.Success -> {
                this.data = data.serverResponseData.data?.children ?: mutableListOf()
                adapter.setData(this.data)
                this.adapter.notifyDataSetChanged()
            }
            is PostsData.Error -> {
                toast(data.error.message)
            }
            is PostsData.Loading -> {}
        }
    }

    private fun Fragment.toast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.CENTER, 0, 0)
            show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
