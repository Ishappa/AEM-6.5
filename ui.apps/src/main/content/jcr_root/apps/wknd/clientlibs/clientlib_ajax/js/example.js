
function loadDoc() {

let btn=$(".btn")
console.log(btn)

  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
  document.getElementById("demo").innerHTML = this.responseText;

  var h2Elements = document.getElementsByTagName("h2");


  for (var i = 0; i < h2Elements.length; i++) {
    h2Elements[i].style.color = "green";
  }

  console.log("Loading", document.getElementById("demo").innerHTML);
}

  };
  xhttp.open("GET", "/apps/wknd/components/ajaxEx/ajax.txt", true);
  xhttp.send();
}
