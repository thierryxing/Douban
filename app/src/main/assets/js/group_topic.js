var body;
var fontSize;
var imageMaxWidth;

function setFontSize() {
    body = document.getElementById('content');
    if (window.group) {
        fontSize = window.group.getFontSize();
        body.style.fontSize = fontSize;
    }
}

function getBody() {
   body = document.getElementById('content');
   if (window.group) {
      body.innerHTML = window.group.getContent();
   }
}

function onLoadImage(object) {
    var src = object.src;
    var alt = object.alt;
    if (window.group) {
        var isImageOn = window.group.isImageOn();
        var defaultImage = window.group.getDefaultImage();
        if (isImageOn == true && src == defaultImage) {
            object.src = alt;
        }
    }
}

function onClickImage(object) {
    var id = object.id;
    if (window.group) {
        window.group.showImage(id);
    }
}

function onClickVideo(object) {
    var url = object.getAttribute('data-url');
    if (window.group) {
        window.group.showVideo(url);
    }
}

function initialize() {
    setFontSize();
    getBody();
}