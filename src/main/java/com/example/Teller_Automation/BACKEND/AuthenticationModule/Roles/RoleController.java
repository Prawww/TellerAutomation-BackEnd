package com.example.Teller_Automation.BACKEND.AuthenticationModule.Roles;

import com.example.Teller_Automation.BACKEND.AuthenticationModule.utils.Shared.EntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/role")
@CrossOrigin
public class RoleController {
    @Autowired
    RoleService roleService;
    @PostMapping("/add")
    public EntityResponse add(@RequestBody Role role){
        return roleService.add(role);
    }
}
