package mm.springframework.petclinic.bootstrap;

import mm.springframework.petclinic.model.*;
import mm.springframework.petclinic.services.OwnerService;
import mm.springframework.petclinic.services.PetTypeService;
import mm.springframework.petclinic.services.SpecialtyService;
import mm.springframework.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by jt on 7/25/18.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }


    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("dentistry");
        Specialty savedDenstry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("via di qua 1");
        owner1.setCity("Gotham City");
        owner1.setTelephone("123123123");

        Pet pluto = new Pet();
        pluto.setPetType(savedDogType);
        pluto.setOwner(owner1);
        pluto.setBirthDate(LocalDate.now());
        pluto.setName("Pluto");
        owner1.addPet(pluto);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("via di la 3");
        owner2.setCity("Topolinia");
        owner2.setTelephone("456456456");

        Pet kitty = new Pet();
        kitty.setPetType(savedCatType);
        kitty.setOwner(owner2);
        kitty.setBirthDate(LocalDate.now());
        kitty.setName("Kitty");
        owner2.addPet(kitty);

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.addSpecialty(radiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.addSpecialty(surgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
