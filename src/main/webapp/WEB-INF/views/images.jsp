<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

</head>
<body>
<link type="text/css" href="../jquery/css/smoothness/jquery-ui-1.8.19.custom.css" rel="Stylesheet" />
<script type="text/javascript" src="../jquery/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../jquery/js/jquery-ui-1.8.19.custom.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script>
    $(document).ready(function() {

    var options = {
        target:     '#divToUpdate',
        url:        'images',
        success:    showResponse,
        error: function() {

        }
    };
    function showResponse(responseText, statusText, xhr, $form)  {

    }
    $("#imageUploadForm").ajaxForm(options);

    // change listener for the image selector
    $("#imageSelect").change(function() {
        var selected = $("#imageSelect option:selected");
        if (selected.val() != 0) {
            loadImage(selected.val());
        }
    });


        $(function() {
            $( "input:submit" ).button();
            $( "#removeButton" ).click(function() {
                alert('lcicked!')
            });
        });

    function loadImage(uuid) {
        $('#image').attr('src', "images/content?uuid=" + uuid);
    }
    })
</script>
<div class="ui-widget">
<form id="imageUploadForm" action="images" enctype="multipart/form-data">
    Upload New Image:
    <input type="file" name="file" /><br/>
    <input type="submit" value="Upload" />
</form>
</div>

<!-- Links to all of the images -->
<div class="ui-widget">
Select Image: <select id="imageSelect">
<c:forEach var="image" items="${images.images}">
    <option value="${image.uuid}">${image.uuid}</option>
</c:forEach>
</select>
<input type="submit" id="removeButton" value="Delete Image" />
</div>


<br/>

<img id="image" />
</body>
</html>