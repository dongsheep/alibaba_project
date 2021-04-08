package com.dong.mail.test;

import com.dong.common.util.LogUtil;
import com.dong.mail.dto.MailDto;
import com.dong.mail.service.MailService;
import com.dong.mail.util.SendMailTool;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MailTest {

    private static Logger log = LogUtil.get(MailTest.class);

    @DubboReference
    private MailService mailService;

    @Autowired
    private SendMailTool sendMailTool;

    @Test
    public void sendSimpleMail() {
        sendMailTool.sendSimpleMail("408515371@qq.com", "主题：简单邮件", "测试邮件内容");
    }

    @Test
    public void sendHtmlMail() {
        sendMailTool.sendHtmlMail("408515371@qq.com", "主题：html邮件", "<html><body><div><h1>MEIZU</h1></div></body><html>");
    }

    @Test
    public void sendAttachmentsMail() {
        sendMailTool.sendAttachmentsMail("408515371@qq.com", "主题：attachments邮件", "<html><body><div><h1>MEIZU attachments</h1></div></body><html>", "C:\\Users\\XIEDONGXIAO\\Desktop\\docker.txt");
    }

    @Test
    public void sendInlineResourceMail() {
        sendMailTool.sendInlineResourceMail("408515371@qq.com", "主题：inlineResource邮件", "<html><body><div><h1>MEIZU InlineResource</h1></div><div><img src=\"cid:rscId01\" /></div></body><html>", "C:\\Users\\XIEDONGXIAO\\Pictures\\ROS联合团队.jpg", "rscId01");
    }

    @Test
    public void sendMail() {
        log.warn("send mail warning...");
        MailDto dto = new MailDto();
        dto.setModule("mail");
        dto.setTitle("测试邮件4");
        dto.setContent("hello world...");
        dto.setSendStatus(0);
        dto.setReceiver("408515371@qq.com");
        mailService.sendEmail(dto);
    }

    public static void main(String[] args) {
        List<String> lst = new ArrayList<>();
        lst.add("abc");
        lst.add("qwe");
        haha(lst.toArray(new String[2]));
    }

    public static void haha(String... lst) {
        for(String a : lst) {
            System.err.println(a);
        }
    }

}
