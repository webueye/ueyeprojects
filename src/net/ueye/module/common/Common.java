package net.ueye.module.common;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.ueye.module.entity.Account;
import net.ueye.module.entity.Role;
import net.ueye.module.entity.RoleFunction;
import net.ueye.module.entity.UserGroup;

/**
 * @author rubys@vip.qq.com 2009-7-4
 */
public class Common {

	/**
	 * 模块的添加
	 */
	public static final String MODULE_INSERT = "module_insert";// 模块的添加
	/**
	 * 模块的修改
	 */
	public static final String MODULE_UPDATE = "module_update";// 模块的修改
	/**
	 * 模块的删除
	 */
	public static final String MODULE_DELETE = "module_delete";// 模块的删除
	/**
	 * 模块的父节点没有改变
	 */
	public static final String MODULE_NON_PARENT_UPDATE = "module_non_parent_update";// 模块的父节点没有改变
	
	public static final String CMSCOLUMN_INSERT="cmsColumn_insert";
	
	public static final String CMSCOLUMN_UPDATE="cmsColumn_update";
	
	public static final String CMSCOLUMN_DELETE="cmsColumn_delete";
	
	public static final String CMSCOLUMN_NON_PARENT_UPDATE = "cmsColumn_non_parent_update";
	
	 /**
	  * 判断字符串是否为空，如果为null|"" 则返回 true
	  * @param str
	  * @return
	  */
	public static boolean isEmpty(String str){
		if(str != null && "".equals(str))
			return false;
		return true;
	}

	/**
	 * 将 List or Set 转换为 Map
	 * 
	 * @param collect
	 * @return Map<String,String>
	 */
	public static Map<String, String> collectionConvertToMap(
			Collection<Role> collect) {
		Map<String, String> map = new HashMap<String, String>();
		if (collect != null) {
			Iterator<Role> iter = collect.iterator();
			while (iter.hasNext()) {
				Role role = iter.next();
				map.put("" + role.getId(), role.getRoleName());
			}
		}
		return map;
	}

	public static Map<String, String> accountConvertToMap(
			Collection<Account> collect) {
		Map<String, String> map = new HashMap<String, String>();
		if (collect != null) {
			Iterator<Account> iter = collect.iterator();
			while (iter.hasNext()) {
				Account account = iter.next();
				map.put("" + account.getId(), account.getUsername());
			}
		}
		return map;
	}

	public static Map<String, String> UserGroupConvertToMap(Collection<UserGroup> collect) {
		Map<String, String> map = new HashMap<String, String>();
		if (collect != null) {
			Iterator<UserGroup> iter = collect.iterator();
			while (iter.hasNext()) {
				UserGroup userGroup = iter.next();
				map.put("" + userGroup.getId(), userGroup.getGroupName());
			}
		}
		return map;
	}

	public static long[] stringToLong(String... values) {
		if (values != null) {
			long[] newValue = new long[values.length];
			for (int i = 0; i < values.length; i++) {
				if (!"".equals(values[i])) {
					newValue[i] = Long.valueOf(values[i]).longValue();
				}
			}
			return newValue;
		}
		return null;
	}

	public static List<RoleFunction> moduleFunctionsToFunction(
			String[] moduleFunctions, long roleId) {
		List<RoleFunction> roleFunctionList = new ArrayList<RoleFunction>();
		if (moduleFunctions != null) {
			for (String str : moduleFunctions) {
				String[] mf = str.split("_");
				if (mf != null && mf.length > 1) {
					RoleFunction rf = new RoleFunction();
					rf.setRoleId(roleId);
					rf.setFunctionId(mf[1]);
					roleFunctionList.add(rf);
				}
			}
		}
		return roleFunctionList;
	}
	
	/**
	 * MD5 加密
	 * @param s
	 * @return
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static void print(Object value) {
		System.out.println("   :" + value);
	}

}
