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

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.gcrm.domain.CaseInstance;
import com.gcrm.domain.Role;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.security.UserUtil;
import com.gcrm.vo.SearchCondition;
import com.gcrm.vo.SearchResult;

/**
 * Lists Case
 * 
 */
public class ListCaseAction extends BaseListAction {

    private static final long serialVersionUID = -2404576552417042445L;
    private static final String CLAZZ = CaseInstance.class.getSimpleName();
    private IBaseService<CaseInstance> baseService;
    private Iterator<CaseInstance> cases;

    /**
     * Gets the list data.
     * 
     * @return null
     */
    public String listFew() throws Exception {
        UserUtil.permissionCheck("view_case");
        User loginUser = UserUtil.getLoginUser();
        SearchCondition searchCondition = getFewSearchCondition(
                loginUser.getScope_case(), loginUser);
        String hql = "select new Case(id,subject) from Case";
        SearchResult<CaseInstance> result = baseService.getPaginationObjectsWithHql(
                CLAZZ, hql, searchCondition);
        Iterator<CaseInstance> cases = result.getResult().iterator();
        getJson(cases);
        return null;
    }

    /**
     * Gets the list data.
     * 
     * @return null
     */
    public String listFull() throws Exception {
        UserUtil.permissionCheck("view_case");
        User loginUser = UserUtil.getLoginUser();
        int scope = loginUser.getScope_case();
        StringBuilder hqlBuilder = new StringBuilder(
                "select new CaseInstance(id,subject) from CaseInstance");

        if (scope == Role.OWNER_OR_DISABLED) {
            hqlBuilder.append(" where owner = ").append(loginUser.getId());
        }
        hqlBuilder.append(" order by created_on");
        List<CaseInstance> result = baseService.findByHQL(hqlBuilder.toString());
        cases = result.iterator();
        return SUCCESS;
    }

    /**
     * Gets the list JSON data.
     * 
     * @param cases
     *            case iterator
     * @return list JSON data
     */
    private void getJson(Iterator<CaseInstance> cases) throws IOException {
        StringBuilder jsonBuilder = new StringBuilder("{");
        while (cases.hasNext()) {
            CaseInstance instance = cases.next();
            int id = instance.getId();
            String name = instance.getName();
            jsonBuilder.append("\"").append(id).append("\":\"").append(name)
                    .append("\"");
            if (cases.hasNext()) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("}");
        // Returns JSON data back to page
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(jsonBuilder.toString());
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public IBaseService<CaseInstance> getbaseService() {
        return baseService;
    }

    public void setbaseService(IBaseService<CaseInstance> baseService) {
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
     * @return the cases
     */
    public Iterator<CaseInstance> getCases() {
        return cases;
    }

    /**
     * @param cases
     *            the cases to set
     */
    public void setCases(Iterator<CaseInstance> cases) {
        this.cases = cases;
    }

}
