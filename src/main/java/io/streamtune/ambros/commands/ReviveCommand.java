package io.streamtune.ambros.commands;

import io.streamtune.ambros.services.CommandService;
import picocli.CommandLine;

import javax.inject.Inject;

@CommandLine.Command(name = "revive", description = "Deletes all of your stored commands")
public class ReviveCommand implements Runnable {
    @Inject
    private CommandService commandService;

    public ReviveCommand(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void run() {
        System.out.println("Truncating entire ambros db");
        commandService.truncateAll();
        System.out.println("Done!");
    }
}

