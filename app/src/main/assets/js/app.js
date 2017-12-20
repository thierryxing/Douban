// Query the device pixel ratio.
//-------------------------------

function getDevicePixelRatio() {
    // No pixel ratio available. Assume 1:1.
    if (window.devicePixelRatio === undefined) return 1;
    return window.devicePixelRatio;
}

// set fontface

function setFontFace(fontFace) {
    // console.log("initFontFace() face=" + fontFace);
    if (fontFace) {
        $("body").css("fontFamily", fontFace);
    }
}

// set textsize

function setTextSize(size) {
    //  set font size, eg, 1em 2em
    // console.log("setTextSize size=" + size);
    $("body").css("fontSize", size);
}

// set textcolor

function setTextColor(color) {
    //  set font color, eg, #ffffff
    // console.log("setTextColor color=" + color);
    $("body").css("color", color);
}

// 设置某个元素的某个属性
// 举例 header.style.height=100px
// setCssById("header","height","100px")

function setCssById(id, name, value) {
    // console.log("setCssById id=" + id + " name=" + name + " value=" + value);
    var element = $("#" + id);
    if (element) {
        element.css(name, value);
    }
}
// 图片下载完成，将默认图片替换为本地图片

function setImage(id) {
    // console.log("setImage id=" + id);
    var img = $("#" + id);
    if (img) {
        var src = img.attr("src"); // 此时应该 src == srcNone
        var srcNone = img.data("none"); // data-none 默认图片地址
        //var srcCache = img.data("cache"); // data-cache 本地图片地址
        var srcData = img.data("src"); // data-src 原始网络地址

        // console.log("setImage src=" + src);
        // console.log("setImage srcNone=" + srcNone);
        // console.log("setImage srcCache=" + srcCache);
        // console.log("setImage srcData=" + srcData);
        if (src == srcNone) {
            //img.attr("src", srcCache);
            img.attr("src", srcData);
        }
    }
}

// init textsize

function initTextSize() {
    // console.log("initTextSize()");
    if (window.app) {
        var size = window.app.getTextSize();
        if (size) {
            setTextSize(size);
        }
    }
}

// init textcolor

function initTextColor() {
    // console.log("initTextColor()");
    if (window.app) {
        var color = window.app.getTextColor();
        if (color) {
            setTextColor(color);
        }
    }
}

// init fontface

function initFontFace() {
    // console.log("initFontFace()");
    if (window.app) {
        var fontFace = window.app.getFontFace();
        if (fontFace) {
            setFontFace(fontFace);
        }
    }
}

function initPageTop() {
    // console.log("initPageTop()");
    if (window.app) {
        var statusBarHeight = window.app.getStatusBarHeight();
        var actionBarHeight = window.app.getActionBarHeight();
        setCssById("page_top", "height", statusBarHeight + actionBarHeight);
    }
}

function initPageBottom() {
    // console.log("initPageBottom()");
    if (window.app) {
        var size = window.app.getSystemBarHeight();
        setCssById("page_bottom", "height", size);
    }
}

// init set content

function initBody() {
    // console.log("initBody()");
    var pageContent = $("#page_content");
    if (window.app) {
        pageContent.html(window.app.getContent());
    }
}

// init set content background color

function initPageContentBackgroundColor() {
    var pageContent = $("#page_content");
    var body = $("body");
    if (window.app) {
        pageContent.css("background-color", window.app.getContentBackgroundColor());
        body.css("background-color", window.app.getContentBackgroundColor());
    }
}

// callback, on img clicked

function onImageClicked(img) {
    // console.log("onImageClicked() img=" + img);
    var id = img.id;
    if (window.app) {
        window.app.onImageClicked(id);
    }
}

// callback, on video clicked

function onVideoClicked(video) {
    // console.log("onVideoClicked() video=" + video);
    var url = video.getAttribute('data-url');
    if (window.app) {
        window.app.onVideoClicked(url);
    }
}

// callback, on page ready

function onWebReady() {
    // console.log("onWebReady()");
    if (window.app) {
        window.app.onWebReady();
    }
}

// init complete, page is ready

function onReady() {
    // console.log("onReady()");
    initBody();
    initPageContentBackgroundColor();
    //initFontFace();
    //initPageTop();
    //initPageBottom();
    // initTextSize();
    // initTextColor();
    onWebReady();
}

//window.onload=initialize
Zepto(function($) {
    onReady();
})
