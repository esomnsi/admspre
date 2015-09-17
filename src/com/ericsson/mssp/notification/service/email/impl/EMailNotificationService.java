
package com.ericsson.mssp.notification.service.email.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


import com.ericsson.mssp.common.dto.NotificationDTO;
import com.ericsson.mssp.notification.service.INotificationService;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;


public class EMailNotificationService  implements INotificationService {

	private MailSender mailSender;
	private SimpleMailMessage templateMessage;

	public MailSender getMailSender() {
		return mailSender;
	}

	public SimpleMailMessage getTemplateMessage() {
		return templateMessage;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ericsson.email.intr.IMailManager#sendEmail()
	 */
	
	/*public void sendNotification(String mailTo, int notificationType, int solutionId) throws MessagingException {
		JavaMailSenderImpl sender = (JavaMailSenderImpl) getMailSender();
		// creating mime message here
		MimeMessage message = sender.createMimeMessage();
		SimpleMailMessage msg = new SimpleMailMessage(getTemplateMessage());

		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(mailTo);
		//helper.setTo("sibayan.adhikary@ericsson.com");
		helper.setFrom(msg.getFrom());
		//helper.setCc(msg.getCc());
		String msgContent="";
		helper.setSubject(msg.getSubject());
		if(notificationType==INotificationService.NOTIFICATION_TYPE_APPROVED){
			msgContent=INotificationService.APPROVAL_MSG;
			msgContent=msgContent.replace("${1}",""+solutionId);
			//msgContent=msgContent.replaceAll("${2}",""+mailTo);
			//msgContent+="Mail sent to "+mailTo;
			helper.setText(msgContent, true);
		}else if(notificationType==INotificationService.NOTIFICATION_TYPE_REJECTED){
			msgContent=INotificationService.REJECTED_MSG;
			msgContent=msgContent.replace("${1}",""+solutionId);
			helper.setText(msgContent, true);
			//msgContent+="Mail sent to "+mailTo;
		}
		System.out.println(msgContent);
		sender.send(message);

	}*/
	
	public void sendNotification(NotificationDTO dto) throws MessagingException {
		JavaMailSenderImpl sender = (JavaMailSenderImpl) getMailSender();
		// creating mime message here
		MimeMessage message = sender.createMimeMessage();
		SimpleMailMessage msg = new SimpleMailMessage(getTemplateMessage());

		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(dto.getRecipientEmailId());
		//helper.setTo("sibayan.adhikary@ericsson.com");
		helper.setFrom(msg.getFrom());
		//helper.setCc(msg.getCc());
		String msgContent="";
		msgContent += HEADER_MSG;
		helper.setSubject(msg.getSubject());
		String myActionLink=ApplicationPropertiesUtil.getProperty("mail.link.myaction");
		switch (dto.getNotificationType()) {
		case 1:
			msgContent += INotificationService.SUBMIT_MSG;
			msgContent = msgContent.replace("${1}", "" + dto.getSolutionId())
					.replace("${2}", dto.getSolutionCreator())
					.replace("${3}", dto.getNextApprover());
			msgContent+=INotificationService.LINK_MYACTION.replace("${Link}",myActionLink);
			break;
		case 2:
			msgContent += INotificationService.APPROVAL_REQUEST_MSG;
			msgContent = msgContent.replace("${1}", "" + dto.getSolutionId())
					.replace("${2}", dto.getPrevApprover())
					.replace("${3}", dto.getNextApprover());
			msgContent+=INotificationService.LINK_MYACTION.replace("${Link}",myActionLink);
			break;
		case 3:
			msgContent += INotificationService.REJECTED_MSG;
			msgContent = msgContent.replace("${1}", "" + dto.getSolutionId())
					.replace("${2}", dto.getPrevApprover());
			break;
		case 4 :
			msgContent += INotificationService.APPROVED_MSG;
			msgContent = msgContent.replace("${1}", "" + dto.getSolutionId());
			break;

		}
		msgContent += FOOTER_MSG;
		//msgContent+=" .Mail To :"+dto.getRecipientEmailId();
		helper.setText(msgContent, true);
		//System.out.println(msgContent);
		sender.send(message);

	}
	

}
