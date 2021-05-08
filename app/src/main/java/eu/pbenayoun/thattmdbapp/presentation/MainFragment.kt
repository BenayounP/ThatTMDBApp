package eu.pbenayoun.thattmdbapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import eu.pbenayoun.thattmdbapp.R
import eu.pbenayoun.thattmdbapp.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainFragment() : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? =null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var popularMoviesRepositoryViewModel: PopularMoviesRepositoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        popularMoviesRepositoryViewModel = ViewModelProvider(this).get(PopularMoviesRepositoryViewModel::class.java)
        popularMoviesRepositoryViewModel.getPopularMovies()
        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}