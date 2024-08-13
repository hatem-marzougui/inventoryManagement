package com.example.ims.service;


import com.example.ims.dao.CompartimentRepository;
import com.example.ims.dto.CompartimentCreateDTO;
import com.example.ims.dto.CompartimentUpdateDTO;
import com.example.ims.model.Compartiment;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

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
    public Compartiment createCompartiment(CompartimentCreateDTO compartimentCreateDTO) {
        Compartiment compartiment = new Compartiment();
        compartiment.setName(compartimentCreateDTO.getName());
        compartiment.setCapacity(compartimentCreateDTO.getCapacity());
        compartiment.setAvailablePlace(compartimentCreateDTO.getAvailablePlace());

        return compartimentRepository.save(compartiment);
    }

    @Override
    public Compartiment deleteCompartimentById(Integer id){
        Compartiment compartiment = compartimentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Compartiment with id " + id + " not found"));

        compartimentRepository.delete(compartiment);
        return compartiment;
    }

    @Override
    public Compartiment updateCompartimentById(Integer id, CompartimentUpdateDTO compartimentUpdateDTO){
        Compartiment compartiment = compartimentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Compartiment with id " + id + " not found"));

        // Update fields
        if (compartimentUpdateDTO.getName() != null) {
            compartiment.setName(compartimentUpdateDTO.getName());
        }
        if (compartimentUpdateDTO.getCapacity() != null) {
            compartiment.setCapacity(compartimentUpdateDTO.getCapacity());
        }
        if (compartimentUpdateDTO.getAvailablePlace() != null) {
            compartiment.setAvailablePlace(compartimentUpdateDTO.getAvailablePlace());
        }

        return compartimentRepository.save(compartiment);
    }
}
