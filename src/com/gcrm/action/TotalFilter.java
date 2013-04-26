package com.gcrm.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.gcrm.util.CommonUtil;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.ActionSupport;

public class TotalFilter extends ActionSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String url;

    public String getUrl() {
        return url;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public String execute() throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        String result = (String) request.getAttribute("jsp");
        url = result;
        if (url != null) {
            if (request.getQueryString() != null) {
                url = url + "?" + request.getQueryString();
            }
            String entityLabel = null;
            int beginIndex = url.indexOf("list");
            if (beginIndex != -1) {
                beginIndex += 4;
                int endIndex = url.indexOf(".jsp");
                String entityName = url.substring(beginIndex, endIndex);
                entityName = CommonUtil.lowerCaseString(entityName);
                entityLabel = getText("entity." + entityName + ".label") + " "
                        + getText("title.action.list");
                HttpSession session = request.getSession();
                ArrayList navigationList = (ArrayList) session
                        .getAttribute(Constant.NAVIGATION_HISTORY);
                if (navigationList == null) {
                    navigationList = new ArrayList();
                }
                String navigatoin = "<a href='" + Constant.APP_PATH + url
                        + "'>" + entityLabel + "</a>";
                if (navigationList.contains(navigatoin)) {
                    navigationList.remove(navigatoin);
                }
                navigationList.add(navigatoin);
                if (navigationList.size() > Constant.NAVIGATION_HISTORY_COUNT) {
                    navigationList.remove(0);
                }
                session.setAttribute(Constant.NAVIGATION_HISTORY,
                        navigationList);
            }
        }
        return "url";
    }
}