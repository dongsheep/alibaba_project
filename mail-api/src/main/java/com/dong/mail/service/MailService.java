package com.dong.mail.service;

import com.dong.mail.dto.MailDto;

/**
 * provider service interface
 *
 * @author xiedongxiao
 */

public interface MailService {

    void sendEmail(MailDto dto);

}
