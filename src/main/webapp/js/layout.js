let header = `
<div>
  <a class="active" href="index.html">Home</a>
  <a href="playlists.html">Playlists</a>
  <a href="test.html">Test</a>
</div>
`;

let footer = `
<p>&copy; Copyright 2018 - Polytechnique Montréal</br>Metafy est une plateforme de streaming développée à l'École Polytechnique de Montréal dans le cadre du cours LOG8430</p>
`;

document.getElementById('header').innerHTML = header;
document.getElementById('footer').innerHTML = footer;