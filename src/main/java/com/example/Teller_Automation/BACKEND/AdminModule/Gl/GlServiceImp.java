package com.example.Teller_Automation.BACKEND.AdminModule.Gl;

import com.example.Teller_Automation.BACKEND.CustomerModule.Utils.EntityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GlServiceImp implements GlService{

    private final GlRepo glRepo;

    public GlServiceImp(GlRepo glRepo) {
        this.glRepo = glRepo;
    }


    @Override
    public EntityResponse<?> findGl(Long id) {
        EntityResponse<Gl> res = new EntityResponse<>();
        try{
            if(id <=0){
                res.setMessage("Please enter valid id");
                res.setEntity(null);
                res.setStatusCode(HttpStatus.BAD_REQUEST.value());
            }
            else{
                Optional<Gl> gl = glRepo.findById(id);

                if(gl.isPresent()){
                    Gl g = gl.get();
                    res.setMessage("Gl found successfully");
                    res.setEntity(g);
                    res.setStatusCode(HttpStatus.FOUND.value());
                }
                else{

                    res.setMessage("Gl not found");
                    res.setEntity(null);
                    res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                }
            }
        }
        catch(Exception e){

            res.setMessage("Error encountered");
            res.setEntity(null);
            res.setStatusCode(HttpStatus.BAD_REQUEST.value());
    }
        return res;
}
@Override
    public EntityResponse<?> findAllGl() {
        EntityResponse<List<Gl>> res = new EntityResponse<>();
        try{

                List<Gl> gl = glRepo.findAll();

                if(!gl.isEmpty()){
                    res.setMessage("Lists of Gls found successfully");
                    res.setEntity(gl);
                    res.setStatusCode(HttpStatus.FOUND.value());
                }
                else{

                    res.setMessage("Lists of Gls not found");
                    res.setEntity(null);
                    res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                }
            }

        catch(Exception e){

            res.setMessage("Error encountered");
            res.setEntity(null);
            res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        }
        return res;
    }
}
