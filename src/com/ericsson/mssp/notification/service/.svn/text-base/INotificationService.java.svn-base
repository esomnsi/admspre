
package com.ericsson.mssp.notification.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ericsson.mssp.common.dto.NotificationDTO;


public interface INotificationService {
    /*public static final int NOTIFICATION_TYPE_APPROVED=1;
    public static final int NOTIFICATION_TYPE_REJECTED=2;*/
    
    public static final Integer NOTIFICATION_TYPE_SUBMIT=1;
    public static final Integer NOTIFICATION_TYPE_APPROVAL_REQUEST=2;
    public static final Integer NOTIFICATION_TYPE_REJECTION_REQUEST=3;
    public static final Integer NOTIFICATION_TYPE_APPROVED=4;
    public static final Integer NOTIFICATION_TYPE_WAITING_PREVIOUS_APPROVAL=5;
    
    public static final String SUBMIT_MSG="The Solution with id ${1} has been created in PRE application by ${2} and it is pending for approval from ${3}. Please have a look and act accordingly.<br><br>";
    public static final String APPROVAL_REQUEST_MSG="The Solution with id ${1} has been approved by ${2} and it is pending for approval from ${3}.Please have a look and act accordingly.< <br>";
    public static final String REJECTED_MSG="The Solution with id ${1} has been rejected by ${2}.<br><br>";
    public static final String APPROVED_MSG="The Solution with id ${1} has been approved.<br><br>";
    public static final String LINK_MYACTION=" To visit the application please click on the following link<br>Link to ADM PRE : <a href=\"${Link}\">${Link}</a><br><br>";
    public static final String WAITING_PREVIOUS_APPROVAL="Previous Level approval not done, please visit again.<br><br>";
    public static final String HEADER_MSG="Hi,<br><br>A Warm Welcome to You! <br><br>";
    public static final String FOOTER_MSG="Thanks & Regards,<br><br> ADM PRE Admin Team<br>Ericsson BUGS CSI<br><a href=\"www.ericsson.com\">www.ericsson.com</a><br><br>";
    
	//public void sendNotification(String receiver,int notificationType, int solutionId) throws MessagingException;
    public void sendNotification(NotificationDTO dto) throws MessagingException;

}
