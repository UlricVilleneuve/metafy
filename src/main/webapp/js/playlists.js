"use strict"
let server = "http://localhost:8080/api/";

let req = new XMLHttpRequest();
//let domParser = new DOMParser();

req.onreadystatechange = function () {
    if (req.readyState !== 4) return;

    if (req.status === 200) {
        console.log(req.responseType + " " + req.response);
        
        let db = JSON.parse(req.response);
        let playlists = db.playlists;
        console.log(playlists);

        let info = "";
        playlists.forEach(function (p) {
            info += `<h1>${p.name}</h1>`
            info += `<ol>`
            p.tracks.forEach(function (t) {
                info += `<li>${t.name} by ${t.author}, duration : ${t.duration}ms.</li>`;
            })
            info += `</ol>`
        });
        document.getElementById('info').innerHTML = info;
    } else {
        document.getElementById('info').innerHTML = "Cannot be retrieved";
    }
};

req.open("GET", server + "playlists/", true);
req.send();