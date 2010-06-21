<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<div align="right" style="padding-left:10px">
	
	<div style="float:right;margin-left:20px" align="right">
			
			<c:if test="${page.pageCount>1}">
				<c:if test="${page.pageNum==1}">
					[<span>首页</span> |
					<span>上一页</span> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?page.pageNum=${page.pageNum+1}${param.param}" >下一页</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?page.pageNum=${page.pageCount}${param.param}" >末页</a>]
				</c:if>
				<c:if test="${page.pageNum>1 and page.pageNum<page.pageCount}">
					[<a href="${pageContext.request.contextPath}/${param.actionURL}?page.pageNum=1${param.param}" >首页</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?page.pageNum=${page.pageNum-1}${param.param}" >上一页</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?page.pageNum=${page.pageNum+1 }${param.param}" >下一页</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?page.pageNum=${page.pageCount }${param.param}" >末页</a>]
				</c:if>
				<c:if test="${page.pageNum==page.pageCount}">
					[<a href="${pageContext.request.contextPath}/${param.actionURL}?page.pageNum=1${param.param}" >首页</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?page.pageNum=${page.pageNum-1}${param.param}" >上一页</a> |
					<span>下一页</span> |
					<span>末页</span>]
				</c:if>	
			</c:if>
	</div>
	<div align="right" style="margin:0 auto">
		<c:if test="${page.pageCount>0 }">
			共 <span>${page.totalCount }</span> 条 | 
			共 <span>${page.pageCount }</span> 页 | 
			第 <span>${page.pageNum }</span> 页 
		</c:if>
	</div>
		
</div>