public class CustomerManager {
    private Logger logger;

    public CustomerManager(Logger logger) {
        this.logger = logger;
    }

    public void add(){
        System.out.println("Müşteri eklendi.");
        logger.log("Müşteri");

    }
}
