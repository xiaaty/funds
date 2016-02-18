package com.gqhmt.util;

import com.gqhmt.core.util.GlobalConstants;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class PageTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	private Pager pager;

	@SuppressWarnings("static-access")
	public int doStartTag() {
		try {
			JspWriter out = pageContext.getOut();
			out.print("<form  id='pageForm' action='" + pager.getLinkUrl() + "'method='post' >");
			if(pager.getPageSize()!= GlobalConstants.PAGE_SIZE){
				out.print("<input type=\"hidden\" name=\"pageNum\" value=\""+pager.getPageSize()+"\" />");
			}
			Map<String,String> conditions=pager.getSearchCondtions();
			if(conditions!=null&&conditions.size()>0){
				Set<Entry<String,String>>  entrys=conditions.entrySet();
				for(Entry<String,String> entry: entrys) {
					if(entry.getValue()!=null&&!entry.getValue().equalsIgnoreCase("null")&&entry.getValue().trim().length()>0){
					 out.print("<input type=\"hidden\"  name=\""+entry.getKey()+"\" value=\""+entry.getValue()+"\"/>");
					}
				}
				
			}
			
			out.print("<p>共计<a class=\"color03 pl5 pr5\" href=\"#\">" + pager.getTotalRows() + "</a>条记录</p><div class=\"fr\">");
			out.print("第<span class=\"color03 pl5 pr5\"> " + pager.getCurrentPage() + "</span>页，共<span class=\"color03 pr5 pl5\"> " + (pager.getTotalPages()) + " </span>页");
			String connector=(pager.getLinkUrl().contains("?")?"&":"?");
			if(pager.getLinkUrl().contains("?")){
				
			}
			if (pager.getCurrentPage() > 1) {
				out.print("<a class=\"p1 img ml10\" href=\"javaScript:pager('" + pager.getLinkUrl() +connector+ "cpage=1');\"></a>");
			}
			if(pager.getCurrentPage() <= pager.getTotalPages()&&pager.getCurrentPage()>1){
				out.print("<a class=\"p2 img\" href=\"javaScript:pager('" + pager.getLinkUrl()+connector + "cpage=" + (pager.getCurrentPage() - 1) + "');\"></a>");
			}
			if (pager.getCurrentPage() < pager.getTotalPages() && pager.getTotalPages() != 0) {
				out.print("<a class=\"p3 img\" href=\"javaScript:pager('" + pager.getLinkUrl()+connector  + "cpage=" + (pager.getCurrentPage() + 1) + "');\"></a>");
				out.print(" <a class=\"p4 img\" href=\"javaScript:pager('" + pager.getLinkUrl() +connector + "cpage=" + (pager.getTotalPages()) + "');\"></a>");
				out.print("<span class=\"pr5 pl15\">转到</span><input type=\"text\" name=\"gpage\" class=\"page_input mr5\" id=\"goPage\" />页");
				out.print("<input id=\"go\" class=\"p5 img\" type='button' name='button' />");
			}
	
			out.print("</form>");
            out.print("<script type=\"text/javascript\"> function pager(url){document.getElementById(\"pageForm\").action=url;"
            		+ "document.getElementById(\"pageForm\").submit();} $('#go').live('click',function(){$('#pageForm').append('<input type=\"hidden\" name=\"cpage\" value=\"'+$('#goPage').val()+'\" />');"
            		+"$('#pageForm').submit();}); $('#pageChange').live('change',function(){$('#pageForm input[name=pageNum]').remove();"
            		+"$('#pageForm').append('<input name=\"pageNum\" value=\"'+$(this).val()+'\" />');$('#pageForm').submit();});</script>");
			out.flush();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return super.SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Pager getPager() {
		return pager;
	}

	public void release() {
		super.release();
	}
}
