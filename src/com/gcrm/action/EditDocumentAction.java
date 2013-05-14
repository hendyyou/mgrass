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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import com.gcrm.domain.Account;
import com.gcrm.domain.CaseInstance;
import com.gcrm.domain.Contact;
import com.gcrm.domain.Document;
import com.gcrm.domain.DocumentCategory;
import com.gcrm.domain.DocumentStatus;
import com.gcrm.domain.DocumentSubCategory;
import com.gcrm.domain.Opportunity;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits Document
 * 
 */
public class EditDocumentAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<Document> baseService;
    private Iterator<Account> accounts;
    private Iterator<Contact> contacts;
    private Iterator<Opportunity> opportunities;
    private Iterator<CaseInstance> cases;
    private Document document;
    private Integer statusID = null;
    private String statusLabel = "";
    private Integer categoryID = null;
    private String categoryLabel = "";
    private Integer subCategoryID = null;
    private String subCategoryLabel = "";
    private Integer relatedDocumentID = null;
    private String relatedDocumentText = null;
    private String publishDateS = null;
    private String expirationDateS = null;
    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    private String fileName;

    /**
     * Gets the entity.
     * 
     * @return the SUCCESS result
     */
    public String get() throws Exception {
        if (this.getId() != null) {
            document = baseService.getEntityById(Document.class, this.getId());
            DocumentStatus status = document.getStatus();
            if (status != null) {
                statusID = status.getId();
                statusLabel = status.getLabel();
            }
            DocumentCategory category = document.getCategory();
            if (category != null) {
                categoryID = category.getId();
                categoryLabel = category.getLabel();
            }

            DocumentSubCategory subCategory = document.getSub_category();
            if (subCategory != null) {
                subCategoryID = subCategory.getId();
                subCategoryLabel = subCategory.getLabel();
            }

            User assignedTo = document.getAssigned_to();
            if (assignedTo != null) {
                this.setAssignedToID(assignedTo.getId());
                this.setAssignedToText(assignedTo.getName());
            }
            Document relatedDocument = document.getRelated_document();
            if (relatedDocument != null) {
                relatedDocumentID = relatedDocument.getId();
                relatedDocumentText = relatedDocument.getName();
            }
            Date publishDate = document.getPublish_date();
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    Constant.DATE_EDIT_FORMAT);
            if (publishDate != null) {
                publishDateS = dateFormat.format(publishDate);
            }
            Date expirationDate = document.getExpiration_date();
            if (expirationDate != null) {
                expirationDateS = dateFormat.format(expirationDate);
            }
            this.getBaseInfo(document, Document.class.getSimpleName(),
                    Constant.CRM_NAMESPACE);
            // Gets related accounts
            Set<Account> accountResult = document.getAccounts();
            accounts = accountResult.iterator();
            // Gets related contacts
            Set<Contact> contactResult = document.getContacts();
            contacts = contactResult.iterator();
            // Gets related opportunities
            Set<Opportunity> oppResult = document.getOpportunities();
            opportunities = oppResult.iterator();
            // Gets related cases
            Set<CaseInstance> caseResult = document.getCases();
            cases = caseResult.iterator();
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

    /**
     * @return the document
     */
    public Document getDocument() {
        return document;
    }

    /**
     * @param document
     *            the document to set
     */
    public void setDocument(Document document) {
        this.document = document;
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
     * @return the categoryID
     */
    public Integer getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID
     *            the categoryID to set
     */
    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * @return the subCategoryID
     */
    public Integer getSubCategoryID() {
        return subCategoryID;
    }

    /**
     * @param subCategoryID
     *            the subCategoryID to set
     */
    public void setSubCategoryID(Integer subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    /**
     * @return the relatedDocumentID
     */
    public Integer getRelatedDocumentID() {
        return relatedDocumentID;
    }

    /**
     * @param relatedDocumentID
     *            the relatedDocumentID to set
     */
    public void setRelatedDocumentID(Integer relatedDocumentID) {
        this.relatedDocumentID = relatedDocumentID;
    }

    /**
     * @return the publishDate
     */
    public String getPublishDateS() {
        return publishDateS;
    }

    /**
     * @param publishDate
     *            the publishDate to set
     */
    public void setPublishDate(String publishDateS) {
        this.publishDateS = publishDateS;
    }

    /**
     * @return the expirationDate
     */
    public String getExpirationDateS() {
        return expirationDateS;
    }

    /**
     * @param expirationDate
     *            the expirationDate to set
     */
    public void setExpirationDateS(String expirationDateS) {
        this.expirationDateS = expirationDateS;
    }

    /**
     * @return the upload
     */
    public File getUpload() {
        return upload;
    }

    /**
     * @param upload
     *            the upload to set
     */
    public void setUpload(File upload) {
        this.upload = upload;
    }

    /**
     * @return the uploadFileName
     */
    public String getUploadFileName() {
        return uploadFileName;
    }

    /**
     * @param uploadFileName
     *            the uploadFileName to set
     */
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    /**
     * @return the uploadContentType
     */
    public String getUploadContentType() {
        return uploadContentType;
    }

    /**
     * @param uploadContentType
     *            the uploadContentType to set
     */
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     *            the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the relatedDocumentText
     */
    public String getRelatedDocumentText() {
        return relatedDocumentText;
    }

    /**
     * @return the baseService
     */
    public IBaseService<Document> getBaseService() {
        return baseService;
    }

    /**
     * @param baseService
     *            the baseService to set
     */
    public void setBaseService(IBaseService<Document> baseService) {
        this.baseService = baseService;
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
     * @return the categoryLabel
     */
    public String getCategoryLabel() {
        return categoryLabel;
    }

    /**
     * @param categoryLabel
     *            the categoryLabel to set
     */
    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    /**
     * @return the subCategoryLabel
     */
    public String getSubCategoryLabel() {
        return subCategoryLabel;
    }

    /**
     * @param subCategoryLabel
     *            the subCategoryLabel to set
     */
    public void setSubCategoryLabel(String subCategoryLabel) {
        this.subCategoryLabel = subCategoryLabel;
    }

    /**
     * @param relatedDocumentText
     *            the relatedDocumentText to set
     */
    public void setRelatedDocumentText(String relatedDocumentText) {
        this.relatedDocumentText = relatedDocumentText;
    }

    /**
     * @param publishDateS
     *            the publishDateS to set
     */
    public void setPublishDateS(String publishDateS) {
        this.publishDateS = publishDateS;
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

    /**
     * @return the contacts
     */
    public Iterator<Contact> getContacts() {
        return contacts;
    }

    /**
     * @param contacts
     *            the contacts to set
     */
    public void setContacts(Iterator<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * @return the opportunities
     */
    public Iterator<Opportunity> getOpportunities() {
        return opportunities;
    }

    /**
     * @param opportunities
     *            the opportunities to set
     */
    public void setOpportunities(Iterator<Opportunity> opportunities) {
        this.opportunities = opportunities;
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
