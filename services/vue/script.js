const params = new URLSearchParams(window.location.search);
const idEntreprise = params.get("id");

async function chargerDetail() {
  const res = await fetch(`http://localhost:8080/api/entreprises/${idEntreprise}`);
  const entreprise = await res.json();

  document.getElementById("nom-entreprise").textContent = entreprise.nom;
  document.getElementById("moyenne-note").textContent =
    `${entreprise.moyenneNote.toFixed(1)} / 5`;
}

async function envoyerNote(valeur) {
  await fetch(`http://localhost:8080/api/entreprises/${idEntreprise}/notes`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ valeur: valeur })
  });
  // on recharge la moyenne après le vote
  chargerDetail();
}

// exemple : 5 étoiles cliquables dans le HTML
document.querySelectorAll(".etoile").forEach((etoile, index) => {
  etoile.addEventListener("click", () => envoyerNote(index + 1));
});

chargerDetail();