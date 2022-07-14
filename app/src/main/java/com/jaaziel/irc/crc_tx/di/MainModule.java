package com.jaaziel.irc.crc_tx.di;

import android.app.Activity;
import com.jaaziel.irc.crc_tx.data.MainMVP;
import com.jaaziel.irc.crc_tx.data.MainModel;
import com.jaaziel.irc.crc_tx.data.MainPresenter;
import com.jaaziel.irc.crc_tx.ui.MainActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;


@Module
@InstallIn(ActivityComponent.class)
public abstract class MainModule {

    @Binds
    public abstract MainMVP.View bindView(MainActivity activity);

    @Binds
    public abstract MainMVP.Presenter bindPresenter(MainPresenter presenter);
}

@Module
@InstallIn(ActivityComponent.class)
class MainActivityModule {
    @Provides
    public MainActivity provideMainView(Activity activity) {
        return (MainActivity) activity;
    }
}