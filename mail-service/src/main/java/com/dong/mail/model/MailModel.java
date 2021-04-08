package com.dong.mail.model;

import java.util.List;

public class MailModel {

    /**
     * 标题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;

    /**
     * 发件人
     */
    private String from;

    /**
     * 收件人
     */
    private List<String> to;

    /**
     * 抄送人
     */
    private List<String> cc;

    /**
     * 密送人
     */
    private List<String> bcc;

    /**
     * 附件路径
     */
    private List<String> attachPaths;

    /**
     * 资源
     */
    private List<ResourceModel> resources;

}
