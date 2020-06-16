package io.streamtune.ambros.utils;


import io.quarkus.arc.config.ConfigProperties;

import java.io.File;
import java.util.Optional;

@ConfigProperties(prefix = "ambros")
public class AmbrosConfiguration {

    private String repositoryDirectory = "~/.ambros";
    private String repositoryFile = "ambros.db";
    private int lastCountDefault = 10;

    public String getRepositoryDirectory() {
        return repositoryDirectory;
    }

    public String getRepositoryFile() {
        return repositoryFile;
    }

    public int getLastCountDefault() {
        return lastCountDefault;
    }

    public void setRepositoryDirectory(String repositoryDirectory) {
        this.repositoryDirectory = repositoryDirectory;
    }

    public void setRepositoryFile(String repositoryFile) {
        this.repositoryFile = repositoryFile;
    }

    public void setLastCountDefault(int lastCountDefault) {
        this.lastCountDefault = lastCountDefault;
    }

    public String getFullRepositoryName() {
        return this.repositoryDirectory + File.separator + this.repositoryFile;
    }

    @Override
    public String toString() {
        return "AmbrosConfiguration{" +
                "repositoryDirectory='" + repositoryDirectory + '\'' +
                ", repositoryFile='" + repositoryFile + '\'' +
                ", lastCountDefault=" + lastCountDefault +
                '}';
    }
}
