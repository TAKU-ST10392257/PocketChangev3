package vcmsa.projects.pocketchange_v3.ui.Category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoryViewModel : ViewModel() {

    private val _emptyState = MutableLiveData<Boolean>().apply { value = true }
    val emptyState: LiveData<Boolean> = _emptyState

    // Later we will connect this to the actual categories list
}