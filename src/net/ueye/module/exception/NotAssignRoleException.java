package net.ueye.module.exception;

/**
 * 未分配角色
 * @author rubys@vip.qq.com
 *	Oct 7, 2009
 */
@SuppressWarnings("serial")
public class NotAssignRoleException extends RuntimeException {
	
	public NotAssignRoleException(){}
	
	public NotAssignRoleException(String msg){
		super(msg);
	}

}
