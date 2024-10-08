package es.juuangarciac.vitasnap.java.user;


import es.juuangarciac.vitasnap.user.domain.User;
import es.juuangarciac.vitasnap.user.services.EmailService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmailFakeService implements EmailService {

    @Override
    public boolean sendRegistrationEmail(User user) {

        String subject = "Welcome";
        String body = "You should active your account. "
                + "Go to http://localhost:8080/useractivation "
                + "and introduce your mail and the following code: "
                + user.getRegisterCode();

        try {
            System.out.println("From: app (testing)");
            System.out.println("To: " + user.getEmail());
            System.out.println("Subject: " + subject);
            System.out.println("Body: " + body);

            int secondsToSleep = 5;
            Thread.sleep(secondsToSleep * 1000);
            System.out.println("Email send simulation done!");
            return true;
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
