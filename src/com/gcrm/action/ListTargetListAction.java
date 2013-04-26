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

import com.gcrm.domain.Role;
import com.gcrm.domain.TargetList;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.security.UserUtil;
import com.gcrm.vo.SearchCondition;
import com.gcrm.vo.SearchResult;

/**
 * Lists TargetList
 * 
 */
public class ListTargetListAction extends BaseListAction {

    private static final long serialVersionUID = -2404576552417042445L;
    private static final String CLAZZ = TargetList.class.getSimpleName();
    private IBaseService<TargetList> baseService;
    private Iterator<TargetList> targetLists;

    /**
     * Gets the list data.
     * 
     * @return null
     */
    public String listFew() throws Exception {
        UserUtil.permissionCheck("view_targetList");
        User loginUser = UserUtil.getLoginUser();
        SearchCondition searchCondition = getFewSearchCondition(
                loginUser.getScope_task(), loginUser);
        String hql = "select new TargetList(id,name) from TargetList";
        SearchResult<TargetList> result = baseService
                .getPaginationObjectsWithHql(CLAZZ, hql, searchCondition);
        Iterator<TargetList> targetLists = result.getResult().iterator();
        getJson(targetLists);
        return null;
    }

    /**
     * Gets the list data.
     * 
     * @return null
     */
    public String listFull() throws Exception {
        UserUtil.permissionCheck("view_targetList");
        User loginUser = UserUtil.getLoginUser();
        int scope = loginUser.getScope_targetList();
        StringBuilder hqlBuilder = new StringBuilder(
                "select new TargetList(id,name) from TargetList");

        if (scope == Role.OWNER_OR_DISABLED) {
            hqlBuilder.append(" where owner = ").append(loginUser.getId());
        }
        hqlBuilder.append(" order by created_on");
        List<TargetList> result = baseService.findByHQL(hqlBuilder.toString());
        targetLists = result.iterator();
        return SUCCESS;
    }

    /**
     * Gets the list JSON data.
     * 
     * @param tasks
     *            task iterator
     * @return list JSON data
     */
    private void getJson(Iterator<TargetList> targetLists) throws IOException {
        StringBuilder jsonBuilder = new StringBuilder("{");
        while (targetLists.hasNext()) {
            TargetList instance = targetLists.next();
            int id = instance.getId();
            String name = instance.getName();
            jsonBuilder.append("\"").append(id).append("\":\"").append(name)
                    .append("\"");
            if (targetLists.hasNext()) {
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

    public IBaseService<TargetList> getbaseService() {
        return baseService;
    }

    public void setbaseService(IBaseService<TargetList> baseService) {
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
     * @return the targetLists
     */
    public Iterator<TargetList> getTargetLists() {
        return targetLists;
    }

    /**
     * @param targetLists
     *            the targetLists to set
     */
    public void setTargetLists(Iterator<TargetList> targetLists) {
        this.targetLists = targetLists;
    }

}
