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
import com.gcrm.domain.CaseInstance;
import com.gcrm.domain.CaseOrigin;
import com.gcrm.domain.CasePriority;
import com.gcrm.domain.CaseReason;
import com.gcrm.domain.CaseStatus;
import com.gcrm.domain.CaseType;
import com.gcrm.domain.Contact;
import com.gcrm.domain.Document;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.service.IOptionService;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits Case
 * 
 */
public class EditCaseAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<CaseInstance> baseService;
    private IOptionService<CaseStatus> caseStatusService;
    private IOptionService<CasePriority> casePriorityService;
    private IOptionService<CaseType> caseTypeService;
    private IOptionService<CaseOrigin> caseOriginService;
    private IOptionService<CaseReason> caseReasonService;
    private IBaseService<Account> accountService;
    private IBaseService<Document> documentService;
    private IBaseService<Contact> contactService;
    private IBaseService<User> userService;
    private CaseInstance caseInstance;
    private List<CaseStatus> statuses;
    private List<CasePriority> casePriorities;
    private List<CaseType> caseTypes;
    private List<CaseOrigin> caseOrigins;
    private List<CaseReason> caseReasons;
    private Integer statusID = null;
    private String statusLabel = "";
    private Integer priorityID = null;
    private String priorityLabel = "";
    private Integer typeID = null;
    private String typeLabel = "";
    private Integer originID = null;
    private String originLabel = "";
    private Integer reasonID = null;
    private String reasonLabel = "";
    private Integer accountID = null;
    private String accountText = null;

    /**
     * Gets the entity.
     * 
     * @return the SUCCESS result
     */
    public String get() throws Exception {
        if (this.getId() != null) {
            caseInstance = baseService.getEntityById(CaseInstance.class,
                    this.getId());
            CaseStatus status = caseInstance.getStatus();
            if (status != null) {
                statusID = status.getId();
            }
            CasePriority priority = caseInstance.getPriority();
            if (priority != null) {
                priorityID = priority.getId();
            }
            CaseType type = caseInstance.getType();
            if (type != null) {
                typeID = type.getId();
            }
            CaseOrigin origin = caseInstance.getOrigin();
            if (origin != null) {
                originID = origin.getId();
            }
            CaseReason reason = caseInstance.getReason();
            if (reason != null) {
                reasonID = reason.getId();
            }
            Account account = caseInstance.getAccount();
            if (account != null) {
                accountID = account.getId();
                accountText = account.getName();
            }
            User assignedTo = caseInstance.getAssigned_to();
            if (assignedTo != null) {
                this.setAssignedToID(assignedTo.getId());
                this.setAssignedToText(assignedTo.getName());
            }
            this.getBaseInfo(caseInstance, CaseInstance.class.getSimpleName(),
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
        this.statuses = caseStatusService.getOptions(
                CaseStatus.class.getSimpleName(), local);
        this.casePriorities = casePriorityService.getOptions(
                CasePriority.class.getSimpleName(), local);
        this.caseTypes = caseTypeService.getOptions(
                CaseType.class.getSimpleName(), local);
        this.caseOrigins = caseOriginService.getOptions(
                CaseOrigin.class.getSimpleName(), local);
        this.caseReasons = caseReasonService.getOptions(
                CaseReason.class.getSimpleName(), local);
    }

    /**
     * @return the baseService
     */
    public IBaseService<CaseInstance> getBaseService() {
        return baseService;
    }

    /**
     * @param baseService
     *            the baseService to set
     */
    public void setBaseService(IBaseService<CaseInstance> baseService) {
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
     * @return the caseInstance
     */
    public CaseInstance getCaseInstance() {
        return caseInstance;
    }

    /**
     * @param caseInstance
     *            the caseInstance to set
     */
    public void setCaseInstance(CaseInstance caseInstance) {
        this.caseInstance = caseInstance;
    }

    /**
     * @return the statuses
     */
    public List<CaseStatus> getStatuses() {
        return statuses;
    }

    /**
     * @param statuses
     *            the statuses to set
     */
    public void setStatuses(List<CaseStatus> statuses) {
        this.statuses = statuses;
    }

    /**
     * @return the casePriorities
     */
    public List<CasePriority> getCasePriorities() {
        return casePriorities;
    }

    /**
     * @param casePriorities
     *            the casePriorities to set
     */
    public void setCasePriorities(List<CasePriority> casePriorities) {
        this.casePriorities = casePriorities;
    }

    /**
     * @return the caseTypes
     */
    public List<CaseType> getCaseTypes() {
        return caseTypes;
    }

    /**
     * @param caseTypes
     *            the caseTypes to set
     */
    public void setCaseTypes(List<CaseType> caseTypes) {
        this.caseTypes = caseTypes;
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
     * @return the priorityID
     */
    public Integer getPriorityID() {
        return priorityID;
    }

    /**
     * @param priorityID
     *            the priorityID to set
     */
    public void setPriorityID(Integer priorityID) {
        this.priorityID = priorityID;
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
     * @return the caseOrigins
     */
    public List<CaseOrigin> getCaseOrigins() {
        return caseOrigins;
    }

    /**
     * @param caseOrigins
     *            the caseOrigins to set
     */
    public void setCaseOrigins(List<CaseOrigin> caseOrigins) {
        this.caseOrigins = caseOrigins;
    }

    /**
     * @return the caseReasons
     */
    public List<CaseReason> getCaseReasons() {
        return caseReasons;
    }

    /**
     * @param caseReasons
     *            the caseReasons to set
     */
    public void setCaseReasons(List<CaseReason> caseReasons) {
        this.caseReasons = caseReasons;
    }

    /**
     * @return the originID
     */
    public Integer getOriginID() {
        return originID;
    }

    /**
     * @param originID
     *            the originID to set
     */
    public void setOriginID(Integer originID) {
        this.originID = originID;
    }

    /**
     * @return the reasonID
     */
    public Integer getReasonID() {
        return reasonID;
    }

    /**
     * @param reasonID
     *            the reasonID to set
     */
    public void setReasonID(Integer reasonID) {
        this.reasonID = reasonID;
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
     * @return the caseStatusService
     */
    public IOptionService<CaseStatus> getCaseStatusService() {
        return caseStatusService;
    }

    /**
     * @param caseStatusService
     *            the caseStatusService to set
     */
    public void setCaseStatusService(
            IOptionService<CaseStatus> caseStatusService) {
        this.caseStatusService = caseStatusService;
    }

    /**
     * @return the casePriorityService
     */
    public IOptionService<CasePriority> getCasePriorityService() {
        return casePriorityService;
    }

    /**
     * @param casePriorityService
     *            the casePriorityService to set
     */
    public void setCasePriorityService(
            IOptionService<CasePriority> casePriorityService) {
        this.casePriorityService = casePriorityService;
    }

    /**
     * @return the caseTypeService
     */
    public IOptionService<CaseType> getCaseTypeService() {
        return caseTypeService;
    }

    /**
     * @param caseTypeService
     *            the caseTypeService to set
     */
    public void setCaseTypeService(IOptionService<CaseType> caseTypeService) {
        this.caseTypeService = caseTypeService;
    }

    /**
     * @return the caseOriginService
     */
    public IOptionService<CaseOrigin> getCaseOriginService() {
        return caseOriginService;
    }

    /**
     * @param caseOriginService
     *            the caseOriginService to set
     */
    public void setCaseOriginService(
            IOptionService<CaseOrigin> caseOriginService) {
        this.caseOriginService = caseOriginService;
    }

    /**
     * @return the caseReasonService
     */
    public IOptionService<CaseReason> getCaseReasonService() {
        return caseReasonService;
    }

    /**
     * @param caseReasonService
     *            the caseReasonService to set
     */
    public void setCaseReasonService(
            IOptionService<CaseReason> caseReasonService) {
        this.caseReasonService = caseReasonService;
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
     * @return the priorityLabel
     */
    public String getPriorityLabel() {
        return priorityLabel;
    }

    /**
     * @param priorityLabel
     *            the priorityLabel to set
     */
    public void setPriorityLabel(String priorityLabel) {
        this.priorityLabel = priorityLabel;
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
     * @return the originLabel
     */
    public String getOriginLabel() {
        return originLabel;
    }

    /**
     * @param originLabel
     *            the originLabel to set
     */
    public void setOriginLabel(String originLabel) {
        this.originLabel = originLabel;
    }

    /**
     * @return the reasonLabel
     */
    public String getReasonLabel() {
        return reasonLabel;
    }

    /**
     * @param reasonLabel
     *            the reasonLabel to set
     */
    public void setReasonLabel(String reasonLabel) {
        this.reasonLabel = reasonLabel;
    }

}
