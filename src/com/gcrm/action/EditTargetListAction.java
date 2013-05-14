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
import java.util.Set;

import com.gcrm.domain.Account;
import com.gcrm.domain.Contact;
import com.gcrm.domain.Lead;
import com.gcrm.domain.Target;
import com.gcrm.domain.TargetList;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits TargetList
 * 
 */
public class EditTargetListAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<TargetList> baseService;
    private TargetList targetList;
    private Iterator<Account> accounts;
    private Iterator<Contact> contacts;
    private Iterator<Target> targets;
    private Iterator<User> users;
    private Iterator<Lead> leads;

    /**
     * Gets the entity.
     * 
     * @return the SUCCESS result
     */
    public String get() throws Exception {
        if (this.getId() != null) {
            targetList = baseService.getEntityById(TargetList.class,
                    this.getId());
            User assignedTo = targetList.getAssigned_to();
            if (assignedTo != null) {
                this.setAssignedToID(assignedTo.getId());
                this.setAssignedToText(assignedTo.getName());
            }
            this.getBaseInfo(targetList, TargetList.class.getSimpleName(),
                    Constant.CRM_NAMESPACE);
            // Gets related accounts
            Set<Account> accountResult = targetList.getAccounts();
            accounts = accountResult.iterator();
            // Gets related contacts
            Set<Contact> contactResult = targetList.getContacts();
            contacts = contactResult.iterator();
            // Gets related leads
            Set<Lead> leadResult = targetList.getLeads();
            leads = leadResult.iterator();
            // Gets related targets
            Set<Target> targetResult = targetList.getTargets();
            targets = targetResult.iterator();
            // Gets related users
            Set<User> userResult = targetList.getUsers();
            users = userResult.iterator();
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
    public IBaseService<TargetList> getBaseService() {
        return baseService;
    }

    /**
     * @param baseService
     *            the baseService to set
     */
    public void setBaseService(IBaseService<TargetList> baseService) {
        this.baseService = baseService;
    }

    /**
     * @return the targetList
     */
    public TargetList getTargetList() {
        return targetList;
    }

    /**
     * @param targetList
     *            the targetList to set
     */
    public void setTargetList(TargetList targetList) {
        this.targetList = targetList;
    }

    /**
     * @return the accounts
     */
    public Iterator<Account> getAccounts() {
        return accounts;
    }

    /**
     * @param accounts
     *            the accounts to set
     */
    public void setAccounts(Iterator<Account> accounts) {
        this.accounts = accounts;
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
     * @return the targets
     */
    public Iterator<Target> getTargets() {
        return targets;
    }

    /**
     * @param targets
     *            the targets to set
     */
    public void setTargets(Iterator<Target> targets) {
        this.targets = targets;
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

}
