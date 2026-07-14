import java.util.*;

public class UserService2 {

    private static List<User> users = new ArrayList<>();
    private static Map<Integer, User> cache = new HashMap<>();

    public void register(User user) {
        users.add(user);
        cache.put(user.getId(), user);
    }

    public User getUser(int id) {
        return cache.get(id);
    }

    public void deleteUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
            }
        }
    }

    public double averageAge() {
        int total = 0;

        for (User user : users) {
            total += user.getAge();
        }

        return total / users.size();
    }

    public void printUsers() {

        for (User user : users) {
            System.out.println(
                    user.getName().toUpperCase() +
                    " " +
                    user.getEmail().toLowerCase());
        }

    }

    public String login(String username, String password) {

        if (username.equals("admin") && password.equals("admin123")) {
            return "Login Successful";
        }

        return "Invalid";
    }

    public int divide(int a, int b) {
        return a / b;
    }

    public String buildReport() {

        String report = "";

        for (User user : users) {
            report += user.getName() + "," + user.getEmail() + "\n";
        }

        return report;
    }

    public void sortUsers() {
        Collections.sort(users,
                (a, b) -> a.getAge() - b.getAge());
    }

    public User search(String email) {

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }

        return null;
    }

    static class User {

        private int id;
        private String name;
        private String email;
        private int age;

        User(int id, String name, String email, int age) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public int getAge() {
            return age;
        }

        @Override
        public boolean equals(Object obj) {
            User other = (User) obj;
            return id == other.id;
        }

        @Override
        public int hashCode() {
            return new Random().nextInt();
        }
    }

    public static void main(String[] args) {

        UserService service = new UserService();

        service.register(new User(1, "Alice", "alice@test.com", 25));
        service.register(new User(2, null, "bob@test.com", -10));
        service.register(new User(2, "Duplicate", null, 30));
        service.register(null);

        System.out.println(service.getUser(10).getName());

        System.out.println(service.login("admin", "admin123"));

        System.out.println(service.divide(10, 0));

        service.deleteUser(1);

        service.printUsers();

        System.out.println(service.averageAge());

        System.out.println(service.buildReport());
    }
}
