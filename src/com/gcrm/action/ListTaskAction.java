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

import com.gcrm.domain.Role;
import com.gcrm.domain.Task;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.security.UserUtil;

/**
 * Lists Task
 * 
 */
public class ListTaskAction extends BaseListAction {

    private static final long serialVersionUID = -2404576552417042445L;
    private IBaseService<Task> baseService;
    private Iterator<Task> tasks;

    /**
     * Gets the list data.
     * 
     * @return null
     */
    public String listFull() throws Exception {
        UserUtil.permissionCheck("view_task");
        User loginUser = UserUtil.getLoginUser();
        int scope = loginUser.getScope_task();
        StringBuilder hqlBuilder = new StringBuilder(
                "select new Task(id,subject) from Task");

        if (scope == Role.OWNER_OR_DISABLED) {
            hqlBuilder.append(" where owner = ").append(loginUser.getId());
        }
        hqlBuilder.append(" order by created_on");
        List<Task> result = baseService.findByHQL(hqlBuilder.toString());
        tasks = result.iterator();
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    /**
     * @return the baseService
     */
    public IBaseService<Task> getBaseService() {
        return baseService;
    }

    /**
     * @param baseService
     *            the baseService to set
     */
    public void setBaseService(IBaseService<Task> baseService) {
        this.baseService = baseService;
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

}
