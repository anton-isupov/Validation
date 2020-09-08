import user.User;

public class Main {

    public static void main(String[] args) {
        User user = null;
        try {
            user = User.newBuilder()
                    .name("test123")
                    .email("tes")
                    .password("121212121212")
                    .validateAndBuild();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("User" + user);

    }
}
