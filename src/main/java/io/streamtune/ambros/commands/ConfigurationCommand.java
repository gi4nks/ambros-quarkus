package io.streamtune.ambros.commands;


import io.streamtune.ambros.utils.AmbrosConfiguration;
import picocli.CommandLine;

import javax.inject.Inject;

@CommandLine.Command(name = "configuration", description = "Returns the current configuration of Ambros")
public class ConfigurationCommand implements Runnable {
    @Inject
    AmbrosConfiguration ambrosConfiguration;

    public ConfigurationCommand() {
    }

    @Override
    public void run() {
        System.out.println("Ambros Configuration command output:");
        System.out.println(ambrosConfiguration.toString());
    }

}