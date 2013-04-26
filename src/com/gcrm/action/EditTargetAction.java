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
import com.gcrm.domain.Lead;
import com.gcrm.domain.Salutation;
import com.gcrm.domain.Target;
import com.gcrm.domain.TargetList;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.service.IOptionService;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits Target
 * 
 */
public class EditTargetAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<Target> baseService;
    private IBaseService<Account> accountService;
    private IBaseService<Lead> leadService;
    private IBaseService<User> userService;
    private IBaseService<TargetList> targetListService;
    private IOptionService<Salutation> salutationService;
    private List<Salutation> salutations;
    private Target target;
    private Lead lead;
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

            Integer leadID = target.getLead_id();
            if (leadID != null) {
                try {
                    lead = this.getLeadService().getEntityById(Lead.class,
                            leadID);
                } catch (Exception e) {
                    // in case the converted lead is deleted
                    lead = null;
                }
            }

            User assignedTo = target.getAssigned_to();
            if (assignedTo != null) {
                this.setAssignedToID(assignedTo.getId());
                this.setAssignedToText(assignedTo.getName());
            }
            this.getBaseInfo(target, Target.class.getSimpleName(),
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
        this.salutations = salutationService.getOptions(
                Salutation.class.getSimpleName(), local);
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

}
