package mm.springframework.petclinic;

public class MyController {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }
}
