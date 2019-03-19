package com.erickson.lostandfound.Repositories;

import com.erickson.lostandfound.Models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);
}
