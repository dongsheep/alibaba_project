package com.dong.mail.util;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendMailTool {

	private static Logger log = LogManager.getLogger(SendMailTool.class);

	@Autowired
	private JavaMailSender sender;

	@Value("${spring.mail.username}")
	private String from;

	/**
	 * 发送纯文本的简单邮件
	 * 
	 * @param to
	 * @param subject
	 * @param content
	 */
	public boolean sendSimpleMail(String to, String subject, String content) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(from);
			message.setTo(to);
			message.setSubject(subject);
			message.setText(content);

			sender.send(message);
			log.info("简单邮件已经发送。");
			return true;
		} catch (Exception e) {
			log.error("发送简单邮件时发生异常！", e);
			return false;
		}
	}

	/**
	 * 发送html格式的邮件
	 * 
	 * @param to
	 * @param subject
	 * @param content
	 */
	public boolean sendHtmlMail(String to, String subject, String content) {
		try {
			MimeMessage message = sender.createMimeMessage();
			// true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			sender.send(message);
			log.info("html邮件已经发送。");
			return true;
		} catch (MessagingException e) {
			log.error("发送html邮件时发生异常！", e);
			return false;
		}
	}

	/**
	 * 发送带附件的邮件
	 * 
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 */
	public boolean sendAttachmentsMail(String to, String subject, String content, String filePath) {
		try {
			MimeMessage message = sender.createMimeMessage();
			// true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);

			sender.send(message);
			log.info("带附件的邮件已经发送。");
			return true;
		} catch (MessagingException e) {
			log.error("发送带附件的邮件时发生异常！", e);
			return false;
		}
	}

	/**
	 * 发送嵌入静态资源（一般是图片）的邮件
	 * 
	 * @param to
	 * @param subject
	 * @param content 邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" >
	 * @param rscPath 静态资源路径和文件名
	 * @param rscId   静态资源id
	 */
	public boolean sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
		try {
			MimeMessage message = sender.createMimeMessage();
			// true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource res = new FileSystemResource(new File(rscPath));
			helper.addInline(rscId, res);

			sender.send(message);
			log.info("嵌入静态资源的邮件已经发送。");
			return true;
		} catch (MessagingException e) {
			log.error("发送嵌入静态资源的邮件时发生异常！", e);
			return false;
		}
	}

}
