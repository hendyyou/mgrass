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
import com.gcrm.domain.AccountType;
import com.gcrm.domain.Campaign;
import com.gcrm.domain.CaseInstance;
import com.gcrm.domain.Contact;
import com.gcrm.domain.Document;
import com.gcrm.domain.Industry;
import com.gcrm.domain.Lead;
import com.gcrm.domain.Opportunity;
import com.gcrm.domain.Task;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.Constant;
import com.gcrm.util.security.UserUtil;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits Account
 * 
 */
public class EditAccountAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<Account> baseService;
    private IBaseService<Campaign> campaignService;
    private IBaseService<Contact> contactService;
    private IBaseService<Opportunity> opportunityService;
    private IBaseService<Lead> leadService;
    private IBaseService<CaseInstance> caseService;
    private IBaseService<Task> taskService;
    private Account account;
    private Iterator<Contact> contacts;
    private Iterator<Opportunity> opportunities;
    private Iterator<Lead> leads;
    private Iterator<Account> members;
    private Iterator<Document> documents;
    private Iterator<CaseInstance> cases;
    private Iterator<Task> tasks;
    private Integer typeID = null;
    private String typeLabel = "";
    private Integer industryID = null;
    private String industryLabel = "";
    private Integer campaignID = null;
    private Integer managerID = null;
    private String managerText = null;

    /**
     * Gets the entity.
     * 
     * @return the SUCCESS result
     */
    public String get() throws Exception {
        if (this.getId() != null) {
            UserUtil.permissionCheck("view_account");
            account = baseService.getEntityById(Account.class, this.getId());
            UserUtil.scopeCheck(account, "scope_account");
            AccountType type = account.getAccount_type();
            if (type != null) {
                typeID = type.getId();
                typeLabel = type.getLabel();
            }
            Industry industry = account.getIndustry();
            if (industry != null) {
                industryID = industry.getId();
                industryLabel = industry.getLabel();
            }

            User assignedTo = account.getAssigned_to();
            if (assignedTo != null) {
                this.setAssignedToID(assignedTo.getId());
                this.setAssignedToText(assignedTo.getName());
            }

            Account manager = account.getManager();
            if (manager != null) {
                managerID = manager.getId();
                managerText = manager.getName();
            }
            this.getBaseInfo(account, Account.class.getSimpleName(),
                    Constant.CRM_NAMESPACE);
            // Gets related contacts
            StringBuilder contactHqlBuilder = new StringBuilder(
                    "select new Contact(id,first_name,last_name) from Contact");
            contactHqlBuilder.append(" where account = ").append(this.getId());
            contactHqlBuilder.append(" order by created_on desc");
            List<Contact> contactResult = contactService
                    .findByHQL(contactHqlBuilder.toString());
            contacts = contactResult.iterator();
            // Gets related opportunities
            StringBuilder oppHqlBuilder = new StringBuilder(
                    "select new Opportunity(id,name) from Opportunity");
            oppHqlBuilder.append(" where account = ").append(this.getId());
            oppHqlBuilder.append(" order by created_on desc");
            List<Opportunity> oppResult = opportunityService
                    .findByHQL(oppHqlBuilder.toString());
            opportunities = oppResult.iterator();
            // Gets related leads
            StringBuilder leadHqlBuilder = new StringBuilder(
                    "select new Lead(id,first_name,last_name) from Lead");
            leadHqlBuilder.append(" where account = ").append(this.getId());
            leadHqlBuilder.append(" order by created_on desc");
            List<Lead> leadResult = leadService.findByHQL(leadHqlBuilder
                    .toString());
            leads = leadResult.iterator();
            // Gets related members
            StringBuilder memberHqlBuilder = new StringBuilder(
                    "select new Account(id,name) from Account");
            memberHqlBuilder.append(" where manager = ").append(this.getId());
            memberHqlBuilder.append(" order by created_on desc");
            List<Account> memberResult = baseService.findByHQL(memberHqlBuilder
                    .toString());
            members = memberResult.iterator();
            // Gets related documents
            Set<Document> documentResult = account.getDocuments();
            documents = documentResult.iterator();
            // Gets related cases
            StringBuilder caseHqlBuilder = new StringBuilder(
                    "select new CaseInstance(id,subject) from CaseInstance");
            caseHqlBuilder.append(" where account = ").append(this.getId());
            caseHqlBuilder.append(" order by created_on desc");
            List<CaseInstance> caseResult = caseService
                    .findByHQL(caseHqlBuilder.toString());
            cases = caseResult.iterator();
            // Gets related tasks
            StringBuilder taskHqlBuilder = new StringBuilder(
                    "select new Task(id,subject) from Task");
            taskHqlBuilder.append(
                    " where related_object = 'Account' and related_record = ")
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
    public IBaseService<Account> getBaseService() {
        return baseService;
    }

    /**
     * @param baseService
     *            the baseService to set
     */
    public void setBaseService(IBaseService<Account> baseService) {
        this.baseService = baseService;
    }

    /**
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account
     *            the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
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
     * @return the industryID
     */
    public Integer getIndustryID() {
        return industryID;
    }

    /**
     * @param industryID
     *            the industryID to set
     */
    public void setIndustryID(Integer industryID) {
        this.industryID = industryID;
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
     * @return the manageID
     */
    public Integer getManagerID() {
        return managerID;
    }

    /**
     * @param manageID
     *            the manageID to set
     */
    public void setManagerID(Integer managerID) {
        this.managerID = managerID;
    }

    /**
     * @return the campaignService
     */
    public IBaseService<Campaign> getCampaignService() {
        return campaignService;
    }

    /**
     * @param campaignService
     *            the campaignService to set
     */
    public void setCampaignService(IBaseService<Campaign> campaignService) {
        this.campaignService = campaignService;
    }

    /**
     * @return the managerText
     */
    public String getManagerText() {
        return managerText;
    }

    /**
     * @param managerText
     *            the managerText to set
     */
    public void setManagerText(String managerText) {
        this.managerText = managerText;
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
     * @return the industryLabel
     */
    public String getIndustryLabel() {
        return industryLabel;
    }

    /**
     * @param industryLabel
     *            the industryLabel to set
     */
    public void setIndustryLabel(String industryLabel) {
        this.industryLabel = industryLabel;
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
     * @return the members
     */
    public Iterator<Account> getMembers() {
        return members;
    }

    /**
     * @param members
     *            the members to set
     */
    public void setMembers(Iterator<Account> members) {
        this.members = members;
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
