package com.yassin.marveltask.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.yassin.marveltask.model.Characters;
import com.yassin.marveltask.repository.CharacterRepository;

import java.util.List;


//ToDo 4: Use repository to get Characters
/* We will edit the ViewModel to take repository as a parameter and get character from it
 */
public class MainViewModel extends ViewModel {


    private LiveData<List<Characters>> characters;

    MainViewModel(CharacterRepository repository) {
        characters = repository.getCharacter();
    }

    public LiveData<List<Characters>> getCharacters() {
        return characters;
    }

}
