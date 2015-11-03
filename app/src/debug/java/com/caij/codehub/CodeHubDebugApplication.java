package com.caij.codehub;

import com.facebook.stetho.Stetho;


/**
 * Created by Caij on 15/11/03.
 */
public class CodeHubDebugApplication extends CodeHubApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
