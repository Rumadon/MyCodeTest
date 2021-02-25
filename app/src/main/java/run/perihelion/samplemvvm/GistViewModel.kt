package run.perihelion.samplemvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import run.perihelion.models.Gist
import run.perihelion.web.WebHandler

class GistViewModel : ViewModel() {
    private var gistLiveData = MutableLiveData<List<Gist>>()

    fun getGists() = liveData(Dispatchers.IO) {
        val retrievedGists = WebHandler.getGists()
        emit(retrievedGists)
    }

    fun changeSorting(sortInt: Int) {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.
        gistLiveData.value?.sortedBy { it.user }?.let {
            gistLiveData.postValue(it)
        }
    }
}
