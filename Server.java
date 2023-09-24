package com.yaswanth
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "servers")
public class Server {
    @Id
    private String id;
    private String name;
    private String language;
    private String framework;
}
package com.yaswanth 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servers")
public class ServerController {
    @Autowired
    private ServerRepository serverRepository;

    // GET all servers or a specific server by ID
    @GetMapping
    public List<Server> getAllServers(@RequestParam(required = false) String id) {
        if (id != null) {
            return List.of(serverRepository.findById(id).orElse(null));
        } else {
            return serverRepository.findAll();
        }
    }

    // PUT a new server
    @PutMapping
    public Server addServer(@RequestBody Server server) {
        return serverRepository.save(server);
    }

    // DELETE a server by ID
    @DeleteMapping("/{id}")
    public void deleteServer(@PathVariable String id) {
        serverRepository.deleteById(id);
    }

    // GET servers by name
    @GetMapping("/findByName")
    public Server getServersByName(@RequestParam String name) {
        return serverRepository.findByNameContaining(name);
    }
}
Upload in servercontroller.java
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServerRepository extends MongoRepository<Server, String> {
    Server findByNameContaining(String name);
}
