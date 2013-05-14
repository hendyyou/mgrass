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
import com.gcrm.domain.Salutation;
import com.gcrm.domain.Target;
import com.gcrm.domain.Task;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits Target
 * 
 */
public class EditTargetAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<Target> baseService;
    private IBaseService<Task> taskService;
    private Iterator<Task> tasks;
    private Target target;
    private Integer accountID = null;
    private String accountText = "";
    private Integer salutationID = null;
    private String salutationLabel = "";

    /**
     * Gets the entity.
     * 
     * @return the SUCCESS result
     */
    public String get() throws Exception {
        if (this.getId() != null) {
            target = baseService.getEntityById(Target.class, this.getId());
            Account account = target.getAccount();
            if (account != null) {
                accountID = account.getId();
                accountText = account.getName();
            }

            Salutation salutation = target.getSalutation();
            if (salutation != null) {
                salutationID = salutation.getId();
                salutationLabel = salutation.getLabel();
            }

            User assignedTo = target.getAssigned_to();
            if (assignedTo != null) {
                this.setAssignedToID(assignedTo.getId());
                this.setAssignedToText(assignedTo.getName());
            }
            this.getBaseInfo(target, Target.class.getSimpleName(),
                    Constant.CRM_NAMESPACE);
            // Gets related tasks
            StringBuilder taskHqlBuilder = new StringBuilder(
                    "select new Task(id,subject) from Task");
            taskHqlBuilder.append(
                    " where related_object = 'Target' and related_record = ")
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
     * @return the target
     */
    public Target getTarget() {
        return target;
    }

    /**
     * @param target
     *            the target to set
     */
    public void setTarget(Target target) {
        this.target = target;
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
    public IBaseService<Target> getBaseService() {
        return baseService;
    }

    /**
     * @param baseService
     *            the baseService to set
     */
    public void setBaseService(IBaseService<Target> baseService) {
        this.baseService = baseService;
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

}
