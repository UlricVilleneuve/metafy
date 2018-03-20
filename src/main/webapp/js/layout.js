let header = `
<div>
  <a href="index.html">Home</a>
  <a href="search.html">Search</a>
  <a href="playlists.html">Playlists</a>
</div>
`;

let footer = `
<p>&copy; Copyright 2018 - Polytechnique Montréal</br>Metafy est une plateforme de streaming développée à l'École Polytechnique de Montréal dans le cadre du cours LOG8430</p>
`;

document.getElementById('header').innerHTML = header;
document.getElementById('footer').innerHTML = footer;

// Sets the current page as the active page in the navbar
var as = document.getElementById('header').getElementsByTagName('a');
for(var i = 0; i < as.length; i++) {
    if (as[i].href == location.href){ as[i].className="active"; }
}