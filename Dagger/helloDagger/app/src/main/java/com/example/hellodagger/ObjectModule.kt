package com.example.hellodagger

import android.util.Log
import dagger.Module
import dagger.Provides

@Module
class ObjectModule {
    @Provides
    fun provideString() : Any {
        Log.d("Dagger","object 생성")
        return Any()
    }

}