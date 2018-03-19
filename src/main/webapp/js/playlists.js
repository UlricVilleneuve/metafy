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
            findAllPlaylists()
        }
    };

    req.open("POST", server + "playlist/", true);
    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    req.send(JSON.stringify({name: query}));
}

function findAllPlaylists() {
    let req = new XMLHttpRequest();

    req.onreadystatechange = function () {
        if (req.readyState !== 4) return;

        if (req.status === 200) {
            console.log(req.responseType + " " + req.response);

            let playlists = JSON.parse(req.response);
            console.log(playlists);

            let info = "";
            playlists.forEach(function (p) {
                info += `<div name="playlist">`
                info += `<h1>${p.name}</h1>`
                info += `<input name="button" type="submit" value="Supprimer" class="pure-button" />`
                info += `<input name="data" type="hidden" value=${encodeURIComponent(JSON.stringify(p))} />`
                info += `<ol>`
                p.tracks.forEach(function (t) {
                    info += `<li>${t.name} by ${t.author}, duration : ${t.duration}ms.</li>`;
                })
                info += `</ol>`
                info += `</div>`
            });
            document.getElementById('info').innerHTML = info;
        } else {
            document.getElementById('info').innerHTML = "Cannot be retrieved";
        }

        var playlists = document.getElementsByName('playlist');
        for(let i = 0; i < playlists.length; i++) {
            let data = JSON.parse(decodeURIComponent(playlists[i].getElementsByTagName('input')[1].getAttribute("value")));
            console.log(data);
            playlists[i].getElementsByTagName('input')[0].onclick = function () {
                let req2 = new XMLHttpRequest();
                req2.onreadystatechange = update();
                req2.open("DELETE", server + "playlist/" + data.id, true);
                req2.send();
            }
        }
    };
    req.open("GET", server + "playlists/", true);
    req.send();
}

var update = function () {
    return function() {
        findAllPlaylists();
    }
};

findAllPlaylists();