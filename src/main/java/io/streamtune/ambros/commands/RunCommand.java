package io.streamtune.ambros.commands;

import io.streamtune.ambros.entities.Command;
import io.streamtune.ambros.services.CommandService;
import io.streamtune.ambros.utils.AnsiColors;
import picocli.CommandLine;

import javax.inject.Inject;

@CommandLine.Command(name = "run", description = "Run a command")
public class RunCommand implements Runnable {
    @Inject
    private CommandService commandService;

    @CommandLine.Option(names = {"-c", "--command"}, description = "command to execute")
    String cmd;

    public RunCommand(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void run() {
        System.out.println("Executing command: " + cmd);
        Command command = commandService.execute(cmd);

        if (command.isStatus()) {
            System.out.println(command.getOutput());
            System.out.format("[" + AnsiColors.ANSI_GREEN + "%s" + AnsiColors.ANSI_RESET + "]\n", command.getId());
        } else {
            System.out.println(command.getError());
            System.out.format("[" + AnsiColors.ANSI_RED + "%s" + AnsiColors.ANSI_RESET + "]\n", command.getId());
        }
    }
}

