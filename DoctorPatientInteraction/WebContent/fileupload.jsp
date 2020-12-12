<!DOCTYPE html>
<html>
<head>
<style type="text/css">

.fileUpload {
    position: relative;
    overflow: hidden;
    margin: 10px;
}
.fileUpload input.upload {
    position: absolute;
    top: 0;
    right: 0;
    margin: 0;
    padding: 0;
    font-size: 20px;
    cursor: pointer;
    opacity: 0;
    filter: alpha(opacity=0);
}
</style>
<title>Ajax File Upload with Progress Bar</title>
<!-- Include jQuery form & jQuery script file. -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="js/fileUploadScript.js"></script>
<!-- Include css styles here -->
<link href="css/stylef.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>
<br>
	<h3>Upload File</h3>
	<div class="formdiv">
<form id="UploadForm" action="UploadFile" method="post"
			enctype="multipart/form-data">
		<table>
			<tr>
				<td><input type="file" size="60" id="myfile" name="myfile">
				</td>
				<td><input type="submit" class="button" value="Upload"></td>
				<td width="50%"  align="right"><div id="progressbox">
						<div id="progressbar"></div>
						<div id="percent">0%</div>
					</div></td>
			</tr>

		</table>

		




			<div id="message"></div>
		</form>
	</div>
</body>
</html>
