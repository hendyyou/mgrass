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

import com.gcrm.domain.Lead;
import com.gcrm.domain.Role;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.security.UserUtil;

/**
 * Lists Lead
 * 
 */
public class ListLeadAction extends BaseListAction {

    private static final long serialVersionUID = -2404576552417042445L;
    private IBaseService<Lead> baseService;
    private Iterator<Lead> leads;

    /**
     * Gets the list data.
     * 
     * @return null
     */
    public String listFull() throws Exception {
        UserUtil.permissionCheck("view_lead");
        User loginUser = UserUtil.getLoginUser();
        int scope = loginUser.getScope_lead();
        StringBuilder hqlBuilder = new StringBuilder(
                "select new Lead(id,first_name,last_name) from Lead");

        if (scope == Role.OWNER_OR_DISABLED) {
            hqlBuilder.append(" where owner = ").append(loginUser.getId());
        }
        hqlBuilder.append(" order by created_on desc");
        List<Lead> result = baseService.findByHQL(hqlBuilder.toString());
        leads = result.iterator();
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public IBaseService<Lead> getbaseService() {
        return baseService;
    }

    public void setbaseService(IBaseService<Lead> baseService) {
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
