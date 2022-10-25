package org.openmrs.module.basicmodule.web.controller.mailing.emailcontroller;


import com.google.gson.Gson;
import org.openmrs.module.basicmodule.web.controller.mailing.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/rest/v1/email")
public class EmailServiceController {

    @Autowired private EmailService emailService;

    @RequestMapping(method = RequestMethod.POST)
    public ResultsResponse sendMail(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message,
            @RequestParam("file") MultipartFile file) {

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setReceiver(to);
        emailDetails.setSubject(subject);
        emailDetails.setMessage(message);

        //Check if the file is empty
        ResultsResponse resultsResponse = new ResultsResponse();

        if(!file.isEmpty()){

            //Check if the file is a pdf
            if (!file.getContentType().equals("application/pdf")){
                resultsResponse.setCode(400);
                resultsResponse.setDetails("File must be a pdf");
                getResponseDetails(resultsResponse);

            }

            //Check if file size exceeds 15mb
            if(file.getSize() > 15728640){
                resultsResponse.setCode(400);
                resultsResponse.setDetails("File size must not exceed 15mb. Please send as link.");
                return getResponseDetails(resultsResponse);
            }

            //Send mail with attachment
            emailDetails.setFile(file);
            resultsResponse = emailService.sendEmailWithAttachment(emailDetails);
        }else {
            //Send mail without attachment
             resultsResponse = emailService.sendSimpleMail(emailDetails);
        }

        //Process the response from email service
        return getResponseDetails(resultsResponse);

    }

    private ResultsResponse getResponseDetails(ResultsResponse resultsResponse) {
        //Convert to json
        Gson gson = new Gson();
        return gson.fromJson(resultsResponse.toString(), ResultsResponse.class);
    }



}
