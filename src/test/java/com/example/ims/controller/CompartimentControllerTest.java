package com.example.ims.controller;

import com.example.ims.dto.CompartimentUpdateDTO;
import com.example.ims.model.Compartiment;
import com.example.ims.service.CompartimentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import static org.mockito.Mockito.*;


//@WebMvcTest : Indique que nous testons uniquement ce controleur.
@WebMvcTest(CompartimentController.class)
public class CompartimentControllerTest {

    //cela signifie que Spring injecte une instance de MockMvc,
    // un outil permettant de tester les contrôleurs de manière isolée, sans lancer un serveur HTTP complet.
    @Autowired
    private MockMvc mockMvc;


    // Crée un mock de CompartimentService et l'injecte dans le contexte Spring
    // (par exemple, vous pouvez spécifier que lorsqu'une méthode spécifique est appelée, elle doit retourner une certaine valeur).
    @MockBean
    private CompartimentService compartimentService;


    @Test
    public void testGetAllCompartiments() throws Exception {
        Compartiment compartiment1 = new Compartiment();
        compartiment1.setId(1);
        compartiment1.setName("Compartiment 1");

        Compartiment compartiment2 = new Compartiment();
        compartiment2.setId(2);
        compartiment2.setName("Compartiment 2");

        when(compartimentService.getAllCompartiments()).thenReturn(Arrays.asList(compartiment1, compartiment2));

        mockMvc.perform(get("/compartiments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Compartiment 1"))
                .andExpect(jsonPath("$[1].name").value("Compartiment 2"));

        // vérifier que la méthode getAllCompartiments()  a bien été appelée exactement une fois pendant l'exécution du test.
        verify(compartimentService, times(1)).getAllCompartiments();
    }

    @Test
    public void testCreateCompartiment() throws Exception {
        Compartiment compartiment = new Compartiment();
        compartiment.setId(1);
        compartiment.setName("New Compartiment");

        when(compartimentService.createCompartiment(any())).thenReturn(compartiment);

        String jsonContent = "{\"name\": \"New Compartiment\", \"capacity\": 100, \"availablePlace\": 100}";

        mockMvc.perform(post("/compartiment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Compartiment"));

        verify(compartimentService, times(1)).createCompartiment(any());
    }

    @Test
    public void testUpdateCompartiment() throws Exception {
        Compartiment compartiment = new Compartiment();
        compartiment.setId(1);
        compartiment.setName("New Compartiment");

        when(compartimentService.createCompartiment(any())).thenReturn(compartiment);

        String jsonContent = "{\"name\": \"New Compartiment\", \"capacity\": 100, \"availablePlace\": 100}";

        mockMvc.perform(post("/compartiment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Compartiment"));

        verify(compartimentService, times(1)).createCompartiment(any());
    }


    @Test
    public void testUpdateCompartimentById() throws Exception {
        // Préparation des données pour le test
        Compartiment updatedCompartiment = new Compartiment();
        updatedCompartiment.setId(1);
        updatedCompartiment.setName("Updated Compartiment");
        updatedCompartiment.setCapacity(150);
        updatedCompartiment.setAvailablePlace(100);

        // Configurer le mock pour simuler la mise à jour du compartiment
        when(compartimentService.updateCompartimentById(eq(1), any(CompartimentUpdateDTO.class)))
                .thenReturn(updatedCompartiment);

        // Préparation du contenu JSON de la requête
        String jsonContent = "{\"name\": \"Updated Compartiment\", \"capacity\": 150, \"availablePlace\": 100}";


        mockMvc.perform(put("/compartiment/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Compartiment"))
                .andExpect(jsonPath("$.capacity").value(150))
                .andExpect(jsonPath("$.availablePlace").value(100));

        verify(compartimentService).updateCompartimentById(eq(1), any(CompartimentUpdateDTO.class));
    }


    @Test
    public void testDeleteCompartimentById() throws Exception {

        Compartiment deletedCompartiment = new Compartiment();
        deletedCompartiment.setId(1);
        deletedCompartiment.setName("deleted Compartiment");
        when(compartimentService.deleteCompartimentById(eq(1)))
                .thenReturn(deletedCompartiment);

        mockMvc.perform(delete("/compartiment/1"))
                .andExpect(status().isOk());
        verify(compartimentService, times(1)).deleteCompartimentById(1);
    }

}
