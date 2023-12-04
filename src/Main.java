import Front.LogInScreen;
import sql.DatabaseLocal;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        try {
            Connection connection = DatabaseLocal.getConnection();
            LogInScreen logInScreen = new LogInScreen(connection);
        }catch (Exception e){
            System.out.println(e);
        }


    }
}