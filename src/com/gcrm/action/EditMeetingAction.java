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
package com.gcrm.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import com.gcrm.domain.Account;
import com.gcrm.domain.CaseInstance;
import com.gcrm.domain.Contact;
import com.gcrm.domain.Lead;
import com.gcrm.domain.Meeting;
import com.gcrm.domain.MeetingStatus;
import com.gcrm.domain.Opportunity;
import com.gcrm.domain.ReminderOption;
import com.gcrm.domain.Target;
import com.gcrm.domain.Task;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.CommonUtil;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits Meeting
 * 
 */
public class EditMeetingAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<Meeting> baseService;
    private IBaseService<Account> accountService;
    private IBaseService<CaseInstance> caseService;
    private IBaseService<Contact> contactService;
    private IBaseService<Lead> leadService;
    private IBaseService<Opportunity> opportunityService;
    private IBaseService<Target> targetService;
    private IBaseService<Task> taskService;
    private Iterator<Contact> contacts;
    private Iterator<Lead> leads;
    private Iterator<User> users;
    private Meeting meeting;
    private Integer statusID = null;
    private String statusLabel = "";
    private Integer reminderOptionEmailID = null;
    private String reminderOptionEmailLabel = "";
    private String relatedObjectText = "";
    private String relatedRecordText = "";
    private String startDate = null;
    private String endDate = null;
    private Integer relatedAccountID = null;
    private Integer relatedCaseID = null;
    private Integer relatedContactID = null;
    private Integer relatedLeadID = null;
    private Integer relatedOpportunityID = null;
    private Integer relatedTargetID = null;
    private Integer relatedTaskID = null;

    /**
     * Gets the entity.
     * 
     * @return the SUCCESS result
     */
    public String get() throws Exception {
        if (this.getId() != null) {
            meeting = baseService.getEntityById(Meeting.class, this.getId());
            MeetingStatus status = meeting.getStatus();
            if (status != null) {
                statusID = status.getId();
                statusLabel = status.getLabel();
            }
            ReminderOption reminderOptionEmail = meeting
                    .getReminder_option_email();
            if (reminderOptionEmail != null) {
                reminderOptionEmailID = reminderOptionEmail.getId();
                reminderOptionEmailLabel = reminderOptionEmail.getLabel();
            }
            User assignedTo = meeting.getAssigned_to();
            if (assignedTo != null) {
                this.setAssignedToID(assignedTo.getId());
                this.setAssignedToText(assignedTo.getName());
            }
            Date start_date = meeting.getStart_date();
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    Constant.DATE_TIME_FORMAT);
            if (start_date != null) {
                startDate = dateFormat.format(start_date);
            }
            Date end_date = meeting.getEnd_date();
            if (end_date != null) {
                endDate = dateFormat.format(end_date);
            }
            String relatedObject = meeting.getRelated_object();
            Integer relatedRecord = meeting.getRelated_record();
            if (relatedRecord != null) {
                relatedObjectText = relatedObject;
                setRelatedRecord(relatedObject, relatedRecord);
            }
            this.getBaseInfo(meeting, Meeting.class.getSimpleName(),
                    Constant.CRM_NAMESPACE);
            // Gets related contacts
            Set<Contact> contactResult = meeting.getContacts();
            contacts = contactResult.iterator();
            // Gets related leads
            Set<Lead> leadResult = meeting.getLeads();
            leads = leadResult.iterator();
            // Gets related users
            Set<User> userResult = meeting.getUsers();
            users = userResult.iterator();
        } else {
            this.initBaseInfo();
            if (!CommonUtil.isNullOrEmpty(this.getRelationKey())) {
                meeting.setRelated_object(this.getRelationKey());
                setRelatedRecord(this.getRelationKey(),
                        Integer.parseInt(this.getRelationValue()));
            }
        }
        return SUCCESS;
    }

    /**
     * Sets the related record ID
     * 
     * @param relatedObject
     *            Related Object name
     * @param relatedRecord
     *            Related Record ID
     */
    private void setRelatedRecord(String relatedObject, Integer relatedRecord) {
        if ("Account".equals(relatedObject)) {
            this.relatedAccountID = relatedRecord;
            this.relatedRecordText = this.accountService.getEntityById(
                    Account.class, relatedRecord).getName();
        } else if ("Case".equals(relatedObject)) {
            this.relatedCaseID = relatedRecord;
            this.relatedRecordText = this.caseService.getEntityById(
                    CaseInstance.class, relatedRecord).getSubject();
        } else if ("Contact".equals(relatedObject)) {
            this.relatedContactID = relatedRecord;
            this.relatedRecordText = this.contactService.getEntityById(
                    Contact.class, relatedRecord).getName();
        } else if ("Lead".equals(relatedObject)) {
            this.relatedLeadID = relatedRecord;
            this.relatedRecordText = this.leadService.getEntityById(Lead.class,
                    relatedRecord).getName();
        } else if ("Opportunity".equals(relatedObject)) {
            this.relatedOpportunityID = relatedRecord;
            this.relatedRecordText = this.opportunityService.getEntityById(
                    Opportunity.class, relatedRecord).getName();
        } else if ("Target".equals(relatedObject)) {
            this.relatedTargetID = relatedRecord;
            this.relatedRecordText = this.targetService.getEntityById(
                    Target.class, relatedRecord).getName();
        } else if ("Task".equals(relatedObject)) {
            this.relatedTaskID = relatedRecord;
            this.relatedRecordText = this.taskService.getEntityById(Task.class,
                    relatedRecord).getSubject();
        }
    }

    /**
     * Prepares the list
     * 
     */
    public void prepare() throws Exception {
    }

    /**
     * @return the baseService
     */
    public IBaseService<Meeting> getBaseService() {
        return baseService;
    }

    /**
     * @param baseService
     *            the baseService to set
     */
    public void setBaseService(IBaseService<Meeting> baseService) {
        this.baseService = baseService;
    }

    /**
     * @return the meeting
     */
    public Meeting getMeeting() {
        return meeting;
    }

    /**
     * @param meeting
     *            the meeting to set
     */
    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    /**
     * @return the statusID
     */
    public Integer getStatusID() {
        return statusID;
    }

    /**
     * @param statusID
     *            the statusID to set
     */
    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the reminderOptionEmailID
     */
    public Integer getReminderOptionEmailID() {
        return reminderOptionEmailID;
    }

    /**
     * @param reminderOptionEmailID
     *            the reminderOptionEmailID to set
     */
    public void setReminderOptionEmailID(Integer reminderOptionEmailID) {
        this.reminderOptionEmailID = reminderOptionEmailID;
    }

    /**
     * @return the relatedAccountID
     */
    public Integer getRelatedAccountID() {
        return relatedAccountID;
    }

    /**
     * @param relatedAccountID
     *            the relatedAccountID to set
     */
    public void setRelatedAccountID(Integer relatedAccountID) {
        this.relatedAccountID = relatedAccountID;
    }

    /**
     * @return the relatedCaseID
     */
    public Integer getRelatedCaseID() {
        return relatedCaseID;
    }

    /**
     * @param relatedCaseID
     *            the relatedCaseID to set
     */
    public void setRelatedCaseID(Integer relatedCaseID) {
        this.relatedCaseID = relatedCaseID;
    }

    /**
     * @return the relatedContactID
     */
    public Integer getRelatedContactID() {
        return relatedContactID;
    }

    /**
     * @param relatedContactID
     *            the relatedContactID to set
     */
    public void setRelatedContactID(Integer relatedContactID) {
        this.relatedContactID = relatedContactID;
    }

    /**
     * @return the relatedLeadID
     */
    public Integer getRelatedLeadID() {
        return relatedLeadID;
    }

    /**
     * @param relatedLeadID
     *            the relatedLeadID to set
     */
    public void setRelatedLeadID(Integer relatedLeadID) {
        this.relatedLeadID = relatedLeadID;
    }

    /**
     * @return the relatedOpportunityID
     */
    public Integer getRelatedOpportunityID() {
        return relatedOpportunityID;
    }

    /**
     * @param relatedOpportunityID
     *            the relatedOpportunityID to set
     */
    public void setRelatedOpportunityID(Integer relatedOpportunityID) {
        this.relatedOpportunityID = relatedOpportunityID;
    }

    /**
     * @return the relatedTargetID
     */
    public Integer getRelatedTargetID() {
        return relatedTargetID;
    }

    /**
     * @param relatedTargetID
     *            the relatedTargetID to set
     */
    public void setRelatedTargetID(Integer relatedTargetID) {
        this.relatedTargetID = relatedTargetID;
    }

    /**
     * @return the relatedTaskID
     */
    public Integer getRelatedTaskID() {
        return relatedTaskID;
    }

    /**
     * @param relatedTaskID
     *            the relatedTaskID to set
     */
    public void setRelatedTaskID(Integer relatedTaskID) {
        this.relatedTaskID = relatedTaskID;
    }

    /**
     * @return the accountService
     */
    public IBaseService<Account> getAccountService() {
        return accountService;
    }

    /**
     * @param accountService
     *            the accountService to set
     */
    public void setAccountService(IBaseService<Account> accountService) {
        this.accountService = accountService;
    }

    /**
     * @return the caseService
     */
    public IBaseService<CaseInstance> getCaseService() {
        return caseService;
    }

    /**
     * @param caseService
     *            the caseService to set
     */
    public void setCaseService(IBaseService<CaseInstance> caseService) {
        this.caseService = caseService;
    }

    /**
     * @return the contactService
     */
    public IBaseService<Contact> getContactService() {
        return contactService;
    }

    /**
     * @param contactService
     *            the contactService to set
     */
    public void setContactService(IBaseService<Contact> contactService) {
        this.contactService = contactService;
    }

    /**
     * @return the leadService
     */
    public IBaseService<Lead> getLeadService() {
        return leadService;
    }

    /**
     * @param leadService
     *            the leadService to set
     */
    public void setLeadService(IBaseService<Lead> leadService) {
        this.leadService = leadService;
    }

    /**
     * @return the opportunityService
     */
    public IBaseService<Opportunity> getOpportunityService() {
        return opportunityService;
    }

    /**
     * @param opportunityService
     *            the opportunityService to set
     */
    public void setOpportunityService(
            IBaseService<Opportunity> opportunityService) {
        this.opportunityService = opportunityService;
    }

    /**
     * @return the targetService
     */
    public IBaseService<Target> getTargetService() {
        return targetService;
    }

    /**
     * @param targetService
     *            the targetService to set
     */
    public void setTargetService(IBaseService<Target> targetService) {
        this.targetService = targetService;
    }

    /**
     * @return the taskService
     */
    public IBaseService<Task> getTaskService() {
        return taskService;
    }

    /**
     * @param taskService
     *            the taskService to set
     */
    public void setTaskService(IBaseService<Task> taskService) {
        this.taskService = taskService;
    }

    /**
     * @return the statusLabel
     */
    public String getStatusLabel() {
        return statusLabel;
    }

    /**
     * @param statusLabel
     *            the statusLabel to set
     */
    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }

    /**
     * @return the reminderOptionEmailLabel
     */
    public String getReminderOptionEmailLabel() {
        return reminderOptionEmailLabel;
    }

    /**
     * @param reminderOptionEmailLabel
     *            the reminderOptionEmailLabel to set
     */
    public void setReminderOptionEmailLabel(String reminderOptionEmailLabel) {
        this.reminderOptionEmailLabel = reminderOptionEmailLabel;
    }

    /**
     * @return the relatedObjectText
     */
    public String getRelatedObjectText() {
        return relatedObjectText;
    }

    /**
     * @param relatedObjectText
     *            the relatedObjectText to set
     */
    public void setRelatedObjectText(String relatedObjectText) {
        this.relatedObjectText = relatedObjectText;
    }

    /**
     * @return the relatedRecordText
     */
    public String getRelatedRecordText() {
        return relatedRecordText;
    }

    /**
     * @param relatedRecordText
     *            the relatedRecordText to set
     */
    public void setRelatedRecordText(String relatedRecordText) {
        this.relatedRecordText = relatedRecordText;
    }

    /**
     * @return the contacts
     */
    public Iterator<Contact> getContacts() {
        return contacts;
    }

    /**
     * @param contacts
     *            the contacts to set
     */
    public void setContacts(Iterator<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * @return the leads
     */
    public Iterator<Lead> getLeads() {
        return leads;
    }

    /**
     * @param leads
     *            the leads to set
     */
    public void setLeads(Iterator<Lead> leads) {
        this.leads = leads;
    }

    /**
     * @return the users
     */
    public Iterator<User> getUsers() {
        return users;
    }

    /**
     * @param users
     *            the users to set
     */
    public void setUsers(Iterator<User> users) {
        this.users = users;
    }
}
