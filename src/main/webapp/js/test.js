"use strict"
let server = "http://localhost:8080/api/";

document.getElementById('searchbutton').onclick = function () {
    let playlistName = document.getElementById('playlistname').value;
    let req = new XMLHttpRequest();
    //let domParser = new DOMParser();

    req.onreadystatechange = function () {
        if (req.readyState !== 4) return;

        if (req.status === 200) {
            console.log(req.responseType + " " + req.response);
            let playlist = JSON.parse(req.response);
            console.log(playlist);

            let info = "";
            let i = 1;
            playlist.tracks.forEach(function (t) {
                info += `Track ${i++} : ${t.name} by ${t.author}, duration : ${t.duration}ms.</br>`;
            });
            console.log(info);
            document.getElementById('info').innerHTML = info;
        } else {
            document.getElementById('info').innerHTML = "Cannot be retrieved";
        }
    };

    req.open("GET", server + "displaytest/", true); //+ playlistName
    req.send();
}