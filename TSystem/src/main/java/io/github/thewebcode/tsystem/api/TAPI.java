package io.github.thewebcode.tsystem.api;

import io.github.thewebcode.tsystem.TBungeeSystem;
import io.github.thewebcode.tsystem.api.utils.ModuleFileManager;
import io.github.thewebcode.tsystem.module.AbstractModule;

import java.util.ArrayList;
import java.util.logging.Logger;

public class TAPI {

    private ArrayList<AbstractModule> modules;
    private ModuleFileManager fileManager;

    public TAPI() {
        this.modules = new ArrayList<>();
        this.fileManager = new ModuleFileManager();

    }

    public void registerModule(AbstractModule module) {
        modules.add(module);
        fileManager.getConfigFile(module);
        module.onEnable();
    }

    public AbstractModule getModule(String name) {
        for (AbstractModule module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }

        return null;
    }

    public ModuleFileManager getFileManager() {
        return fileManager;
    }

    public static void register(AbstractModule module) {
        get().registerModule(module);
    }

    public static TAPI get() {
        return TBungeeSystem.getInstance().getApi();
    }

    public static Logger getLogger() {
        return Logger.getLogger("TSystem");
    }
}
