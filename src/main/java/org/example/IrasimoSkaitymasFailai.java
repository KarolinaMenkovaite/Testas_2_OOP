package org.example;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IrasimoSkaitymasFailai {

    ObjectMapper mapper;

    public IrasimoSkaitymasFailai(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    void irasytaIFaila(List<Zmogus> zmogus) {
        String failoPav = "Zmones.json";
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File file = new File(failoPav);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            mapper.writeValue(file, zmogus);
        } catch (IOException e) {
            System.out.printf("Neimanoma sukurti failo %s: %s%n:", failoPav, e.getMessage());
        }
    }

    List<Zmogus> skaitytiFaila() {

        String failoPav = "Persons.json";
        List<Zmogus> zmogus = new ArrayList<>();
        File file = new File(failoPav);
        if (!file.exists()) {
            return zmogus;
        }
        try {
            zmogus = mapper.readValue(file, new TypeReference<>() {
            });
            return zmogus;

        } catch (IOException e) {
            System.out.printf("Can't read file %s: %s%n", failoPav, e.getMessage());

        }
        return zmogus;
    }
}
