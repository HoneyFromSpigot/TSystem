package io.github.thewebcode.testbungeemodule;

import io.github.thewebcode.tsystem.TBungeeSystem;
import io.github.thewebcode.tsystem.TestClass;
import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.api.utils.FileManagement;
import io.github.thewebcode.tsystem.module.AbstractModule;
import io.github.thewebcode.tsystem.server.reflections.MethodMap;

public class Module extends AbstractModule implements FileManagement {
    public Module() {
        super("testbungeemodule", "1.0", "TestBungeeModule", "TheWebCode");
    }

    @Override
    public void onEnable() {
        instance = this;
        TAPI tapi = TAPI.get();
        MethodMap map = TAPI.map(this, TestBungeeClass.class);
        TAPI.get().register(map);
        super.onEnable();
    }
}
