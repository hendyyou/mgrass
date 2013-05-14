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
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import com.gcrm.domain.Campaign;
import com.gcrm.domain.CampaignStatus;
import com.gcrm.domain.CampaignType;
import com.gcrm.domain.Currency;
import com.gcrm.domain.TargetList;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits Campaign
 * 
 */
public class EditCampaignAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<Campaign> baseService;
    private Iterator<TargetList> targetLists;
    private Campaign campaign;
    private Integer statusID = null;
    private String statusLabel = "";
    private Integer typeID = null;
    private String typeLabel = "";
    private Integer currencyID = null;
    private String currencyText = "";
    private String startDate = null;
    private String endDate = null;

    /**
     * Gets the entity.
     * 
     * @return the SUCCESS result
     */
    public String get() throws Exception {
        if (this.getId() != null) {
            campaign = baseService.getEntityById(Campaign.class, this.getId());
            CampaignStatus status = campaign.getStatus();
            if (status != null) {
                statusID = status.getId();
                statusLabel = status.getLabel();
            }
            CampaignType type = campaign.getType();
            if (type != null) {
                typeID = type.getId();
                typeLabel = type.getLabel();
            }
            Currency currency = campaign.getCurrency();
            if (currency != null) {
                currencyID = currency.getId();
                currencyText = currency.getFullName();
            }
            User assignedTo = campaign.getAssigned_to();
            if (assignedTo != null) {
                this.setAssignedToID(assignedTo.getId());
                this.setAssignedToText(assignedTo.getName());
            }
            Date start_date = campaign.getStart_date();
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    Constant.DATE_EDIT_FORMAT);
            if (start_date != null) {
                startDate = dateFormat.format(start_date);
            }
            Date end_date = campaign.getEnd_date();
            if (end_date != null) {
                endDate = dateFormat.format(end_date);
            }
            this.getBaseInfo(campaign, Campaign.class.getSimpleName(),
                    Constant.CRM_NAMESPACE);
            // Gets related targetLists
            Set<TargetList> targetListResult = campaign.getTargetLists();
            targetLists = targetListResult.iterator();
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
    }

    public IBaseService<Campaign> getbaseService() {
        return baseService;
    }

    public void setbaseService(IBaseService<Campaign> baseService) {
        this.baseService = baseService;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    /**
     * @return the statusID
     */
    public Integer getStatusID() {
        return statusID;
    }

    /**
     * @param statusID
     *            the statusID to set
     */
    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
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
     * @return the currencyID
     */
    public Integer getCurrencyID() {
        return currencyID;
    }

    /**
     * @param currencyID
     *            the currencyID to set
     */
    public void setCurrencyID(Integer currencyID) {
        this.currencyID = currencyID;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the statusLabel
     */
    public String getStatusLabel() {
        return statusLabel;
    }

    /**
     * @param statusLabel
     *            the statusLabel to set
     */
    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
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

    /**
     * @return the currencyText
     */
    public String getCurrencyText() {
        return currencyText;
    }

    /**
     * @param currencyText
     *            the currencyText to set
     */
    public void setCurrencyText(String currencyText) {
        this.currencyText = currencyText;
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
