package io.github.thewebcode.tsystem.api.utils;

import io.github.thewebcode.tsystem.module.AbstractModule;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;

public class BungeeFileManager {
    private File FOLDER, configFile;
    private Configuration configuration;

    public BungeeFileManager(AbstractModule module){
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

            configuration = YamlConfiguration.getProvider(YamlConfiguration.class).load(configFile);

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
            YamlConfiguration.getProvider(YamlConfiguration.class).save(configuration, configFile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
