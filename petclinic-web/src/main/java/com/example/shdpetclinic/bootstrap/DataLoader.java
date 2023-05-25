package com.example.shdpetclinic.bootstrap;

import com.example.shdpetclinic.model.*;
import com.example.shdpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType cat = new PetType();
        cat.setName("Cat");

        PetType savedCatPetType = petTypeService.save(cat);

        PetType dog = new PetType();
        cat.setName("Dog");

        PetType savedDogPetType = petTypeService.save(dog);

        System.out.println("<<< Loaded Pet Types ! >>>");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiologySpeciality = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        radiology.setDescription("Surgery");
        Speciality savedSurgerySpeciality = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        radiology.setDescription("Dentistry");
        Speciality savedDentistrySpeciality = specialityService.save(dentistry);


        System.out.println("<<< Loaded Specialities ! >>>");

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

        Visit ow2Pet1Visit1 = new Visit();
        ow2Pet1Visit1.setDate(LocalDate.now());
        ow2Pet1Visit1.setDescription("Sneezing Cat");
        ow2Pet1Visit1.setPet(ow2Pet1);
        ow2Pet1.getVisits().add(ow2Pet1Visit1);

        ownerService.save(owner2);

        System.out.println("<<< Loaded Owners ! >>>");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Williams");
        vet1.getSpecialities().add(savedRadiologySpeciality);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgerySpeciality);

        vetService.save(vet2);

        System.out.println("<<< Loaded Vets ! >>>");
    }
}
