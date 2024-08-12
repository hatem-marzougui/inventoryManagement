package com.example.ims.service;


import com.example.ims.dao.CompartimentRepository;
import com.example.ims.dto.CompartimentRequestDTO;
import com.example.ims.model.Compartiment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompartimentServiceImpl implements CompartimentService {
    private final CompartimentRepository compartimentRepository;


    public CompartimentServiceImpl(CompartimentRepository compartimentRepository) {
        this.compartimentRepository = compartimentRepository;
    }

    @Override
    public List<Compartiment> getAllCompartiments() {
        return compartimentRepository.findAll();
    }

    @Override
    public Compartiment createCompartiment(CompartimentRequestDTO compartimentRequestDTO) {
        Compartiment compartiment = new Compartiment();
        compartiment.setName(compartimentRequestDTO.getName());
        compartiment.setCapacity(compartimentRequestDTO.getCapacity());
        compartiment.setAvailablePlace(compartimentRequestDTO.getAvailablePlace());

        return compartimentRepository.save(compartiment);
    }
}
