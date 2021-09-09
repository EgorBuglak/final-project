package by.buglak.fitflow.ui.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrainingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "CHOOSE YOUR WORKOUT PROGRAM"
    }
    val text: LiveData<String> = _text

}