package com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Implementations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.apes_techno.Base.App
import com.example.apes_techno.Base.BaseFragment
import com.example.apes_techno.Connection.Resources.Services
import com.example.apes_techno.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.apes_techno.Models.BaseModel
import com.example.apes_techno.Models.MessageResponse
import com.example.apes_techno.Models.Movie
import com.example.apes_techno.Models.Movies
import com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Complements.ViewListMovies
import com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Interfaces.IFragmentHomeMoviesPresenter
import com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Interfaces.IFragmentHomeMoviesView
import com.example.apes_techno.R
import com.example.apes_techno.Util.DialogueGenerico

class FragmentHomeMovies : BaseFragment() {

    var rv_movies_list: ViewListMovies? = null
    private var presenter: IFragmentHomeMoviesPresenter? = null
    private var actionPresenter = actionViewPresenter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_movies, container, false)
        rv_movies_list = view.findViewById(R.id.list_movies)
        presenter = FragmentHomeMoviesPresenter(App.mContext!!, actionPresenter)
        return view
    }

    override fun onResume() {
        super.onResume()
        callService(
            Movies(), object :
            IRetrofitParcelable {},
            Services.get_list_movies)
    }

    fun callService(objectResponse: BaseModel,
                    objectSend: IRetrofitParcelable,
                    service: Services
    ){
        presenter?.callService(objectResponse, objectSend, service)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun visualizarList(list: MutableList<Movie>){
        rv_movies_list
            ?.conVisualizeListMovies(list)
            ?.conListenerMovieList {
                    findNavController().navigate(R.id.action_detail_movie)
            }
    }

    inner class actionViewPresenter: IFragmentHomeMoviesView {
        override fun failureService(response: MessageResponse) {
            dialogueFragment(response.Code.toString(), response.Message!!, DialogueGenerico.TypeDialogue.ERROR)
        }

        override fun responseHomeMovies(movies: MutableList<Movie>) {
            visualizarList(movies)
        }
    }
}