package by.buglak.fitflow.ui.me

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MeViewModel : ViewModel() {

    private val _text_training = MutableLiveData<String>().apply {
        value = "Training plans"
    }
    private val _text_reminder = MutableLiveData<String>().apply {
        value = "Reminder"
    }
    private val _text_disclaimer = MutableLiveData<String>().apply {
        value = "Disclaimer"
    }
    private val _text_privacy = MutableLiveData<String>().apply {
        value = "Privacy policy"
    }
    val text_training: LiveData<String> = _text_training
    val text_reminder: LiveData<String> = _text_reminder
    val text_disclaimer: LiveData<String> = _text_disclaimer
    val text_privacy: LiveData<String> = _text_privacy
}