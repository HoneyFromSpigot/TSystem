package io.github.thewebcode.tsystem.module;

import io.github.thewebcode.tsystem.api.TAPI;

import java.util.logging.Logger;

public abstract class AbstractModule {
    protected String moduleID;
    protected String version;
    protected String name;
    protected String author;

    public AbstractModule instance;

    public AbstractModule(String moduleID, String version, String name, String author) {
        this.moduleID = moduleID;
        this.version = version;
        this.name = name;
        this.author = author;
    }

    public void onEnable() {
        Logger logger = TAPI.getLogger();

        String message = "§8---------------------------------------------" + "\n" +
                "§aModule §8» §7" + name + "§8(§7" + moduleID + "§8)" + "\n" +
                "§aVersion §8» §7" + version + "\n" +
                "§aAuthor §8» §7" + author + "\n" +
                "§8---------------------------------------------";

        logger.info(message);
    }

    public void onDisable() {
        String message = "§8---------------------------------------------" + "\n" +
                "§cModule §8» §7" + name + "§8(§7" + moduleID + "§8)" + "\n" +
                "§cVersion §8» §7" + version + "\n" +
                "§cAuthor §8» §7" + author + "\n" +
                "§8---------------------------------------------";

        TAPI.getLogger().info(message);
    }



    public String getModuleID() {
        return moduleID;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
}
