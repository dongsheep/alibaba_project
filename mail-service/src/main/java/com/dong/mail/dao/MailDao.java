package com.dong.mail.dao;

import com.dong.mail.domain.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * spring data jpa 数据层接口
 *
 * @author xiedongxiao
 */

@Repository
public interface MailDao extends JpaRepository<MailEntity, Integer> {

}
