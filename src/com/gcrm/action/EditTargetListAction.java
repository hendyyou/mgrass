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

import com.gcrm.domain.TargetList;
import com.gcrm.domain.TargetListType;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.service.IOptionService;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits TargetList
 * 
 */
public class EditTargetListAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<TargetList> baseService;
    private IOptionService<TargetListType> targetListTypeService;
    private IBaseService<User> userService;
    private TargetList targetList;
    private List<TargetListType> targetListTypes;
    private Integer typeID = null;
    private String typeLabel = "";

    /**
     * Gets the entity.
     * 
     * @return the SUCCESS result
     */
    public String get() throws Exception {
        if (this.getId() != null) {
            targetList = baseService.getEntityById(TargetList.class,
                    this.getId());
            TargetListType type = targetList.getType();
            if (type != null) {
                typeID = type.getId();
                typeLabel = type.getLabel();
            }

            User assignedTo = targetList.getAssigned_to();
            if (assignedTo != null) {
                this.setAssignedToID(assignedTo.getId());
                this.setAssignedToText(assignedTo.getName());
            }
            this.getBaseInfo(targetList, TargetList.class.getSimpleName(),
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
        this.targetListTypes = targetListTypeService.getOptions(
                TargetListType.class.getSimpleName(), local);
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
     * @return the typeID
     */
    public Integer getTypeID() {
        return typeID;
    }

    /**
     * @param typeID
     *            the typeID to set
     */
    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    /**
     * @return the targetListTypes
     */
    public List<TargetListType> getTargetListTypes() {
        return targetListTypes;
    }

    /**
     * @param targetListTypes
     *            the targetListTypes to set
     */
    public void setTargetListTypes(List<TargetListType> targetListTypes) {
        this.targetListTypes = targetListTypes;
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
     * @return the targetListTypeService
     */
    public IOptionService<TargetListType> getTargetListTypeService() {
        return targetListTypeService;
    }

    /**
     * @param targetListTypeService
     *            the targetListTypeService to set
     */
    public void setTargetListTypeService(
            IOptionService<TargetListType> targetListTypeService) {
        this.targetListTypeService = targetListTypeService;
    }

    /**
     * @return the typeLabel
     */
    public String getTypeLabel() {
        return typeLabel;
    }

    /**
     * @param typeLabel
     *            the typeLabel to set
     */
    public void setTypeLabel(String typeLabel) {
        this.typeLabel = typeLabel;
    }

}
