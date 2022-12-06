package io.github.thewebcode.testpapermodule;

import io.github.thewebcode.tsystem.api.TAPI;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestPaperModule extends JavaPlugin {
    private static TestPaperModule instance;

    @Override
    public void onEnable() {
        instance = this;
        TAPI.get().registerModule(new Module());
    }
}
