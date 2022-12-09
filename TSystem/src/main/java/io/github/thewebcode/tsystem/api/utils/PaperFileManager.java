package io.github.thewebcode.tsystem.api.utils;

import io.github.thewebcode.tsystem.module.AbstractModule;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class PaperFileManager {
    private File FOLDER, configFile;
    private YamlConfiguration configuration;

    public PaperFileManager(AbstractModule module){
        this.FOLDER = new File("plugins/" + module.getModuleID() + "/");
        this.configFile = new File(FOLDER, "config.yml");

        if(!FOLDER.exists()){
            FOLDER.mkdirs();
        }

        try{

            boolean setup = false;
            if(!configFile.exists()){
                setup = true;
                configFile.createNewFile();
            }

            configuration = YamlConfiguration.loadConfiguration(configFile);

            if(setup){
                configuration.set("localserver.port", 0);
                save();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void save(){
        try{
            configuration.save(configFile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }
}
