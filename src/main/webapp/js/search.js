"use strict"
let server = "http://localhost:8080/api/";

document.getElementById('searchbutton').onclick = function(){
    submit();
}

document.getElementById('querry').onkeydown = function(e){
    if(e.keyCode == 13){
      submit()
    }
 };

 function submit(){
    let querry = document.getElementById('querry').value;
    let req = new XMLHttpRequest();

    req.onreadystatechange = function () {
        if (req.readyState !== 4) return;

        if (req.status === 200) {
            console.log(req.responseType + " " + req.response);

            let tracksDTO = JSON.parse(req.response);
            let tracks = tracksDTO.tracks;
            console.log(tracks);

            let info = "";
            info += `<h1>Results</h1>`
            info += `<ol>`
            tracks.forEach(function (t) {
                info += `<li>${t.name} by ${t.author},
                        duration : ${Math.floor(t.duration/60000)}min${Math.floor(t.duration%60000/1000)}s.
                        <img src="/metafy/img/${t.origin}.png" style="height:16px; width:16px;">`
                //Display play button only if link for preview is available
                if(t.url != undefined){
                    info += `<a href=${t.url} class="" target="_blank">&#x25b6;</a>`
                }
                info += `</li>`;
            });
            info += `</ol>`
            document.getElementById('info').innerHTML = info;
        } else {
            document.getElementById('info').innerHTML = "Cannot be retrieved";
        }
    };

    req.open("GET", server + "search/" + querry, true);
    req.send();
}
