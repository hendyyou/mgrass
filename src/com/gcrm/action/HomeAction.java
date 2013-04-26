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
import java.util.Map;

import com.gcrm.domain.Account;
import com.gcrm.domain.Call;
import com.gcrm.domain.Lead;
import com.gcrm.domain.Meeting;
import com.gcrm.domain.Opportunity;
import com.gcrm.domain.Task;
import com.gcrm.domain.User;
import com.gcrm.security.AuthenticationSuccessListener;
import com.gcrm.service.IBaseService;
import com.gcrm.util.security.UserUtil;
import com.gcrm.vo.SearchCondition;
import com.gcrm.vo.SearchResult;
import com.opensymphony.xwork2.ActionContext;

/**
 * Home Action
 * 
 */
public class HomeAction extends BaseListAction {

    private static final long serialVersionUID = -2404576552417042445L;

    private Integer userID = null;
    private IBaseService<Account> accountService;
    private IBaseService<Lead> leadService;
    private IBaseService<Task> taskService;
    private IBaseService<Opportunity> opportunityService;
    private IBaseService<Meeting> meetingService;
    private IBaseService<Call> callService;
    private Iterator<Account> accounts;
    private Iterator<Lead> leads;
    private Iterator<Task> tasks;
    private Iterator<Opportunity> opportunities;
    private Iterator<Meeting> meetings;
    private Iterator<Call> calls;

    /**
     * Selects the entities
     * 
     * @return the SUCCESS result
     */
    public String load() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        User loginUser = (User) session
                .get(AuthenticationSuccessListener.LOGIN_USER);
        this.userID = loginUser.getId();

        if (UserUtil.hasPermission("view_account")) {
            listAccountFew(loginUser);
        }
        if (UserUtil.hasPermission("view_lead")) {
            listLeadFew(loginUser);
        }
        if (UserUtil.hasPermission("view_task")) {
            listTaskFew(loginUser);
        }
        if (UserUtil.hasPermission("view_opportunity")) {
            listOpportunityFew(loginUser);
        }
        if (UserUtil.hasPermission("view_meeting")) {
            listMeetingFew(loginUser);
        }
        if (UserUtil.hasPermission("view_call")) {
            listCallFew(loginUser);
        }
        return SUCCESS;
    }

    /**
     * Gets the Account list data.
     * 
     * @param loginUser
     *            login User
     * 
     */
    private void listAccountFew(User loginUser) throws Exception {
        SearchCondition searchCondition = getFewSearchCondition(
                loginUser.getScope_task(), loginUser);
        String hql = "select new Account(id,name) from Account";
        SearchResult<Account> result = accountService
                .getPaginationObjectsWithHql(Account.class.getSimpleName(),
                        hql, searchCondition);
        accounts = result.getResult().iterator();
    }

    /**
     * Gets the Lead list data.
     * 
     * @param loginUser
     *            login User
     */
    private void listLeadFew(User loginUser) throws Exception {
        SearchCondition searchCondition = getFewSearchCondition(
                loginUser.getScope_task(), loginUser);
        String hql = "select new Lead(id,first_name,last_name) from Lead";
        SearchResult<Lead> result = leadService.getPaginationObjectsWithHql(
                Lead.class.getSimpleName(), hql, searchCondition);
        leads = result.getResult().iterator();
    }

    /**
     * Gets the Task list data.
     * 
     * @param loginUser
     *            login User
     */
    private void listTaskFew(User loginUser) throws Exception {
        SearchCondition searchCondition = getFewSearchCondition(
                loginUser.getScope_task(), loginUser);
        String hql = "select new Task(id,subject) from Task";
        SearchResult<Task> result = taskService.getPaginationObjectsWithHql(
                Task.class.getSimpleName(), hql, searchCondition);
        tasks = result.getResult().iterator();
    }

    /**
     * Gets the Opportunity list data.
     * 
     * @param loginUser
     *            login User
     */
    private void listOpportunityFew(User loginUser) throws Exception {
        SearchCondition searchCondition = getFewSearchCondition(
                loginUser.getScope_task(), loginUser);
        String hql = "select new Opportunity(id,name) from Opportunity";
        SearchResult<Opportunity> result = opportunityService
                .getPaginationObjectsWithHql(Opportunity.class.getSimpleName(),
                        hql, searchCondition);
        opportunities = result.getResult().iterator();
    }

    /**
     * Gets the Meeting list data.
     * 
     * @param loginUser
     *            login User
     */
    public void listMeetingFew(User loginUser) throws Exception {
        SearchCondition searchCondition = getFewSearchCondition(
                loginUser.getScope_meeting(), loginUser);
        String hql = "select new Meeting(id,subject) from Meeting";
        SearchResult<Meeting> result = meetingService
                .getPaginationObjectsWithHql(Meeting.class.getSimpleName(),
                        hql, searchCondition);
        meetings = result.getResult().iterator();
    }

    /**
     * Gets the Call list data.
     * 
     * @param loginUser
     *            login User
     */
    public void listCallFew(User loginUser) throws Exception {
        SearchCondition searchCondition = getFewSearchCondition(
                loginUser.getScope_call(), loginUser);
        String hql = "select new Call(id,subject) from Call";
        SearchResult<Call> result = callService.getPaginationObjectsWithHql(
                Call.class.getSimpleName(), hql, searchCondition);
        calls = result.getResult().iterator();
    }

    /**
     * @return the userID
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * @param userID
     *            the userID to set
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
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
     * @return the meetings
     */
    public Iterator<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * @param meetings
     *            the meetings to set
     */
    public void setMeetings(Iterator<Meeting> meetings) {
        this.meetings = meetings;
    }

    /**
     * @return the calls
     */
    public Iterator<Call> getCalls() {
        return calls;
    }

    /**
     * @param calls
     *            the calls to set
     */
    public void setCalls(Iterator<Call> calls) {
        this.calls = calls;
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

}
