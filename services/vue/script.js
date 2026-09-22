const API = "http://localhost:8080/api/entreprises";
const listeEntreprises = document.getElementById("liste-entreprises");
const IMAGE_PAR_DEFAUT = "arreter-tailler-arbres.jpg";

async function afficherEntreprises() {
    const entreprises = await (await fetch(API)).json();

    for (const entreprise of entreprises) {
        const moyenne = await (await fetch(`${API}/${entreprise.id}/moyenne`)).json();
        const nombreAvis = await (await fetch(`${API}/${entreprise.id}/notes`)).json();
        const nbEmployes = entreprise.employes ? entreprise.employes.length : 0;

        const article = document.createElement("article");
        article.className = "carte-entreprise";
        article.dataset.id = entreprise.id;

        const categorie = document.createElement("span");
        categorie.className = "categorie-entreprise";
        categorie.textContent = entreprise.domaine || "Non renseigné";

        const entete = document.createElement("div");
        entete.className = "entete-entreprise";

        const logo = document.createElement("img");
        logo.className = "logo-entreprise";
        logo.src = entreprise.imageUrl || IMAGE_PAR_DEFAUT;
        logo.alt = `Logo ${entreprise.nom}`;

        const nom = document.createElement("h2");
        nom.className = "nom-entreprise";
        nom.textContent = entreprise.nom;

        entete.append(logo, nom);

        const description = document.createElement("p");
        description.className = "description-entreprise";
        description.textContent = entreprise.description || "";

        const note = document.createElement("span");
        note.className = "note-entreprise";
        if (moyenne) {
            note.innerHTML = `<span class="etoile-note">★</span> ${moyenne.toFixed(1)}`;
        } else {
            note.textContent = "Pas encore noté";
            note.classList.add("sans-note");
        }

        const avis = document.createElement("span");
        avis.textContent = `${nombreAvis} avis`;

        const employes = document.createElement("span");
        employes.textContent = `${nbEmployes} employé${nbEmployes > 1 ? "s" : ""}`;

        const lienDetail = document.createElement("a");
        lienDetail.className = "lien-detail";
        lienDetail.href = `page_detail_v3.html?id=${entreprise.id}`;
        lienDetail.textContent = "Voir détail";

        const lieu = document.createElement("span");
        lieu.className = "lieu-entreprise";
        lieu.textContent = entreprise.lieu || "";

        const stats = document.createElement("div");
        stats.className = "stats-entreprise";
        stats.append(note, avis, employes, lienDetail, lieu);

        article.append(categorie, entete, description, stats);
        listeEntreprises.appendChild(article);
    }
}

afficherEntreprises().catch((error) => {
    console.error("Erreur lors de la récupération des entreprises :", error);
});
