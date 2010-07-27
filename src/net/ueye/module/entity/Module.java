package net.ueye.module.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.ueye.common.entity.TreeModel;

/**
 * @author rubys@vip.qq.com
 * Aug 30, 2009
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ueye_module")
public class Module extends TreeModel implements Serializable{
	
	private String name;
	private String icon;
	private String label;
	private String action;
	private int orderValue=0;	
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=Module.class)
	@JoinColumn(name="parent_id")
	private Module parent;
	
	@Transient
	private List<Function> functions;	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public Module getParent() {
		return parent;
	}
	public void setParent(Module parent) {
		this.parent = parent;
	}
	public int getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(int orderValue) {
		this.orderValue = orderValue;
	}
	@Transient
	public String getIndent(){
		StringBuffer sb=new StringBuffer();
		for(int i=1;i<getWidth();i++){
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		}
		return sb.toString();
	}
	
	public List<Function> getFunctions() {
		return functions;
	}
	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
}
