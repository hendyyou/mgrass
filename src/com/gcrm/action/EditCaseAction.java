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
import com.gcrm.domain.CaseInstance;
import com.gcrm.domain.CaseOrigin;
import com.gcrm.domain.CasePriority;
import com.gcrm.domain.CaseReason;
import com.gcrm.domain.CaseStatus;
import com.gcrm.domain.CaseType;
import com.gcrm.domain.Contact;
import com.gcrm.domain.Document;
import com.gcrm.domain.Task;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits Case
 * 
 */
public class EditCaseAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<CaseInstance> baseService;
    private IBaseService<Task> taskService;
    private Iterator<Task> tasks;
    private Iterator<Contact> contacts;
    private Iterator<Document> documents;
    private CaseInstance caseInstance;
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
            // Gets related contacts
            Set<Contact> contactResult = caseInstance.getContacts();
            contacts = contactResult.iterator();
            // Gets related documents
            Set<Document> documentResult = caseInstance.getDocuments();
            documents = documentResult.iterator();
            // Gets related tasks
            StringBuilder taskHqlBuilder = new StringBuilder(
                    "select new Task(id,subject) from Task");
            taskHqlBuilder.append(
                    " where related_object = 'Case' and related_record = ")
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

}
