package org.openmrs.module.basicmodule.web.controller.mailing.service;

import org.openmrs.module.basicmodule.web.controller.mailing.emailcontroller.EmailDetails;
import org.openmrs.module.basicmodule.web.controller.mailing.emailcontroller.ResultsResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

@Service
public class EmailService {

    @Autowired private JavaMailSender javaMailSender;

    // To send a simple email
    public ResultsResponse sendSimpleMail(EmailDetails details) {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            // Setting up necessary details
            mailMessage.setTo(details.getReceiver());
            mailMessage.setText(details.getMessage());
            mailMessage.setSubject(details.getSubject());
            // Sending the mail
            javaMailSender.send(mailMessage);
            return new ResultsResponse(200, "Email sent successfully");
        } catch (Exception e) {
            return new ResultsResponse(400, "Email could not be sent");
        }
    }


    //Send email with attachment
    public ResultsResponse sendEmailWithAttachment(EmailDetails emailDetails) {

        try{

            String emailTo = emailDetails.getReceiver();
            String subject = emailDetails.getSubject();
            String message = emailDetails.getMessage();
            MultipartFile file = emailDetails.getFile();

            javaMailSender.send(mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                messageHelper.setTo(emailTo);
                messageHelper.setSubject(subject);
                messageHelper.setText(message);

                String attachName = file.getOriginalFilename();
                if (attachName != null){
                    messageHelper.addAttachment(attachName, file);
                }
            });
            return new ResultsResponse(200, "Email sent successfully");

        }catch (Exception exception){
            return new ResultsResponse(400, "Email could not be sent");
        }

    }


}
