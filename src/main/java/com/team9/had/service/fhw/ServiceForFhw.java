package com.team9.had.service.fhw;

import com.team9.had.customModel.fhw.ModelForFhw;

import java.io.Serializable;

public interface ServiceForFhw {

    Serializable sync(ModelForFhw modelForFhw, String role);

}
