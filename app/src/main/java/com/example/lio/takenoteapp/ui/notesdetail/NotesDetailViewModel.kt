package com.example.lio.takenoteapp.ui.notesdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lio.takenoteapp.other.Event
import com.example.lio.takenoteapp.other.Resource
import com.example.lio.takenoteapp.repositories.NoteRepository
import kotlinx.coroutines.launch

class NotesDetailViewModel @ViewModelInject constructor(
    private val repository: NoteRepository
): ViewModel() {

    private val _addOwnerStatus = MutableLiveData<Event<Resource<String>>>()
    val addOwnerStatus: LiveData<Event<Resource<String>>> = _addOwnerStatus

    fun addOwnerToNote(owner: String, noteID: String) {
        _addOwnerStatus.postValue(Event(Resource.loading(null)))
        if(owner.isEmpty() || noteID.isEmpty()) {
            _addOwnerStatus.postValue(Event(Resource.error("The owner can't be empty", null)))
            return
        }
        viewModelScope.launch {
            val result = repository.addOwnerToNote(owner, noteID)
            _addOwnerStatus.postValue(Event(result))
        }
    }

    fun observeNoteByID(noteID: String) = repository.observeNoteByID(noteID)
}