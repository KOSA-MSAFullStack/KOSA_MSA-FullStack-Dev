package ch4_6_SingletonPattern;

public final class DbConfigSingleton {
   private final String hostName;
   private final String dbName;
   private static final DbConfigSingleton instance = new DbConfigSingleton();
   
   // Private constructor to prevent instantiation from other classes
   private DbConfigSingleton() {
       // Values are typically loaded from a configuration file in practice
       hostName = "dbhost.example.com";
       dbName = "exampleDB"; // Example value for dbName
   }
   
   // Public method to provide access to the singleton instance
   public static DbConfigSingleton getInstance() {
       return instance;
   }
   // Getter for hostName
   public String getHostName() {
       return hostName;
   }
   // Getter for dbName
   public String getDbName() {
       return dbName;
   }
   
   // Main method to demonstrate the Singleton functionality
   public static void main(String[] args) {
       DbConfigSingleton config = DbConfigSingleton.getInstance();
       System.out.println("Host Name: " + config.getHostName());
       System.out.println("Database Name: " + config.getDbName());
   }
}
