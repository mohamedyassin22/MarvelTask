package com.yassin.marveltask;

import android.content.Context;

import com.yassin.marveltask.repository.CharacterRepository;
import com.yassin.marveltask.repository.CharacterRepositoryImpl;
import com.yassin.marveltask.rest.ApiClient;
import com.yassin.marveltask.rest.ApiInterface;

//ToDo : Create injection class
/* Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
public class Injection {

    public static CharacterRepository provideCharacterRepository(Context context) {
        return new CharacterRepositoryImpl(provideAPIService(),
                provideAPIKey(context), provideTs(context), provideHash(context));
    }

    private static ApiInterface provideAPIService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

    private static String provideAPIKey(Context context) {
        return context.getString(R.string.api_key);
    }

    private static String provideTs(Context context) {
        return context.getString(R.string.ts);
    }

    private static String provideHash(Context context) {
        return context.getString(R.string.hash);
    }
}
