<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

</head>
<body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script>
    var options = {
        target:     '#divToUpdate',
        url:        'images',
        success:    showResponse,
        error: function() {
            $("#percentComplete").append("error");
        }
    };
    function showResponse(responseText, statusText, xhr, $form)  {

    }
    $("#imageUploadForm").ajaxForm(options);

    function loadImage(uuid) {
        $('#image').attr('src', "images/content?uuid=" + uuid);
    }
</script>
<div id="percentComplete" />
<form id="imageUploadForm" action="images" enctype="multipart/form-data">
    <table>
        <tr>
            <td><input type="file" name="file" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" />
            </td>
        </tr>
    </table>
</form>

<!-- Links to all of the images -->
<c:forEach var="image" items="${images.images}">
<a href="javascript:loadImage('${image.uuid}')">Image - ${image.uuid}</a><br/>
</c:forEach>
<img id="image" />
</body>
</html>