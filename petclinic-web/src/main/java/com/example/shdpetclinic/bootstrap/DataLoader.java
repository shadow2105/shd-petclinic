package com.example.shdpetclinic.bootstrap;

import com.example.shdpetclinic.model.Owner;
import com.example.shdpetclinic.model.Pet;
import com.example.shdpetclinic.model.PetType;
import com.example.shdpetclinic.model.Vet;
import com.example.shdpetclinic.services.OwnerService;
import com.example.shdpetclinic.services.PetTypeService;
import com.example.shdpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType cat = new PetType();
        cat.setName("Cat");

        PetType savedCatPetType = petTypeService.save(cat);

        PetType dog = new PetType();
        cat.setName("Dog");

        PetType savedDogPetType = petTypeService.save(dog);

        System.out.println("<<< Loaded Pet Types ! >>>");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("23 King St W");
        owner1.setCity("Toronto");
        owner1.setTelephone("456098123");

        Pet ow1Pet1 = new Pet();
        ow1Pet1.setPetType(savedDogPetType);
        ow1Pet1.setOwner(owner1);
        ow1Pet1.setBirthDate(LocalDate.now());
        ow1Pet1.setName("Duke");
        owner1.getPets().add(ow1Pet1);


        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Jones");
        owner2.setAddress("100 York Mills Rd");
        owner2.setCity("Toronto");
        owner2.setTelephone("345987126");

        Pet ow2Pet1 = new Pet();
        ow2Pet1.setPetType(savedCatPetType);
        ow2Pet1.setOwner(owner2);
        ow2Pet1.setBirthDate(LocalDate.now());
        ow2Pet1.setName("Lucy");
        owner1.getPets().add(ow2Pet1);

        ownerService.save(owner2);

        System.out.println("<<< Loaded Owners ! >>>");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Williams");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("<<< Loaded Vets ! >>>");
    }
}
