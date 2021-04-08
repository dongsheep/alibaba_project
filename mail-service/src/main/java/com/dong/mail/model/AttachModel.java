package com.dong.mail.model;

import org.springframework.core.io.InputStreamSource;

public class AttachModel {

    private String name;

    private InputStreamSource attach;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputStreamSource getAttach() {
        return attach;
    }

    public void setAttach(InputStreamSource attach) {
        this.attach = attach;
    }
}
