package io.github.thewebcode.tsystem.api.utils;

import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.module.AbstractModule;

import java.io.File;

public interface FileManagement {
    default File getConfigFile(AbstractModule module) {
        return TAPI.get().getFileManager().getConfigFile(module);
    }

    default File getFile(AbstractModule module, String file) {
        return TAPI.get().getFileManager().getFile(module, file);
    }
}
