package com.example.ims.service;

import com.example.ims.dto.CompartimentRequestDTO;
import com.example.ims.model.Compartiment;

import java.util.List;

public interface CompartimentService {

    List<Compartiment> getAllCompartiments();

    Compartiment createCompartiment(CompartimentRequestDTO compartimentRequestDTO);
}
