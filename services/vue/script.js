const listeEntreprises = [];

fetch("http://localhost:8080/api/entreprises")
  .then((response) => {
    if (!response.ok) {
      throw new Error("Réponse non valide");
    }
    return response.json();
  })
  .then((data) => {
    const listeEntreprises = data; 
  })
    .catch((error) => {
    console.error("Erreur lors de la récupération des entreprises :", error);
    })

for (let i = 0; i < listeEntreprises.length; i++) {
  const entreprise = listeEntreprises[i];
  const entrepriseElement = document.createElement("div");
  entrepriseElement.textContent = `Nom: ${entreprise.nom}, Adresse: ${entreprise.adresse}, Ville: ${entreprise.ville}, Code Postal: ${entreprise.code_postal}, Employés: ${entreprise.employes}`;
  document.body.appendChild(entrepriseElement);
}