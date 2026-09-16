const id = new URLSearchParams(window.location.search).get("id");
console.log("ID de l'entreprise:", id);

fetch("http://localhost:8080/api/entreprises?id=" + id)
    .then((response) => {
        if (!response.ok) {
            throw new Error("Réponse non valide");
        }
        return response.json();
    })
    .then((entreprises) => {
        const entreprise = entreprises[0];
        const article = document.createElement("article");
        article.className = "carte-entreprise";
        article.dataset.id = entreprise.id;

        const nom = document.createElement("h2");
        nom.className = "nom-entreprise";
        nom.textContent = entreprise.nom;

        article.append(nom);
        document.body.appendChild(article);
    })
    .catch((error) => {
        console.error("Erreur lors de la récupération des entreprises :", error);
    });
