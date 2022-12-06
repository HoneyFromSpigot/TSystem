package io.github.thewebcode.tsystem.api.utils;

import io.github.thewebcode.tsystem.module.AbstractModule;

import java.io.File;

public class ModuleFileManager {

    public File getFile(AbstractModule module, String file) {
        String moduleID = module.getModuleID();

        File dir = new File("tsystem/modules/" + moduleID + "/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File f = new File("tsystem/modules/" + moduleID + "/" + file);

        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return f;
    }

    public File getConfigFile(AbstractModule module) {
        return getFile(module, "config.yml");
    }
}
