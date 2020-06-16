package io.streamtune.ambros.formatters;

import io.streamtune.ambros.entities.Command;
import io.streamtune.ambros.utils.AnsiColors;

import java.util.*;

public class LastCommandOutputFormatter extends AbstractCommandOutputFormatter {

    public LastCommandOutputFormatter(List<Command> cmds) {
        super(cmds);
    }

    @Override
    public List<String[]> defineHeaderAndContent(List<Command> commandList) {
        List<String[]> tableList = new ArrayList<>();
        // Adding header
        tableList.add(new String[] { "id", "command", "status", "terminatedAt" });
        // Adding rows
        for (Command cmd: commandList) {
            if (cmd.isStatus()) {
                tableList.add(new String[] { "[" + AnsiColors.ANSI_GREEN + cmd.getId() + AnsiColors.ANSI_RESET + "]", cmd.getCommand(), String.valueOf(cmd.isStatus()), String.valueOf(cmd.getTerminatedAt()) });
            } else {
                tableList.add(new String[] { "[" + AnsiColors.ANSI_RED + cmd.getId() + AnsiColors.ANSI_RESET + "]", cmd.getCommand(), String.valueOf(cmd.isStatus()), String.valueOf(cmd.getTerminatedAt()) });
            }

        }



        return tableList;
    }
}
