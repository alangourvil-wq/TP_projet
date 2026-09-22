const listeEntreprises = document.getElementById("liste-entreprises");

fetch("http://localhost:8080/api/entreprises")
    .then((response) => {
        if (!response.ok) {
            throw new Error("Réponse non valide");
        }
        return response.json();
    })
    .then((entreprises) => {
        for (const entreprise of entreprises) {
            const article = document.createElement("article");
            article.className = "carte-entreprise";
            article.dataset.id = entreprise.id;

            const image = document.createElement("img");
            image.className = "image-entreprise";
            image.src = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.lesulis.fr%2Ffileadmin%2Fwww.lesulis.fr%2FMEDIA%2FSortir%2FPiscine_Municipale%2Fimages%2F2023%2Fbassin_25m.png&f=1&nofb=1&ipt=bacb97358defab5f46e1b4e0b616164afdaaca045c3dc21d7972567f5c622f87";
            image.alt = `Entreprise ${entreprise.nom}`;

            const nom = document.createElement("h2");
            nom.className = "nom-entreprise";
            nom.textContent = entreprise.nom;

            const note = document.createElement("div");
            note.className = "note-entreprise";
            note.textContent = entreprise.note_moyenne;;

            const lienDetail = document.createElement("a");
            lienDetail.className = "lien-detail";
            lienDetail.href = `page_detail_v3.html?id=${entreprise.id}`;
            lienDetail.textContent = "Voir détail";

            article.append(image, nom, note, lienDetail);
            listeEntreprises.appendChild(article);
        }
    })
    .catch((error) => {
        console.error("Erreur lors de la récupération des entreprises :", error);
    });
