public class DatabaseLogger implements Logger{
    @Override
    public void log(String message) {
        System.out.println(message);
        System.out.println("Databaseye loglandı");
    }
}
