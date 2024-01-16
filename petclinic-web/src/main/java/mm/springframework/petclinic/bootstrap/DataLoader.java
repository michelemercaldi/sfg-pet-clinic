package mm.springframework.petclinic.bootstrap;

import mm.springframework.petclinic.model.Owner;
import mm.springframework.petclinic.model.Pet;
import mm.springframework.petclinic.model.PetType;
import mm.springframework.petclinic.model.Vet;
import mm.springframework.petclinic.services.OwnerService;
import mm.springframework.petclinic.services.PetTypeService;
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

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

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

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
