package spring.project.adi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import spring.project.adi.model.User;
import spring.project.adi.dto.CreateUserDTO;
import spring.project.adi.dto.GetUserDTO;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    User toEntity(CreateUserDTO createUserDTO);

    GetUserDTO toDTO(User user);
}
