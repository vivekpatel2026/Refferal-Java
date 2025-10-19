package com.refrralApp.refrral.Services;

import com.refrralApp.refrral.dto.CreateApplicationRequest;
import com.refrralApp.refrral.repository.ApplicationRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ApplicationServices {

    @Autowired
    private ApplicationRespository applicationRespository;

    public Map<?,?>  createApplication(CreateApplicationRequest application){
        return null;
    }


}
