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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.gcrm.domain.BaseEntity;
import com.gcrm.domain.User;
import com.gcrm.security.AuthenticationSuccessListener;
import com.gcrm.util.CommonUtil;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Base Action for entity edit.
 */
public class BaseEditAction extends ActionSupport {

    private String createdBy = "";
    private String createdOn = "";
    private String updatedBy = "";
    private String updatedOn = "";
    private Integer ownerID = null;
    private String ownerText = null;
    private Integer assignedToID = null;
    private String assignedToText = null;
    private Integer id;
    private String relationKey;
    private String relationValue;
    protected String seleteIDs = null;
    protected String[] massUpdate = null;
    private String saveFlag = null;

    private static final long serialVersionUID = -2404576552417042445L;

    /**
     * Updates the base information for entity.
     * 
     * @param entity
     *            instance
     */
    protected void updateBaseInfo(BaseEntity entity) {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        User loginUser = (User) session
                .get(AuthenticationSuccessListener.LOGIN_USER);
        if (entity.getId() == null) {
            entity.setCreated_by(loginUser);
            entity.setCreated_on(new Date());
        } else {
            entity.setUpdated_by(loginUser);
            entity.setUpdated_on(new Date());
        }
    }

    /**
     * Gets login user.
     * 
     * @return login user
     */
    protected User getLoginUser() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        User loginUser = (User) session
                .get(AuthenticationSuccessListener.LOGIN_USER);
        return loginUser;
    }

    /**
     * Gets the base information for entity.
     * 
     * @param entity
     *            instance
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void getBaseInfo(BaseEntity entity, String entityName,
            String namespace) {
        User createdUser = entity.getCreated_by();
        if (createdUser != null) {
            this.setCreatedBy(createdUser.getName());
        }
        User updatedUser = entity.getUpdated_by();
        if (updatedUser != null) {
            this.setUpdatedBy(updatedUser.getName());
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                Constant.DATE_TIME_FORMAT);
        Date createdOn = entity.getCreated_on();
        if (createdOn != null) {
            this.setCreatedOn(dateFormat.format(createdOn));
        }
        Date updatedOn = entity.getUpdated_on();
        if (updatedOn != null) {
            this.setUpdatedOn(dateFormat.format(updatedOn));
        }
        User owner = entity.getOwner();
        if (owner != null) {
            ownerID = owner.getId();
            ownerText = owner.getName();
        }

        // Sets navigation history
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        ArrayList navigationList = (ArrayList) session
                .getAttribute(Constant.NAVIGATION_HISTORY);
        if (navigationList == null) {
            navigationList = new ArrayList();
        }
        String entityLabel = getText("entity."
                + CommonUtil.lowerCaseString(entityName) + ".label");
        if (!CommonUtil.isNullOrEmpty(entity.getName())) {
            entityLabel += " - " + entity.getName();
        }
        String navigatoin = "<a href='" + Constant.APP_PATH + namespace
                + "edit" + entityName + "?id=" + entity.getId() + "'>"
                + entityLabel + "</a>";
        if (navigationList.contains(navigatoin)) {
            navigationList.remove(navigatoin);
        }
        navigationList.add(navigatoin);
        if (navigationList.size() > Constant.NAVIGATION_HISTORY_COUNT) {
            navigationList.remove(0);
        }
        session.setAttribute(Constant.NAVIGATION_HISTORY, navigationList);
    }

    /**
     * Initiates the base information for entity.
     * 
     */
    protected void initBaseInfo() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        User loginUser = (User) session
                .get(AuthenticationSuccessListener.LOGIN_USER);
        assignedToID = loginUser.getId();
        assignedToText = loginUser.getName();
        ownerID = loginUser.getId();
        ownerText = loginUser.getName();
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the createdOn
     */
    public String getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn
     *            the createdOn to set
     */
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return the updatedBy
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy
     *            the updatedBy to set
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return the updatedOn
     */
    public String getUpdatedOn() {
        return updatedOn;
    }

    /**
     * @param updatedOn
     *            the updatedOn to set
     */
    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the relationKey
     */
    public String getRelationKey() {
        return relationKey;
    }

    /**
     * @param relationKey
     *            the relationKey to set
     */
    public void setRelationKey(String relationKey) {
        this.relationKey = relationKey;
    }

    /**
     * @return the relationValue
     */
    public String getRelationValue() {
        return relationValue;
    }

    /**
     * @param relationValue
     *            the relationValue to set
     */
    public void setRelationValue(String relationValue) {
        this.relationValue = relationValue;
    }

    /**
     * @return the seleteIDs
     */
    public String getSeleteIDs() {
        return seleteIDs;
    }

    /**
     * @param seleteIDs
     *            the seleteIDs to set
     */
    public void setSeleteIDs(String seleteIDs) {
        this.seleteIDs = seleteIDs;
    }

    /**
     * @return the massUpdate
     */
    public String[] getMassUpdate() {
        return massUpdate;
    }

    /**
     * @param massUpdate
     *            the massUpdate to set
     */
    public void setMassUpdate(String[] massUpdate) {
        this.massUpdate = massUpdate;
    }

    /**
     * @return the ownerID
     */
    public Integer getOwnerID() {
        return ownerID;
    }

    /**
     * @param ownerID
     *            the ownerID to set
     */
    public void setOwnerID(Integer ownerID) {
        this.ownerID = ownerID;
    }

    /**
     * @return the ownerText
     */
    public String getOwnerText() {
        return ownerText;
    }

    /**
     * @param ownerText
     *            the ownerText to set
     */
    public void setOwnerText(String ownerText) {
        this.ownerText = ownerText;
    }

    /**
     * @return the assignedToID
     */
    public Integer getAssignedToID() {
        return assignedToID;
    }

    /**
     * @param assignedToID
     *            the assignedToID to set
     */
    public void setAssignedToID(Integer assignedToID) {
        this.assignedToID = assignedToID;
    }

    /**
     * @return the assignedToText
     */
    public String getAssignedToText() {
        return assignedToText;
    }

    /**
     * @param assignedToText
     *            the assignedToText to set
     */
    public void setAssignedToText(String assignedToText) {
        this.assignedToText = assignedToText;
    }

    /**
     * @return the saveFlag
     */
    public String getSaveFlag() {
        return saveFlag;
    }

    /**
     * @param saveFlag
     *            the saveFlag to set
     */
    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }

}
