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
import com.gcrm.domain.Role;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.security.UserUtil;

/**
 * Lists Account
 * 
 */
public class ListAccountAction extends BaseListAction {

    private static final long serialVersionUID = -2404576552417042445L;
    private IBaseService<Account> baseService;
    private Iterator<Account> accounts;

    /**
     * Gets the list data.
     * 
     * @return null
     */
    public String listFull() throws Exception {
        UserUtil.permissionCheck("view_account");
        User loginUser = UserUtil.getLoginUser();
        int scope = loginUser.getScope_account();
        StringBuilder hqlBuilder = new StringBuilder(
                "select new Account(id,name) from Account");

        if (scope == Role.OWNER_OR_DISABLED) {
            hqlBuilder.append(" where owner = ").append(loginUser.getId());
        }
        hqlBuilder.append(" order by created_on");
        List<Account> result = baseService.findByHQL(hqlBuilder.toString());
        accounts = result.iterator();
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public IBaseService<Account> getbaseService() {
        return baseService;
    }

    public void setbaseService(IBaseService<Account> baseService) {
        this.baseService = baseService;
    }

    /**
     * @return the id
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
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

}
