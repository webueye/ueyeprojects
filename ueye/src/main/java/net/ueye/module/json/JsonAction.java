package net.ueye.module.json;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import net.ueye.module.action.BaseAction;
import net.ueye.module.entity.*;
import net.ueye.module.service.ModuleService;

@SuppressWarnings("serial")
public class JsonAction extends BaseAction {

	private String moduleIconContentType;
	private File moduleIcon;
	private String moduleIconFileName;
	private ModuleService moduleService;
	
	/**
	 * 模块管理中的图标上传
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public String uploadModuleIcon() throws Exception{
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		String targetDir=ServletActionContext.getRequest().getRealPath("/images");
		String moduleIconName=System.currentTimeMillis()+"_"+moduleIconFileName;
		File target = new File(targetDir, moduleIconName);
		FileUtils.copyFile(this.getModuleIcon(),target);
		setModuleIconFileName(target.getName());	
		ServletActionContext.getResponse().getWriter().println(moduleIconFileName);
		ServletActionContext.getResponse().getWriter().close();
		return null;
	}

	/**
	 * 模块列表 
	 * @return null
	 * @throws Exception
	 */
	public String module() throws Exception {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		List<Module> list = new ArrayList<Module>();
		Module rootModule=null;
		if(isAdministrator()){
			rootModule=getModuleService().findModuleNoLeafList(null,true);
		}
		else{
			Account account=getCurrentAccount();
			List<Role> accountRole=getAccountService().getAccountAllRoles(account.getId());
			rootModule=getModuleService().findModuleNoLeafList(accountRole,false);
		}		
		List<Module> ml=new ArrayList<Module>();
		ml.add(rootModule);
		if(rootModule!=null)
			forEachModule(list, ml);
		JSONArray json = JSONArray.fromObject(list);
		ServletActionContext.getResponse().getWriter().println(json);
		ServletActionContext.getResponse().getWriter().close();
		return null;
	}
	
	public String column(){
		beforeAnsy();
		//List<CmsColumn> columnList = new ArrayList<CmsColumn>();
		
		
		
		
		return null;
	}
	
	
	
	/**
	 * 更改模块列表
	 * @param list 更改后的新模块列表
	 * @param moduleList 更改前的模块列表
	 */
	@SuppressWarnings("unchecked")
	public void forEachModule(List<Module> list, List<Module> moduleList) {
		if (moduleList != null) {
			for (Module module : moduleList) {
				if (!module.isLeaf()) {
					Module tn = new Module();
					tn.setId(module.getId());
					tn.setLabel(indent(module.getWidth()) + module.getLabel());
					tn.setWidth(module.getWidth());
					list.add(tn);
					if (module.getChild() != null && module.getChild().size() > 0) {
						forEachModule(list, (List<Module>)module.getChild());
					} else {
						forEachModule(list,getModuleService().findModuleByParent(module.getId()));
					}
				}
			}
		}
	}

	/**
	 * 列表缩排
	 * @param width 列表缩排字符串的个数
	 * @return string 
	 */
	public String indent(int width) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < width; i++) {
			sb.append("&nbsp;&nbsp;");
		}
		return sb.toString();
	}

	public String getModuleIconContentType() {
		return moduleIconContentType;
	}

	public void setModuleIconContentType(String moduleIconContentType) {
		this.moduleIconContentType = moduleIconContentType;
	}

	public File getModuleIcon() {
		return moduleIcon;
	}

	public void setModuleIcon(File moduleIcon) {
		this.moduleIcon = moduleIcon;
	}

	public String getModuleIconFileName() {
		return moduleIconFileName;
	}

	public void setModuleIconFileName(String moduleIconFileName) {
		this.moduleIconFileName = moduleIconFileName;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
		
}
