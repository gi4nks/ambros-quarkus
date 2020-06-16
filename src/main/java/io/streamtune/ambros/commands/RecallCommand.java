package io.streamtune.ambros.commands;

import io.streamtune.ambros.entities.Command;
import io.streamtune.ambros.services.CommandService;
import io.streamtune.ambros.utils.AnsiColors;
import picocli.CommandLine;

import javax.inject.Inject;
import java.util.Optional;

@CommandLine.Command(name = "recall", description = "Recall an executed command!")
public class RecallCommand implements Runnable {
    @Inject
    private CommandService commandService;

    @CommandLine.Option(names = {"-i", "--id"}, description = "the id of the command to recall")
    String id;

    public RecallCommand(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void run() {
        System.out.println("Executing command: " + id);
        Optional<Command> command = commandService.findById(id);

        if (command.isPresent()) {
            Command cmd = command.get();

            Command clone = commandService.execute(cmd.getCommand());

            if (clone.isStatus()) {
                System.out.println(clone.getOutput());
                System.out.format("[" + AnsiColors.ANSI_GREEN + "%s" + AnsiColors.ANSI_RESET + "]\n", clone.getId());
            } else {
                System.out.println(clone.getError());
                System.out.format("[" + AnsiColors.ANSI_RED + "%s" + AnsiColors.ANSI_RESET + "]\n", clone.getId());
            }
        }
    }
}

