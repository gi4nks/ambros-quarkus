package io.streamtune.ambros.services;

import io.streamtune.ambros.entities.Command;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CommandService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public List<Command> findMax(int top) {
        return entityManager.createNamedQuery("Command.findAll")
                .setMaxResults(top)
                .getResultList();
    }

    @Transactional
    public List<Command> findByStatus(int top, boolean status) {
        return entityManager.createNamedQuery("Command.findByStatus")
                .setParameter("status", status).setMaxResults(top)
                .getResultList();
    }

    @Transactional
    public Optional<Command> findById(String id) {
        Command cmd = entityManager.find(Command.class, id);
        return cmd != null ? Optional.of(cmd) : Optional.empty();
    }

    @Transactional
    public List<Command> findAll() {
        return entityManager.createNamedQuery("Command.findAll")
                .getResultList();
    }

    @Transactional
    public void create(String command) {
        Command cmd = new Command();
        cmd.setCommand(command);
        cmd.setStatus(true);
        cmd.setOutput(null);
        cmd.setTerminatedAt(new Date());

        entityManager.persist(cmd);
    }

    @Transactional
    public void delete(String id) {
        Command cmd = entityManager.find(Command.class, id);
        if (cmd!= null) {
            entityManager.remove(cmd);
        }
    }

    @Transactional
    public void truncateAll() {
        entityManager.createNamedQuery("Command.truncate").executeUpdate();
    }

    @Transactional
    public Command execute(String command) {
        Command cmd = new Command();
        cmd.setCommand(command);

        CommandExecutorWrapper.execute(cmd);

        entityManager.persist(cmd);

        return cmd;
    }
}
