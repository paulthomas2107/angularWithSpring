package io.getarrays.server.service.implementation;

import static io.getarrays.server.enumerations.Status.*;
import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepo;
import io.getarrays.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.lang.Boolean.TRUE;
import static org.springframework.data.domain.PageRequest.*;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }


    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = new Server();
        server = serverRepo.findByIpAddress(ipAddress);
        log.info("Passed 001");
        InetAddress address = InetAddress.getByName(ipAddress);
        log.info("Passed 002");

        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        log.info("Passed 003");
        serverRepo.save(server);
        log.info("Passed 004");
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepo.findAll(of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by id: {}", id);
        serverRepo.deleteById(id);
        return TRUE;
    }

    private String setServerImageUrl() {
        String[] imageNames = {"1.png", "2.png", "3.png", "4.png"};
        return ServletUriComponentsBuilder.
                fromCurrentContextPath().
                path("/server/image/"+imageNames[new Random().nextInt(4)]).toUriString();
    }
}
