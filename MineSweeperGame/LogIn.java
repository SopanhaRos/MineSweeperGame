package MineSweeperGame;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogIn {

    static ArrayList<User> userList = new ArrayList<User>(); 
    
    public static void getUserList()
    {
        String filePath = "C:\\Java_project\\exercise\\src\\MineSweeperGame\\UserInfo.txt";
        
        try {
            // Create a FileReader
            FileReader fileReader = new FileReader(filePath);
            // Wrap the FileReader in a BufferedReader for efficient reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // Read each line from the file
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Process the line as needed
                //System.out.println(line);
                String[] parts = line.split("/");
                User tmp = new User(parts[0], parts[1]);
                userList.add(tmp);
            }

            // Close the BufferedReader
            bufferedReader.close();
        } catch (IOException e) {
            // Handle IO exceptions, e.g., if the file cannot be read
            e.printStackTrace();
        }
    }
    
    public static boolean VerifyUserName(String name)
    {
       
        getUserList();        

        boolean exist = false;
        for(User i : userList)
        {
            // System.out.println(i.getName());
            if(i.getName().equals(name))
            {
                exist = true;
            }
          
        }

        if(exist==true)
        {
            System.out.println("User exist please pick different name");
            return true;
        }

        return false;
    }

    public static boolean loginUser(User loginUser){
        
        getUserList();   

        boolean exist = false;
        for(User i : userList)
        {
            if(i.equals(loginUser))
            {
                exist = true;
            }
          
        }

        if(exist==true)
        {
            System.out.println("User exist");
        }else
        {
            System.out.println("User doesn't exist");
        }
        return exist;
    }
}
