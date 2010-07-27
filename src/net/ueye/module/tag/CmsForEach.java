package net.ueye.module.tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.ueye.common.entity.TreeModel;

@SuppressWarnings("serial")
public class CmsForEach extends BodyTagSupport {
	
	private String var;
	private String indexed;
	private String items;
	private List<TreeModel> itemList;
	private Iterator<?> iterator;
	private  int rows=0;
	
	@Override
	public int doAfterBody() throws JspException {
		// TODO Auto-generated method stub
		if(iterator.hasNext()){
			pageContext.setAttribute(var,iterator.next());
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
	}

	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		itemList=getItemList();
		if(itemList!=null&&itemList.size()>0){			
			List<TreeModel> list=new ArrayList<TreeModel>();
			iterator=loop(list,itemList,rows).iterator();
			pageContext.setAttribute(var,iterator.next());
			return EVAL_BODY_INCLUDE;
		}
		else 
			return SKIP_BODY;
	}
	
	/**
	 * 循环遍历出 Module 以及它的子节点
	 * 添加到 List 中
	 * @param list 待添加的 List
	 * @param moduleList 需遍历的  moduleList 列表
	 * @return 返回添加过的 List
	 */
	@SuppressWarnings("unchecked")
	public List<TreeModel> loop(List<TreeModel> list,List<TreeModel> moduleList,int rows){
		for(int i=0;i<moduleList.size();i++){
			moduleList.get(i).setLevel(rows);
			moduleList.get(i).setRows(new String[rows]);
			list.add(moduleList.get(i));
			if(i==0)
				moduleList.get(i).setFirst(true);
			if(i==moduleList.size()-1)
				moduleList.get(i).setLast(true);
			if(moduleList.get(i).getChild()!=null&&moduleList.get(i).isExpanded())
				loop(list,(List<TreeModel>)moduleList.get(i).getChild(),rows+1);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List getItemList(){
		if(pageContext.getAttribute(items,PageContext.PAGE_SCOPE)!=null)
			return (List) pageContext.getAttribute(items,PageContext.PAGE_SCOPE);
		if(pageContext.getAttribute(items,PageContext.REQUEST_SCOPE)!=null)
			return (List) pageContext.getAttribute(items,PageContext.REQUEST_SCOPE);
		if(pageContext.getAttribute(items,PageContext.SESSION_SCOPE)!=null)
			return (List) pageContext.getAttribute(items,PageContext.SESSION_SCOPE);
		if(pageContext.getAttribute(items,PageContext.APPLICATION_SCOPE)!=null)
			return (List) pageContext.getAttribute(items,PageContext.APPLICATION_SCOPE);
		return null;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getIndexed() {
		return indexed;
	}

	public void setIndexed(String indexed) {
		this.indexed = indexed;
	}

}
