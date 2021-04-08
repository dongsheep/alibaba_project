package com.dong.mail.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 界面与服务端传输的dto对象
 *
 * @author xiedongxiao
 */

public class MailDto implements Serializable {

    private Integer id;

    private String module;

    private String title;

    private String content;

    private Integer sendStatus;

    private Date responseTime;

    private  String receiver;

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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MailDto{" +
                "id=" + id +
                ", module='" + module + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", sendStatus=" + sendStatus +
                ", responseTime=" + responseTime +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
