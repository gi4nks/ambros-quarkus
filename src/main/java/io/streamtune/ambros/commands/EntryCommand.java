package io.streamtune.ambros.commands;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine;

@TopCommand
@CommandLine.Command(mixinStandardHelpOptions = true,  version = "1.0",
        subcommands = {
        OutputCommand.class, RecallCommand.class,
        ConfigurationCommand.class,
        LastCommand.class, ReviveCommand.class, RunCommand.class})
public class EntryCommand {
}