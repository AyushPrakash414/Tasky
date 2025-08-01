package com.tasky.Service;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.tasky.Entity.EmailDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.sendgrid.helpers.mail.objects.Personalization;

import java.io.IOException;
@Component
public class EmailService {
    @Value("${EMAIL_API_KEY}")
    private String api_key;

    public ResponseEntity<?> SendEmail(@NotNull EmailDTO Smail) {
        try {
            Email from = new Email(Smail.getFrom());
            Email to = new Email(Smail.getTo());
            String subject = Smail.getSubject();
            Content content = new Content("text/plain", Smail.getBody());

            // Build mail manually with Personalization
            Mail mail = new Mail();
            mail.setFrom(from);
            mail.setSubject(subject);
            mail.addContent(content);

            Personalization personalization = new Personalization();
            personalization.addTo(to);
            mail.addPersonalization(personalization);

            SendGrid sg = new SendGrid(getApi_key());
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            // Optional: Print response details
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());

            return ResponseEntity.ok("Email sent successfully! Status code: " + response.getStatusCode());
        } catch (IOException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while sending email: " + ex.getMessage());
        }
    }

    private String getApi_key() {
        return api_key;
    }

}
