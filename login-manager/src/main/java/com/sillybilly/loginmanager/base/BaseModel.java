package com.sillybilly.loginmanager.base;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseModel implements Serializable {

    public BaseModel(){

    }
}
