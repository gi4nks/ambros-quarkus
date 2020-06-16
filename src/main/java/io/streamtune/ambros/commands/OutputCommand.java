package io.streamtune.ambros.commands;

import io.streamtune.ambros.entities.Command;
import io.streamtune.ambros.services.CommandService;
import io.streamtune.ambros.utils.AnsiColors;
import picocli.CommandLine;

import javax.inject.Inject;
import java.util.Optional;

@CommandLine.Command(name = "output", description = "Show the output of a command")
public class OutputCommand implements Runnable {
    @Inject
    private CommandService commandService;

    @CommandLine.Option(names = {"-i", "--id"}, description = "the id of the command to show output")
    String id;

    public OutputCommand(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void run() {
        Optional<Command> command = commandService.findById(id);

        if (command.isPresent()) {
            Command cmd = command.get();
            if (cmd.isStatus()) {
                System.out.println(cmd.getOutput());
                System.out.format("[" + AnsiColors.ANSI_GREEN + "%s" + AnsiColors.ANSI_RESET + "]\n", cmd.getId());
            } else {
                System.out.println(cmd.getError());
                System.out.format("[" + AnsiColors.ANSI_RED + "%s" + AnsiColors.ANSI_RESET + "]\n", cmd.getId());
            }
        }
    }
}

