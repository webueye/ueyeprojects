<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ueye" uri="ueye" %>

<html>
	
	<script type="text/javascript" src="ckeditor.js"></script>
	
	<body>
		<form id="fm" name="fm" action="/erp/ansy-data">

			<p>
				"Kama" skin:<br/>
				<textarea cols="80" id="editor_kama" name="editor_kama" rows="10">&lt;p&gt;This is some &lt;strong&gt;sample text&lt;/strong&gt;. You are using &lt;a href="http://www.fckeditor.net/"&gt;CKEditor&lt;/a&gt;.&lt;/p&gt;</textarea>
				<script type="text/javascript">
				//<![CDATA[

					CKEDITOR.replace( 'editor_kama',
						{
							skin : 'kama'
						});

				//]]>
				</script>
			</p>
			<p>
				"Office 2003" skin:<br/>
				<textarea cols="80" id="editor_office2003" name="editor_office2003" rows="10">&lt;p&gt;This is some &lt;strong&gt;sample text&lt;/strong&gt;. You are using &lt;a href="http://www.fckeditor.net/"&gt;CKEditor&lt;/a&gt;.&lt;/p&gt;</textarea>
				<script type="text/javascript">
				//<![CDATA[

					CKEDITOR.replace( 'editor_office2003',
						{
							skin : 'office2003'
						});

				//]]>
				</script>
			</p>
			
			
		</form>

	</body>
</html>
