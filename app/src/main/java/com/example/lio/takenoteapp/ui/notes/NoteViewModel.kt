package com.example.lio.takenoteapp.ui.notes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.lio.takenoteapp.data.local.entities.Note
import com.example.lio.takenoteapp.other.Event
import com.example.lio.takenoteapp.other.Resource
import com.example.lio.takenoteapp.repositories.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel @ViewModelInject constructor(
    private val repository: NoteRepository
): ViewModel() {

    private val _forceUpdate = MutableLiveData<Boolean>(false)

    private val _allNotes = _forceUpdate.switchMap {
        repository.getAllNotes().asLiveData(viewModelScope.coroutineContext)
    }.switchMap {
        MutableLiveData(Event(it))
    }
    val allNotes: LiveData<Event<Resource<List<Note>>>> = _allNotes

    fun syncAllNotes() = _forceUpdate.postValue(true)

    fun deleteNote(noteID: String) = viewModelScope.launch {
        repository.deleteNote(noteID)
    }

    fun deleteLocallyDeletedNoteID(deletedNoteID: String) = viewModelScope.launch {
        repository.deleteLocallyDeletedNoteID(deletedNoteID)
    }

    fun insertNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

}