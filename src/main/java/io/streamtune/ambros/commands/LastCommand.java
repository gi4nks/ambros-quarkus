package io.streamtune.ambros.commands;

import io.streamtune.ambros.entities.Command;
import io.streamtune.ambros.formatters.LastCommandOutputFormatter;
import io.streamtune.ambros.services.CommandService;
import io.streamtune.ambros.utils.AmbrosConfiguration;
import picocli.CommandLine;

import javax.inject.Inject;
import java.util.List;

@CommandLine.Command(name = "last", description = "Last command executed")
public class LastCommand implements Runnable {
    @Inject
    private CommandService commandService;

    @Inject
    private AmbrosConfiguration ambrosConfiguration;

    @CommandLine.Option(names = {"-c", "--count"}, description = "The number of commands to see")
    private int count;

    public LastCommand(CommandService commandService, AmbrosConfiguration ambrosConfiguration) {
        this.commandService = commandService;
        this.ambrosConfiguration = ambrosConfiguration;
        this.count = this.ambrosConfiguration.getLastCountDefault();
    }

    @Override
    public void run() {
        List<Command> cmds = commandService.findMax(this.count);

        LastCommandOutputFormatter formatter = new LastCommandOutputFormatter(cmds);
        formatter.print();
    }
}

