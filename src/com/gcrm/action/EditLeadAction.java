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

import com.gcrm.domain.Account;
import com.gcrm.domain.Campaign;
import com.gcrm.domain.Lead;
import com.gcrm.domain.LeadSource;
import com.gcrm.domain.LeadStatus;
import com.gcrm.domain.Salutation;
import com.gcrm.domain.Task;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits Lead
 * 
 */
public class EditLeadAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<Lead> baseService;
    private IBaseService<Task> taskService;
    private Iterator<Task> tasks;
    private Lead lead;
    private Integer accountID = null;
    private String accountText = null;
    private Integer leadStatusID = null;
    private String leadStatusLabel = "";
    private Integer leadSourceID = null;
    private String leadSourceLabel = "";
    private Integer salutationID = null;
    private String salutationLabel = "";
    private Integer campaignID = null;
    private String campaignText = null;
    private boolean accountCheck;
    private boolean contactCheck;
    private boolean opportunityCheck;

    /**
     * Gets the entity.
     * 
     * @return the SUCCESS result
     */
    public String get() throws Exception {
        if (this.getId() != null) {
            lead = baseService.getEntityById(Lead.class, this.getId());
            Account account = lead.getAccount();
            if (account != null) {
                accountID = account.getId();
                accountText = account.getName();
            }

            LeadStatus leadStatus = lead.getStatus();
            if (leadStatus != null) {
                leadStatusID = leadStatus.getId();
                leadStatusLabel = leadStatus.getLabel();
            }

            LeadSource leadSource = lead.getLead_source();
            if (leadSource != null) {
                leadSourceID = leadSource.getId();
                leadSourceLabel = leadSource.getLabel();
            }

            Salutation salutation = lead.getSalutation();
            if (salutation != null) {
                salutationID = salutation.getId();
                salutationLabel = salutation.getLabel();
            }

            Campaign campaign = lead.getCampaign();
            if (campaign != null) {
                campaignID = campaign.getId();
                campaignText = campaign.getName();
            }
            User assignedTo = lead.getAssigned_to();
            if (assignedTo != null) {
                this.setAssignedToID(assignedTo.getId());
                this.setAssignedToText(assignedTo.getName());
            }
            this.getBaseInfo(lead, Lead.class.getSimpleName(),
                    Constant.CRM_NAMESPACE);
            // Gets related tasks
            StringBuilder taskHqlBuilder = new StringBuilder(
                    "select new Task(id,subject) from Task");
            taskHqlBuilder.append(
                    " where related_object = 'Lead' and related_record = ")
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
     * @return the lead
     */
    public Lead getLead() {
        return lead;
    }

    /**
     * @param lead
     *            the lead to set
     */
    public void setLead(Lead lead) {
        this.lead = lead;
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
     * @return the leadStatusID
     */
    public Integer getLeadStatusID() {
        return leadStatusID;
    }

    /**
     * @param leadStatusID
     *            the leadStatusID to set
     */
    public void setLeadStatusID(Integer leadStatusID) {
        this.leadStatusID = leadStatusID;
    }

    /**
     * @return the accountCheck
     */
    public boolean isAccountCheck() {
        return accountCheck;
    }

    /**
     * @param accountCheck
     *            the accountCheck to set
     */
    public void setAccountCheck(boolean accountCheck) {
        this.accountCheck = accountCheck;
    }

    /**
     * @return the contactCheck
     */
    public boolean isContactCheck() {
        return contactCheck;
    }

    /**
     * @param contactCheck
     *            the contactCheck to set
     */
    public void setContactCheck(boolean contactCheck) {
        this.contactCheck = contactCheck;
    }

    /**
     * @return the opportunityCheck
     */
    public boolean isOpportunityCheck() {
        return opportunityCheck;
    }

    /**
     * @param opportunityCheck
     *            the opportunityCheck to set
     */
    public void setOpportunityCheck(boolean opportunityCheck) {
        this.opportunityCheck = opportunityCheck;
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
     * @return the campaignText
     */
    public String getCampaignText() {
        return campaignText;
    }

    /**
     * @param campaignText
     *            the campaignText to set
     */
    public void setCampaignText(String campaignText) {
        this.campaignText = campaignText;
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
     * @return the baseService
     */
    public IBaseService<Lead> getBaseService() {
        return baseService;
    }

    /**
     * @param baseService
     *            the baseService to set
     */
    public void setBaseService(IBaseService<Lead> baseService) {
        this.baseService = baseService;
    }

    /**
     * @return the leadStatusLabel
     */
    public String getLeadStatusLabel() {
        return leadStatusLabel;
    }

    /**
     * @param leadStatusLabel
     *            the leadStatusLabel to set
     */
    public void setLeadStatusLabel(String leadStatusLabel) {
        this.leadStatusLabel = leadStatusLabel;
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
