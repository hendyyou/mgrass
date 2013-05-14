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
import java.util.List;
import java.util.Set;

import com.gcrm.domain.Account;
import com.gcrm.domain.Campaign;
import com.gcrm.domain.Contact;
import com.gcrm.domain.Currency;
import com.gcrm.domain.Document;
import com.gcrm.domain.Lead;
import com.gcrm.domain.LeadSource;
import com.gcrm.domain.Opportunity;
import com.gcrm.domain.OpportunityType;
import com.gcrm.domain.SalesStage;
import com.gcrm.domain.Task;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits Opportunity
 * 
 */
public class EditOpportunityAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<Opportunity> baseService;
    private IBaseService<Task> taskService;
    private Iterator<Contact> contacts;
    private Iterator<Lead> leads;
    private Iterator<Document> documents;
    private Iterator<Task> tasks;
    private Opportunity opportunity;
    private Integer accountID = null;
    private String accountText = null;
    private Integer typeID = null;
    private String typeLabel = "";
    private Integer currencyID = null;
    private String currencyText = "";
    private Integer salesStageID = null;
    private String salesStageLabel = "";
    private Integer sourceID = null;
    private String sourceLabel = "";
    private Integer campaignID = null;
    private String campaignText = null;
    private String expectCloseDate = null;

    /**
     * Gets the entity.
     * 
     * @return the SUCCESS result
     */
    public String get() throws Exception {
        if (this.getId() != null) {
            opportunity = baseService.getEntityById(Opportunity.class,
                    this.getId());

            Account account = opportunity.getAccount();
            if (account != null) {
                accountID = account.getId();
                accountText = account.getName();
            }
            Currency currency = opportunity.getCurrency();
            if (currency != null) {
                currencyID = currency.getId();
                currencyText = currency.getFullName();
            }
            SalesStage salesStage = opportunity.getSales_stage();
            if (salesStage != null) {
                salesStageID = salesStage.getId();
                salesStageLabel = salesStage.getLabel();
            }
            LeadSource leadSource = opportunity.getLead_source();
            if (leadSource != null) {
                sourceID = leadSource.getId();
                sourceLabel = leadSource.getLabel();
            }
            OpportunityType type = opportunity.getType();
            if (type != null) {
                typeID = type.getId();
                typeLabel = type.getLabel();
            }
            User assignedTo = opportunity.getAssigned_to();
            if (assignedTo != null) {
                this.setAssignedToID(assignedTo.getId());
                this.setAssignedToText(assignedTo.getName());
            }

            Campaign campaign = opportunity.getCampaign();
            if (campaign != null) {
                campaignID = campaign.getId();
                campaignText = campaign.getName();
            }

            Date expect_close_date = opportunity.getExpect_close_date();
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    Constant.DATE_EDIT_FORMAT);
            if (expect_close_date != null) {
                expectCloseDate = dateFormat.format(expect_close_date);
            }
            this.getBaseInfo(opportunity, Opportunity.class.getSimpleName(),
                    Constant.CRM_NAMESPACE);
            // Gets related contacts
            Set<Contact> contactResult = opportunity.getContacts();
            contacts = contactResult.iterator();
            // Gets related leads
            Set<Lead> leadResult = opportunity.getLeads();
            leads = leadResult.iterator();
            // Gets related documents
            Set<Document> documentResult = opportunity.getDocuments();
            documents = documentResult.iterator();
            // Gets related tasks
            StringBuilder taskHqlBuilder = new StringBuilder(
                    "select new Task(id,subject) from Task");
            taskHqlBuilder
                    .append(" where related_object = 'Opportunity' and related_record = ")
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
    public IBaseService<Opportunity> getBaseService() {
        return baseService;
    }

    /**
     * @param baseService
     *            the baseService to set
     */
    public void setBaseService(IBaseService<Opportunity> baseService) {
        this.baseService = baseService;
    }

    /**
     * @return the opportunity
     */
    public Opportunity getOpportunity() {
        return opportunity;
    }

    /**
     * @param opportunity
     *            the opportunity to set
     */
    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    /**
     * @return the typeID
     */
    public Integer getTypeID() {
        return typeID;
    }

    /**
     * @param typeID
     *            the typeID to set
     */
    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    /**
     * @return the currencyID
     */
    public Integer getCurrencyID() {
        return currencyID;
    }

    /**
     * @param currencyID
     *            the currencyID to set
     */
    public void setCurrencyID(Integer currencyID) {
        this.currencyID = currencyID;
    }

    /**
     * @return the salesStageID
     */
    public Integer getSalesStageID() {
        return salesStageID;
    }

    /**
     * @param salesStageID
     *            the salesStageID to set
     */
    public void setSalesStageID(Integer salesStageID) {
        this.salesStageID = salesStageID;
    }

    /**
     * @return the sourceID
     */
    public Integer getSourceID() {
        return sourceID;
    }

    /**
     * @param sourceID
     *            the sourceID to set
     */
    public void setSourceID(Integer sourceID) {
        this.sourceID = sourceID;
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
     * @return the expectCloseDate
     */
    public String getExpectCloseDate() {
        return expectCloseDate;
    }

    /**
     * @param expectCloseDate
     *            the expectCloseDate to set
     */
    public void setExpectCloseDate(String expectCloseDate) {
        this.expectCloseDate = expectCloseDate;
    }

    /**
     * @return the accountText
     */
    public String getAccountText() {
        return accountText;
    }

    /**
     * @return the campaignText
     */
    public String getCampaignText() {
        return campaignText;
    }

    /**
     * @return the typeLabel
     */
    public String getTypeLabel() {
        return typeLabel;
    }

    /**
     * @param typeLabel
     *            the typeLabel to set
     */
    public void setTypeLabel(String typeLabel) {
        this.typeLabel = typeLabel;
    }

    /**
     * @return the salesStageLabel
     */
    public String getSalesStageLabel() {
        return salesStageLabel;
    }

    /**
     * @param salesStageLabel
     *            the salesStageLabel to set
     */
    public void setSalesStageLabel(String salesStageLabel) {
        this.salesStageLabel = salesStageLabel;
    }

    /**
     * @return the sourceLabel
     */
    public String getSourceLabel() {
        return sourceLabel;
    }

    /**
     * @param sourceLabel
     *            the sourceLabel to set
     */
    public void setSourceLabel(String sourceLabel) {
        this.sourceLabel = sourceLabel;
    }

    /**
     * @param accountText
     *            the accountText to set
     */
    public void setAccountText(String accountText) {
        this.accountText = accountText;
    }

    /**
     * @param campaignText
     *            the campaignText to set
     */
    public void setCampaignText(String campaignText) {
        this.campaignText = campaignText;
    }

    /**
     * @return the currencyText
     */
    public String getCurrencyText() {
        return currencyText;
    }

    /**
     * @param currencyText
     *            the currencyText to set
     */
    public void setCurrencyText(String currencyText) {
        this.currencyText = currencyText;
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
