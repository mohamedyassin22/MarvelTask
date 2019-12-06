package com.yassin.marveltask.view_model;

//ToDo : ViewModelFactory like MainViewModel constructor to take the repository as a parameter

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yassin.marveltask.repository.CharacterRepository;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private CharacterRepository repository;

    public MainViewModelFactory(CharacterRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(repository);
    }
}
