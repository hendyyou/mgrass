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

import com.gcrm.domain.Campaign;
import com.gcrm.domain.Role;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.security.UserUtil;
import com.gcrm.vo.SearchCondition;
import com.gcrm.vo.SearchResult;

/**
 * Lists Campaign
 * 
 */
public class ListCampaignAction extends BaseListAction {

    private static final long serialVersionUID = -2404576552417042445L;
    private static final String CLAZZ = Campaign.class.getSimpleName();
    private IBaseService<Campaign> baseService;
    private Iterator<Campaign> campaigns;

    /**
     * Gets the list data.
     * 
     * @return null
     */
    public String listFew() throws Exception {
        UserUtil.permissionCheck("view_campaign");
        User loginUser = UserUtil.getLoginUser();
        SearchCondition searchCondition = getFewSearchCondition(
                loginUser.getScope_task(), loginUser);
        String hql = "select new Campaign(id,name) from Campaign";
        SearchResult<Campaign> result = baseService
                .getPaginationObjectsWithHql(CLAZZ, hql, searchCondition);
        Iterator<Campaign> tasks = result.getResult().iterator();
        getJson(tasks);
        return null;
    }

    /**
     * Gets the list data.
     * 
     * @return null
     */
    public String listFull() throws Exception {
        UserUtil.permissionCheck("view_campaign");
        User loginUser = UserUtil.getLoginUser();
        int scope = loginUser.getScope_campaign();
        StringBuilder hqlBuilder = new StringBuilder(
                "select new Campaign(id,name) from Campaign");

        if (scope == Role.OWNER_OR_DISABLED) {
            hqlBuilder.append(" where owner = ").append(loginUser.getId());
        }
        hqlBuilder.append(" order by created_on desc");
        List<Campaign> result = baseService.findByHQL(hqlBuilder.toString());
        campaigns = result.iterator();
        return SUCCESS;
    }

    /**
     * Gets the list JSON data.
     * 
     * @param tasks
     *            task iterator
     * @return list JSON data
     */
    private void getJson(Iterator<Campaign> campaigns) throws IOException {
        StringBuilder jsonBuilder = new StringBuilder("{");
        while (campaigns.hasNext()) {
            Campaign instance = campaigns.next();
            int id = instance.getId();
            String name = instance.getName();
            jsonBuilder.append("\"").append(id).append("\":\"").append(name)
                    .append("\"");
            if (campaigns.hasNext()) {
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

    public IBaseService<Campaign> getbaseService() {
        return baseService;
    }

    public void setbaseService(IBaseService<Campaign> baseService) {
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
     * @return the campaigns
     */
    public Iterator<Campaign> getCampaigns() {
        return campaigns;
    }

    /**
     * @param campaigns
     *            the campaigns to set
     */
    public void setCampaigns(Iterator<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

}
