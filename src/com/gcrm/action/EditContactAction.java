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

import java.util.List;
import java.util.Map;

import com.gcrm.domain.Account;
import com.gcrm.domain.Call;
import com.gcrm.domain.Campaign;
import com.gcrm.domain.CaseInstance;
import com.gcrm.domain.Contact;
import com.gcrm.domain.Document;
import com.gcrm.domain.LeadSource;
import com.gcrm.domain.Meeting;
import com.gcrm.domain.Opportunity;
import com.gcrm.domain.Salutation;
import com.gcrm.domain.TargetList;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.service.IOptionService;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits Contact
 * 
 */
public class EditContactAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<Contact> baseService;
    private IBaseService<Account> accountService;
    private IOptionService<LeadSource> leadSourceService;
    private IOptionService<Salutation> salutationService;
    private IBaseService<User> userService;
    private IBaseService<Campaign> campaignService;
    private IBaseService<Opportunity> opportunityService;
    private IBaseService<TargetList> targetListService;
    private IBaseService<Document> documentService;
    private IBaseService<CaseInstance> caseService;
    private IBaseService<Call> callService;
    private IBaseService<Meeting> meetingService;
    private Contact contact;
    private List<LeadSource> leadSources;
    private List<Salutation> salutations;
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
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        String local = (String) session.get("locale");
        this.leadSources = leadSourceService.getOptions(
                LeadSource.class.getSimpleName(), local);
        this.salutations = salutationService.getOptions(
                Salutation.class.getSimpleName(), local);
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
     * @return the userService
     */
    public IBaseService<User> getUserService() {
        return userService;
    }

    /**
     * @param userService
     *            the userService to set
     */
    public void setUserService(IBaseService<User> userService) {
        this.userService = userService;
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
     * @return the leadSource
     */
    public List<LeadSource> getLeadSources() {
        return leadSources;
    }

    /**
     * @param leadSource
     *            the leadSource to set
     */
    public void setLeadSource(List<LeadSource> leadSources) {
        this.leadSources = leadSources;
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
     * @return the targetListService
     */
    public IBaseService<TargetList> getTargetListService() {
        return targetListService;
    }

    /**
     * @param targetListService
     *            the targetListService to set
     */
    public void setTargetListService(IBaseService<TargetList> targetListService) {
        this.targetListService = targetListService;
    }

    /**
     * @return the callService
     */
    public IBaseService<Call> getCallService() {
        return callService;
    }

    /**
     * @param callService
     *            the callService to set
     */
    public void setCallService(IBaseService<Call> callService) {
        this.callService = callService;
    }

    /**
     * @return the meetingService
     */
    public IBaseService<Meeting> getMeetingService() {
        return meetingService;
    }

    /**
     * @param meetingService
     *            the meetingService to set
     */
    public void setMeetingService(IBaseService<Meeting> meetingService) {
        this.meetingService = meetingService;
    }

    /**
     * @return the documentService
     */
    public IBaseService<Document> getDocumentService() {
        return documentService;
    }

    /**
     * @param documentService
     *            the documentService to set
     */
    public void setDocumentService(IBaseService<Document> documentService) {
        this.documentService = documentService;
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
     * @return the salutations
     */
    public List<Salutation> getSalutations() {
        return salutations;
    }

    /**
     * @param salutations
     *            the salutations to set
     */
    public void setSalutations(List<Salutation> salutations) {
        this.salutations = salutations;
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
     * @return the leadSourceService
     */
    public IOptionService<LeadSource> getLeadSourceService() {
        return leadSourceService;
    }

    /**
     * @param leadSourceService
     *            the leadSourceService to set
     */
    public void setLeadSourceService(
            IOptionService<LeadSource> leadSourceService) {
        this.leadSourceService = leadSourceService;
    }

    /**
     * @return the salutationService
     */
    public IOptionService<Salutation> getSalutationService() {
        return salutationService;
    }

    /**
     * @param salutationService
     *            the salutationService to set
     */
    public void setSalutationService(
            IOptionService<Salutation> salutationService) {
        this.salutationService = salutationService;
    }

    /**
     * @param leadSources
     *            the leadSources to set
     */
    public void setLeadSources(List<LeadSource> leadSources) {
        this.leadSources = leadSources;
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

}
