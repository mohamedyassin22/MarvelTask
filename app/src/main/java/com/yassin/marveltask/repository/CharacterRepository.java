package com.yassin.marveltask.repository;

import androidx.lifecycle.LiveData;

import com.yassin.marveltask.model.Characters;

import java.util.List;

//ToDo  : Create The Repository
/* A Repository is a class that abstracts access to multiple data sources.
 * The Repository is not part of the Architecture Components libraries, but is a suggested best practice for code separation and architecture.
 * A Repository class handles data operations.
 * It provides a clean API to the rest of the app for app data.
 * -
 * In movie app, Character repository will fetch Character list from a network
 */
public interface CharacterRepository {
    LiveData<List<Characters>> getCharacter();

}
