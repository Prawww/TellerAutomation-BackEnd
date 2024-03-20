package com.example.Teller_Automation.BACKEND.AdminModule.Gl;

import com.example.Teller_Automation.BACKEND.CustomerModule.Utils.EntityResponse;

public interface GlService {

    EntityResponse<?> findGl(Long id);

    EntityResponse<?> findAllGl();
}
