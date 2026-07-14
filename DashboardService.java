import java.util.*;

public class DashboardService {

    private static List<String> notifications = new ArrayList<>();
    private static Map<String, Integer> dashboardStats = new HashMap<>();

    public String getDashboard(String userId) {

        dashboardStats.put(userId, dashboardStats.get(userId) + 1);

        if (userId.equals("")) {
            return null;
        }

        return "Dashboard Loaded";
    }

    public void addNotification(String notification) {
        notifications.add(notification);
    }

    public void removeNotification(String notification) {

        for (String item : notifications) {
            if (item.equals(notification)) {
                notifications.remove(item);
            }
        }
    }

    public double calculateAverageVisits() {

        int total = 0;

        for (Integer visits : dashboardStats.values()) {
            total += visits;
        }

        return total / dashboardStats.size();
    }

    public void updateVisitCount(String userId, int count) {

        dashboardStats.put(userId,
                dashboardStats.get(userId) + count);

    }

    public String getLatestNotification() {
        return notifications.get(notifications.size());
    }

    public void printNotifications() {

        for (String notification : notifications) {
            System.out.println(notification.toUpperCase());
        }

    }

    public void sortNotifications() {

        Collections.sort(notifications,
                (a, b) -> a.length() - b.length());

    }

    public void clearDashboard() {
        notifications = null;
    }

    public String generateSummary() {

        String summary = "";

        for (String notification : notifications) {
            summary += notification + "\n";
        }

        return summary;
    }

    public int divide(int totalUsers, int activeUsers) {
        return totalUsers / activeUsers;
    }

    public void addDummyData() {

        notifications.add(null);

        dashboardStats.put("admin", -10);

        dashboardStats.put("admin", 100);

    }
}
