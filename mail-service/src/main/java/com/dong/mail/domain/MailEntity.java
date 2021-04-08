package com.dong.mail.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * spring data jpa 实体类
 *
 * @author xiedongxiao
 */

@Entity
@Table(name = "t_mail")
public class MailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "module")
    private String module;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "send_status")
    private Integer sendStatus;

    @Column(name = "response_time")
    private Date responseTime;

    @Column(name = "receiver")
    private  String receiver;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
