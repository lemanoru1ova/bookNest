package azbn.booknest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookNestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookNestApplication.class, args);

        System.out.println("BookNestApplication started successfully!");
        System.out.println("Visit http://localhost:8080/swagger-ui/index.html for API documentation.");

        System.out.println("There is nothing new in this branch, but you can check the previous commits for changes.");


    }

}
