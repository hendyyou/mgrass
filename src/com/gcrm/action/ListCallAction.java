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

import com.gcrm.domain.Call;
import com.gcrm.domain.Role;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.security.UserUtil;

/**
 * Lists Call
 * 
 */
public class ListCallAction extends BaseListAction {

    private static final long serialVersionUID = -2404576552417042445L;
    private IBaseService<Call> baseService;
    private Iterator<Call> calls;

    /**
     * Gets the list data.
     * 
     * @return null
     */
    public String listFull() throws Exception {
        UserUtil.permissionCheck("view_call");
        User loginUser = UserUtil.getLoginUser();
        int scope = loginUser.getScope_call();
        StringBuilder hqlBuilder = new StringBuilder(
                "select new Call(id,subject) from Call");

        if (scope == Role.OWNER_OR_DISABLED) {
            hqlBuilder.append(" where owner = ").append(loginUser.getId());
        }
        hqlBuilder.append(" order by created_on");
        List<Call> result = baseService.findByHQL(hqlBuilder.toString());
        calls = result.iterator();
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public IBaseService<Call> getbaseService() {
        return baseService;
    }

    public void setbaseService(IBaseService<Call> baseService) {
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

}
