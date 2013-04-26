/**
 * Copyright (C) 2012, Grass CRM Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gcrm.util.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.gcrm.domain.EmailSetting;
import com.gcrm.service.IBaseService;

/**
 * Mail service
 */
public class MailService {
    private TaskExecutor taskExecutor;
    private IBaseService<EmailSetting> baseService;

    public void asynSendSimpleMail(final SimpleMailMessage msg) {
        taskExecutor.execute(new Runnable() {
            public void run() {
                sendSimpleMail(msg);
            }
        });
    }

    public void sendSimpleMail(SimpleMailMessage msg) {
        List<EmailSetting> emailSettings = baseService
                .getAllObjects(EmailSetting.class.getSimpleName());
        EmailSetting emailSetting = null;
        if (emailSettings != null && emailSettings.size() > 0) {
            emailSetting = emailSettings.get(0);
        } else {
            return;
        }
        JavaMailSenderImpl sender = this.prepareSender(emailSetting);
        if (sender != null) {
            sender.send(msg);
        }
    }

    public void asynSendHtmlMail(final String from, final String[] to,
            final String subject, final String text) {
        taskExecutor.execute(new Runnable() {
            public void run() {
                try {
                    sendHtmlMail(from, to, subject, text);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void sendHtmlMail(String from, String[] to, String subject,
            String text) throws Exception {
        List<EmailSetting> emailSettings = baseService
                .getAllObjects(EmailSetting.class.getSimpleName());
        EmailSetting emailSetting = null;
        if (emailSettings != null && emailSettings.size() > 0) {
            emailSetting = emailSettings.get(0);
        } else {
            return;
        }
        if (from == null) {
            from = emailSetting.getFrom_address();
        }
        JavaMailSenderImpl sender = this.prepareSender(emailSetting);
        if (sender != null) {
            MimeMessage msg = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, false,
                    "utf-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            sender.send(msg);
        }

    }

    private JavaMailSenderImpl prepareSender(EmailSetting emailSetting) {

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", "true");
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        int emailProvider = emailSetting.getEmail_provider();
        switch (emailProvider) {
        case EmailSetting.PROVIDER_GMAIL:
            sender.setHost("smtp.gmail.com");
            sender.setUsername(emailSetting.getGmail_address());
            sender.setPassword(emailSetting.getGmail_password());
            break;
        case EmailSetting.PROVIDER_YAHOO:
            sender.setHost("smtp.mail.yahoo.com");
            sender.setUsername(emailSetting.getYahoo_mail_ID());
            sender.setPassword(emailSetting.getYahoo_mail_password());
            break;
        case EmailSetting.PROVIDER_OTHER:
            sender.setHost(emailSetting.getSmtp_server());
            sender.setPort(emailSetting.getSmtp_port());
            sender.setUsername(emailSetting.getSmtp_username());
            sender.setPassword(emailSetting.getSmtp_password());
            switch (emailSetting.getSmtp_protocol()) {
            case EmailSetting.PROTOCOL_SSL:
                sender.setProtocol("smtps");
                break;
            case EmailSetting.PROTOCOL_TLS:
                javaMailProperties.put("mail.smtp.starttls.enable", "true");
                break;
            }
            break;
        }
        sender.setJavaMailProperties(javaMailProperties);
        return sender;
    }

    /**
     * @param taskExecutor
     *            the taskExecutor to set
     */
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    /**
     * @return the baseService
     */
    public IBaseService<EmailSetting> getBaseService() {
        return baseService;
    }

    /**
     * @param baseService
     *            the baseService to set
     */
    public void setBaseService(IBaseService<EmailSetting> baseService) {
        this.baseService = baseService;
    }

}
