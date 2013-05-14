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

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.gcrm.domain.Account;
import com.gcrm.domain.Campaign;
import com.gcrm.domain.CaseInstance;
import com.gcrm.domain.Contact;
import com.gcrm.domain.Document;
import com.gcrm.domain.Lead;
import com.gcrm.domain.LeadSource;
import com.gcrm.domain.Opportunity;
import com.gcrm.domain.Salutation;
import com.gcrm.domain.Task;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits Contact
 * 
 */
public class EditContactAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<Contact> baseService;
    private IBaseService<Task> taskService;
    private Contact contact;
    private Iterator<Opportunity> opportunities;
    private Iterator<Lead> leads;
    private Iterator<Document> documents;
    private Iterator<CaseInstance> cases;
    private Iterator<Task> tasks;
    private Integer accountID = null;
    private String accountText = null;
    private Integer reportToID = null;
    private String reportToText = null;
    private Integer leadSourceID = null;
    private String leadSourceLabel = null;
    private Integer salutationID = null;
    private String salutationLabel = null;
    private Integer campaignID = null;
    private String campaignText = null;

    /**
     * Gets the entity.
     * 
     * @return the SUCCESS result
     */
    public String get() throws Exception {
        if (this.getId() != null) {
            contact = baseService.getEntityById(Contact.class, this.getId());
            Account account = contact.getAccount();
            if (account != null) {
                accountID = account.getId();
                accountText = account.getName();
            }

            Contact report_to = contact.getReport_to();
            if (report_to != null) {
                reportToID = report_to.getId();
                reportToText = report_to.getName();
            }

            LeadSource leadSource = contact.getLeadSource();
            if (leadSource != null) {
                leadSourceID = leadSource.getId();
                leadSourceLabel = leadSource.getLabel();
            }

            Salutation salutation = contact.getSalutation();
            if (salutation != null) {
                salutationID = salutation.getId();
                salutationLabel = salutation.getLabel();
            }

            Campaign campaign = contact.getCampaign();
            if (campaign != null) {
                campaignID = campaign.getId();
                campaignText = campaign.getName();
            }
            User assignedTo = contact.getAssigned_to();
            if (assignedTo != null) {
                this.setAssignedToID(assignedTo.getId());
                this.setAssignedToText(assignedTo.getName());
            }
            this.getBaseInfo(contact, Contact.class.getSimpleName(),
                    Constant.CRM_NAMESPACE);
            // Gets related opportunities
            Set<Opportunity> oppResult = contact.getOpportunities();
            opportunities = oppResult.iterator();
            // Gets related leads
            Set<Lead> leadResult = contact.getLeads();
            leads = leadResult.iterator();
            // Gets related documents
            Set<Document> documentResult = contact.getDocuments();
            documents = documentResult.iterator();
            // Gets related cases
            Set<CaseInstance> caseResult = contact.getCases();
            cases = caseResult.iterator();
            // Gets related tasks
            StringBuilder taskHqlBuilder = new StringBuilder(
                    "select new Task(id,subject) from Task");
            taskHqlBuilder.append(
                    " where related_object = 'Contact' and related_record = ")
                    .append(this.getId());
            taskHqlBuilder.append(" order by created_on desc");
            List<Task> taskResult = taskService.findByHQL(taskHqlBuilder
                    .toString());
            tasks = taskResult.iterator();
        } else {
            this.initBaseInfo();
        }
        return SUCCESS;
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
    public IBaseService<Contact> getBaseService() {
        return baseService;
    }

    /**
     * @param baseService
     *            the baseService to set
     */
    public void setBaseService(IBaseService<Contact> baseService) {
        this.baseService = baseService;
    }

    /**
     * @return the contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * @param contact
     *            the contact to set
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * @return the accountID
     */
    public Integer getAccountID() {
        return accountID;
    }

    /**
     * @param accountID
     *            the accountID to set
     */
    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    /**
     * @return the leadSourceID
     */
    public Integer getLeadSourceID() {
        return leadSourceID;
    }

    /**
     * @param leadSourceID
     *            the leadSourceID to set
     */
    public void setLeadSourceID(Integer leadSourceID) {
        this.leadSourceID = leadSourceID;
    }

    /**
     * @return the campaignID
     */
    public Integer getCampaignID() {
        return campaignID;
    }

    /**
     * @param campaignID
     *            the campaignID to set
     */
    public void setCampaignID(Integer campaignID) {
        this.campaignID = campaignID;
    }

    /**
     * @return the reportToID
     */
    public Integer getReportToID() {
        return reportToID;
    }

    /**
     * @param reportToID
     *            the reportToID to set
     */
    public void setReportToID(Integer reportToID) {
        this.reportToID = reportToID;
    }

    /**
     * @return the accountText
     */
    public String getAccountText() {
        return accountText;
    }

    /**
     * @param accountText
     *            the accountText to set
     */
    public void setAccountText(String accountText) {
        this.accountText = accountText;
    }

    /**
     * @return the reportToText
     */
    public String getReportToText() {
        return reportToText;
    }

    /**
     * @return the campaignText
     */
    public String getCampaignText() {
        return campaignText;
    }

    /**
     * @return the salutationID
     */
    public Integer getSalutationID() {
        return salutationID;
    }

    /**
     * @param salutationID
     *            the salutationID to set
     */
    public void setSalutationID(Integer salutationID) {
        this.salutationID = salutationID;
    }

    /**
     * @return the leadSourceLabel
     */
    public String getLeadSourceLabel() {
        return leadSourceLabel;
    }

    /**
     * @param leadSourceLabel
     *            the leadSourceLabel to set
     */
    public void setLeadSourceLabel(String leadSourceLabel) {
        this.leadSourceLabel = leadSourceLabel;
    }

    /**
     * @return the salutationLabel
     */
    public String getSalutationLabel() {
        return salutationLabel;
    }

    /**
     * @param salutationLabel
     *            the salutationLabel to set
     */
    public void setSalutationLabel(String salutationLabel) {
        this.salutationLabel = salutationLabel;
    }

    /**
     * @param reportToText
     *            the reportToText to set
     */
    public void setReportToText(String reportToText) {
        this.reportToText = reportToText;
    }

    /**
     * @param campaignText
     *            the campaignText to set
     */
    public void setCampaignText(String campaignText) {
        this.campaignText = campaignText;
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
     * @return the opportunities
     */
    public Iterator<Opportunity> getOpportunities() {
        return opportunities;
    }

    /**
     * @param opportunities
     *            the opportunities to set
     */
    public void setOpportunities(Iterator<Opportunity> opportunities) {
        this.opportunities = opportunities;
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
     * @return the documents
     */
    public Iterator<Document> getDocuments() {
        return documents;
    }

    /**
     * @param documents
     *            the documents to set
     */
    public void setDocuments(Iterator<Document> documents) {
        this.documents = documents;
    }

    /**
     * @return the cases
     */
    public Iterator<CaseInstance> getCases() {
        return cases;
    }

    /**
     * @param cases
     *            the cases to set
     */
    public void setCases(Iterator<CaseInstance> cases) {
        this.cases = cases;
    }

    /**
     * @return the tasks
     */
    public Iterator<Task> getTasks() {
        return tasks;
    }

    /**
     * @param tasks
     *            the tasks to set
     */
    public void setTasks(Iterator<Task> tasks) {
        this.tasks = tasks;
    }

}
