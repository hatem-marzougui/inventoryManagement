package com.example.ims.service;

import com.example.ims.dto.CompartimentCreateDTO;
import com.example.ims.dto.CompartimentUpdateDTO;
import com.example.ims.model.Compartiment;

import java.util.List;

public interface CompartimentService {

    List<Compartiment> getAllCompartiments();

    Compartiment createCompartiment(CompartimentCreateDTO compartimentCreateDTO);

    Compartiment deleteCompartimentById(Integer id);

    Compartiment updateCompartimentById(Integer id, CompartimentUpdateDTO compartimentUpdateDTO);
}
