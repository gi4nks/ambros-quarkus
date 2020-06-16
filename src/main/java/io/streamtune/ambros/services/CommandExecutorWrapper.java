package io.streamtune.ambros.services;

import io.streamtune.ambros.entities.Command;
import io.streamtune.ambros.utils.StreamGobbler;
import io.streamtune.ambros.utils.Utilities;

import java.util.Date;

public abstract class CommandExecutorWrapper {

    public static void execute(Command cmd) {
        try {
            String[] cmds = {};
            if (Utilities.isWindows()) {
                cmds = new String[] { "cmd.exe", "/c", cmd.getCommand() };
            } else {
                cmds = new String[] { "bash", "-c", cmd.getCommand() };
            }
            Process p = Runtime.getRuntime().exec(cmds);

            StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");
            StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream(), "OUTPUT");

            outputGobbler.start();
            errorGobbler.start();

            int result = p.waitFor();

            cmd.setStatus(result == 0);
            cmd.setOutput(outputGobbler.getContent());
            cmd.setError(errorGobbler.getContent());
        } catch (Exception e) {
            cmd.setStatus(false);
            cmd.setOutput(null);
        } finally {
            cmd.setTerminatedAt(new Date());
        }
    }
}
