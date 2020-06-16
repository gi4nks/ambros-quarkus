package io.streamtune.ambros.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;
import java.util.UUID;

@Entity
@Cacheable
@NamedQueries({
        @NamedQuery(name = "Command.truncate",
                query = "DELETE FROM Command"),
        @NamedQuery(name = "Command.findByStatus",
                query = "SELECT c FROM Command c WHERE c.status = :status ORDER by c.createdAt DESC"),
        @NamedQuery(name = "Command.findAll",
                query = "SELECT c FROM Command c ORDER by c.createdAt DESC")
})
public class Command extends AbstractBaseEntity {

    @Column(length = 1024)
    private String command;

    @Column()
    private boolean status;

    @Lob
    private String output;

    @Lob
    private String error;

    public Command() {
    }

    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Command{" +
                "id='" + getId() + '\'' +
                ", command='" + command + '\'' +
                ", status=" + status +
                ", createdAt='" + getCreatedAt() + '\'' +
                ", terminatedAt='" + getTerminatedAt() + '\'' +
                '}';
    }
}
