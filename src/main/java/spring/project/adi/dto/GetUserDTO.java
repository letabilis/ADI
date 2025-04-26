package spring.project.adi.dto;

import java.time.Instant;
import java.util.UUID;

public record GetUserDTO(UUID id, String name, String email, Instant updateTimestamp) {
    
}
