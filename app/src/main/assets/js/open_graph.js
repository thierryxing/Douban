function getOGInfos(){
    var resultJson;
    resultJson = JSON.stringify([].slice.apply(
      document.querySelectorAll('meta[property^="og"]')).map(
        function(el){
          return [el.getAttribute('property'),el.getAttribute('content')]
        }
      )
    );
    console.log(resultJson);
    window.ogInfo.updateOGObject(resultJson);
}
getOGInfos();