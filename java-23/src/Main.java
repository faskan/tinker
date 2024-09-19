/*
    Java 23 Features
 */
// Use --enable-preview flag to compile and run this code as it uses preview features
// Feature-1 (Preview) - Import whole java base module so that you don't have to import each module separately
import module java.base;

/// Feature-2 Now you can document your code using **markdown** syntax
// Resubmitted Feature (Third Preview) - Implicitly declared class and main method
void main() {
    // Par of Feature-1 (Preview) - You dont have to import java.util.List
    List<String> names = List.of("John", "Jennie", "Jim", "Jack", "Joe");
    usePrimitiveTypesInSwitchAndPatternMatching(2);

}

void usePrimitiveTypesInSwitchAndPatternMatching(int x) {
    // Feature - 3 (Preview) - Now you can use primitive types (byte, short, int, long, etc) in switch case
    switch (x) {
        case 1,3,5,7,9 -> System.out.println("Odd");
        case 2,4,6,8,10 -> System.out.println("Even");
        default -> System.out.println("Invalid Number");
    }
    // Feature - 3 (Preview) - Now you can use primitive types (byte, short, int, long, etc) in instanceof and pattern matching
    long y = 100;
    if(y instanceof byte b && b > 0) {
        println(b + " is byte");
    }
    if(y instanceof short s) {
        println(s + " is short");
    }


}
record User(String name){}
record Data(String someData){}
record Request(String someRequest) {}

// Scoped Values (Third Preview - minor changes)
class Server {
    public final static ScopedValue<User> LOGGED_IN_USER = ScopedValue.newInstance();
    private RestAdapter restAdapter;

    public Server(RestAdapter restAdapter) {
        this.restAdapter = restAdapter;
    }
    private void serve(Request request) {
        User loggedInUser = authenticateUser(request);
        // Scoped Values (Third Preview - minor changes)
        ScopedValue.where(LOGGED_IN_USER, loggedInUser)
                .run(() -> restAdapter.processRequest(request));
    }

    private User authenticateUser(Request request) {
        return new User("John");
    }
}
class Repository {
    public void saveData(Data data) {
        // Scoped Values (Third Preview - minor changes)
        User loggedInUser = Server.LOGGED_IN_USER.get();
        System.out.println("Saving data " + data + " for " + loggedInUser);
    }

}

class RestAdapter {
    private Repository repository;
    public RestAdapter(Repository repository) {
        this.repository = repository;
    }
    public void processRequest(Request request) {
        repository.saveData(new Data("Some Data"));
    }
}
// Structured Concurrency (Third Preview - no changes from previous preview)

// Flexible Construction Body (Second Preview - minor changes)