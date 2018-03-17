"use strict"
let server = "http://localhost:8080/api/";

document.getElementById('query').onkeydown = function(e){
    if(e.keyCode == 13){
      submit()
    }
 };

 function submit(){
    let query = document.getElementById('query').value;
    let req = new XMLHttpRequest();

    req.onreadystatechange = function () {
        if (req.readyState !== 4) return;

        if (req.status === 200) {
            console.log(req.responseType + " " + req.response);

            let tracks = JSON.parse(req.response);
            console.log(tracks);

            let info = "";
            info += `<h1>Results</h1>`
            info += `<ol>`
            tracks.forEach(function (t) {
                info += `<li>${t.name} -- ${t.author}   
                        &#8986 ${Math.floor(t.duration/60000)}min${Math.floor(t.duration%60000/1000)}s.
                        <img src="/metafy/img/${t.origin}.png" class="api-image">`
                //Display play button only if link for preview is available
                if(t.url != undefined){
                    info += `<a href=${t.url} class="unicode" target="_blank">&#x25b6;</a>`
                }
                info += `</li>`;
            });
            info += `</ol>`
            document.getElementById('info').innerHTML = info;
        } else {
            document.getElementById('info').innerHTML = "Cannot be retrieved";
        }
    };

    req.open("GET", server + "search/" + query, true);
    req.send();
}
